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
	private int mapImageWidth, mapImageHeight;
	private File mapFile;
	private static Dimension d = new Dimension(400, 250);
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu AddMenu;
	private JMenu AlgoMenu;
	private Point2D mouseClick;
	private boolean shouldPaintPacman;
	private ShortestPathAlgo SPA;

	public MyFrame() {
		initFrame();
	}
	/**
	 * Frame initiator: initiate image buffer, menu, panel
	 */
	public void initFrame() {
		m = new Map();
		mainGame = new Game();
		this.setPreferredSize(d);
		try {
			//ImageINITIALIZER
			mapFile = m.getFile();
			mapImage = ImageIO.read(mapFile);
			mapImageWidth = mapImage.getWidth();
			mapImageHeight = mapImage.getHeight();
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
		//		JTextField jText1 = new JTextField();


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
		//////////////////////////////////////////////////////////////////////////////////////
		fileMenu.add(open);
		fileMenu.add(save);
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
		
		findShortPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SPA = new ShortestPathAlgo(mainGame);
				Iterator<Pacman> pac = mainGame.getPacmans().iterator();
				
				while(pac.hasNext()) {
					Pacman pacman = pac.next();
					Iterator<Point3D> path = pacman.getPath().iterator();
					while(path.hasNext()) {
						Point3D move = path.next();
						System.out.println(move.toString() + "\n");
						
					}
					System.out.println("ended a path\n");
				}
					
			}
		});
		
		AlgoMenu.add(findShortPath);
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
			int w = _panel.getWidth();
			int h = _panel.getHeight();
			Image img = mapImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
//			System.out.println("Paint");
			g.drawImage(img, 0, 0, null);
			Point2D frameSizePixels = new Point2D(getWidth(), getHeight());
			List <Pacman> pacmansList = mainGame.getPacmans();
			g.setColor(Color.YELLOW);
			for (Pacman pacman : pacmansList) {
				Point2D pacPixels = m.CoordsToPixel(pacman.getCoords(), frameSizePixels);
//				System.out.println("Pac " + pacPixels + " //// Coords:" + pacman.getCoords());
				g.fillOval((int)pacPixels.x(), (int)pacPixels.y(), 20, 20);
			}
			List <Fruit> fruitsList = mainGame.getFruits();
			g.setColor(Color.RED);
			for (Fruit fruit : fruitsList) {
				Point2D fruPixels = m.CoordsToPixel(fruit.getCoords(), frameSizePixels);
//				System.out.println("Fru " + fruPixels);
				g.fillOval((int)fruPixels.x(), (int)fruPixels.y(), 15, 15);
			}
			repaint();
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
		System.out.println(mouseClick);
//		Point2D frameSizePixels = new Point2D(getWidth(), getHeight());
//		Point3D ppp =m.PixelToCoords(mouseClick, frameSizePixels);
//		System.out.println(mouseClick.toString() + "------>"+ m.CoordsToPixel(new Point3D (ppp.x(),ppp.y()),frameSizePixels));
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
		Point2D frameSizePixels = new Point2D(getWidth(), getHeight());
		System.out.println(frameSizePixels);
		if(shouldPaintPacman) 
			mainGame.addPacman(mouseClick, frameSizePixels);
		else 
			mainGame.addFruit(mouseClick, frameSizePixels);
		_panel.repaint();
		System.out.println(mainGame.getPacmans().toString());

	}
	///////////////////////////////////////////////////////////////////////////////////
}


