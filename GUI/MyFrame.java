package GUI;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algo.ShortestPathAlgo;
import File_format.Path2KML;
import File_format.gameToCSVWriter;
import GameComponents.Fruit;
import GameComponents.Game;
import GameComponents.Pacman;
import GameComponents.Path;
import Geom.Point2D;
import Geom.Point3D;
import Resourses.Map;

public class MyFrame extends JFrame implements MouseListener{

	private Game mainGame;
	private Map m;
	private JPanel _panel;
	private BufferedImage mapImage;
	private File mapFile;
	private static Dimension d = new Dimension(400, 250);
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu AddMenu;
	private JMenu AlgoMenu;
	private Point2D mouseClick;
	private boolean shouldPaintPacman;
	private ShortestPathAlgo SPA;
	private Point2D frameSizePixels;
	private boolean shouldDrawLines;

	public MyFrame() {
		initFrame();
	}
	/**
	 * Frame initiator: initiate image buffer, menu, panel
	 */
	public void initFrame() {
		m = new Map();
		shouldDrawLines = false;
		mainGame = new Game();
		this.setPreferredSize(d);
		try {
			//ImageINITIALIZER
			mapFile = m.getFile();
			mapImage = ImageIO.read(mapFile);
		} 
		catch (Exception e) {
			System.err.println("ImageIO: Cant load image");
		}
		createMenu();
		createPanel();

		shouldPaintPacman = true;

	}
	/**
	 * Create Main options menu
	 */
	public void createMenu() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		AddMenu = new JMenu("Add");
		AlgoMenu = new JMenu("Run");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		createFileMenu();
		createAddMenu();
		createAlgoMenu();
		this.setJMenuBar(menuBar);

	}

	/**
	 * Create file menu
	 */
	public void createFileMenu() {
		JMenuItem open = new JMenuItem("Open File");
		JMenuItem save = new JMenuItem("Save File");
		JMenuItem saveKML = new JMenuItem("Save Path2KML");
		//SAVEKML PLATFORM
		saveKML.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(new File("c"));
				fc.setDialogTitle("Save your Game");
				fc.setFileFilter(new FileNameExtensionFilter("kml", "KML"));
				int value = fc.showSaveDialog(null);
				File f = fc.getSelectedFile();

				if(f != null) {
					String filePath = f.getAbsolutePath();
					Path2KML p2k = new Path2KML();
					p2k.writePath2KML(mainGame.getPacmans(), filePath);
				}
			}
		});
		
		//OPEN FILE PLATFORM
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {                                 
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("c"));
				chooser.setFileFilter(new FileNameExtensionFilter("csv","CSV"));
				int value = chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				if (f != null) {
					String fileName = f.getAbsolutePath();
					mainGame = new Game(fileName);
				}
			}

		});

		///////////////////////////////////////////////////////////////////////////////////////
		//SAVES THE GAME AS A CSV FILE
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(new File("c:\\"));
				fc.setDialogTitle("Save your Game");
				fc.setFileFilter(new FileNameExtensionFilter(".csv", "CSV File"));
				int value = fc.showSaveDialog(null);
				File f = fc.getSelectedFile();

				if(f != null) {
					String filePath = f.getAbsolutePath();
					gameToCSVWriter saveGame = new gameToCSVWriter();
					saveGame.CSVWrite(mainGame.getPacmans(), mainGame.getFruits(), filePath);
				}

			}
		});
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveKML);
		menuBar.add(fileMenu);
	}

	/**
	 * Create Add menu
	 */

	///////////////////////////////////////////////////////////
	public void createAddMenu() {
		JMenuItem addPacman = new JMenuItem("Add Pacman");
		JMenuItem addFruit = new JMenuItem("Add Fruit");

		addPacman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shouldPaintPacman = true;
				// mouseClicked
			}
		});

		addFruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shouldPaintPacman = false;

			}
		});
		/////////////////////////////////////////////////////////
		AddMenu.add(addPacman);
		AddMenu.add(addFruit);
		menuBar.add(AddMenu);
	}

	public void createAlgoMenu() {
		JMenuItem findShortPath = new JMenuItem("Find Short Path");
		JMenuItem playGame = new JMenuItem("Play Game");
		JMenuItem reset = new JMenuItem("Reset");

		findShortPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SPA = new ShortestPathAlgo(mainGame);
				shouldDrawLines = true;

			}
		});
		
		
		playGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainGame = new Game();
			}
		});
		
		
		AlgoMenu.add(findShortPath);
		AlgoMenu.add(playGame);
		AlgoMenu.add(reset);
		menuBar.add(AlgoMenu);
	}
	/**
	 * Create Main Panel with map image on it
	 */
	public void createPanel() {
		_panel = new JPanelBG();
		_panel.setLayout(new BorderLayout());
		_panel.addMouseListener(this);
		this.add(_panel);
	}
	/**
	 * This subclass is for main panel, draws image
	 * @author Igor
	 *
	 */
	public class JPanelBG extends JPanel{
		@Override
		public void paint(Graphics g) {
			super.paintComponent(g);
			int w = MyFrame.this.getWidth();
			int h = MyFrame.this.getHeight();
			frameSizePixels = new Point2D(w, h);

			Image img = mapImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			g.drawImage(img, 0, 0, null);
			Point2D frameSizePixels = new Point2D(w,h);
			Iterator<Pacman> pacmanList = mainGame.getPacmans().iterator();
			g.setColor(Color.YELLOW);
			while(pacmanList.hasNext()) {
				Pacman pacman = pacmanList.next();
				Point2D pacPixels = m.CoordsToPixel(pacman.getCoords(), frameSizePixels);
				g.fillOval((int)pacPixels.x()-10, (int)pacPixels.y()-10, 20, 20);

			}

			Iterator<Fruit> fruitList = mainGame.getFruits().iterator();
			g.setColor(Color.RED);
			while(fruitList.hasNext()) {
				Fruit fruit = fruitList.next();
				Point2D fruPixels = m.CoordsToPixel(fruit.getCoords(), frameSizePixels);
				g.fillOval((int)fruPixels.x()-7, (int)fruPixels.y()-7, 14, 14);
			}
			if(shouldDrawLines) paintLines(g);
			else {
			repaint();
			}
			
		}
		public void paintLines(Graphics g) {
			repaint();
			Iterator<Pacman> pIte = mainGame.getPacmans().iterator();
			while(pIte.hasNext()) {
				Pacman singlePacPath = pIte.next();
				Iterator<Point3D> cPoint = singlePacPath.getPath().iterator();
				Point2D pPoint = null;
				if(cPoint.hasNext()) pPoint = m.CoordsToPixel(cPoint.next(), frameSizePixels);
				g.setColor(Color.BLUE);
				while(cPoint.hasNext()) {
					Point2D startPoint = pPoint;
					pPoint = m.CoordsToPixel(cPoint.next(), frameSizePixels);
					g.drawLine((int)startPoint.x(), (int)startPoint.y(), (int)pPoint.x(), (int)pPoint.y());
				}
			}
		}
	};


	public static void main(String[] args) {
		MyFrame mainGameFrame = new MyFrame();
		mainGameFrame.setSize(d);
		mainGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGameFrame.setVisible(true);
	}

	///////////////////////////////////////////////////////////////////////////
	@Override
	public void mouseClicked(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {
		mouseClick = new Point2D(e.getX(), e.getY());
		System.err.println(mouseClick);
		System.err.println(m.PixelToCoords(mouseClick, frameSizePixels));
		System.err.println(m.CoordsToPixel(m.PixelToCoords(mouseClick, frameSizePixels), frameSizePixels));
		paintFigure();
	}
	@Override
	public void mouseReleased(MouseEvent e) {


	}
	@Override
	public void mouseEntered(MouseEvent e) {


	}
	@Override
	public void mouseExited(MouseEvent e) {


	}

	public void paintFigure () {
		if(shouldPaintPacman) 
			mainGame.addPacman(mouseClick, frameSizePixels);
		else {
			mainGame.addFruit(mouseClick, frameSizePixels);
		}
		_panel.repaint();


	}
	///////////////////////////////////////////////////////////////////////////////////
}


