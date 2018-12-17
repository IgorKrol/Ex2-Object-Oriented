package GUI;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import GameComponents.Game;
import Resourses.Map;

public class MyFrame extends JFrame{

	private OpenFile openfileframe;
	private Game mainGame;
	private JPanel _panel;
	private BufferedImage mapImage;
	private int mapImageWidth, mapImageHeight;
	private File mapFile;
	private static Dimension d = new Dimension(400, 250);
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu AddMenu;

	public MyFrame() {
		initFrame();
	}
	/**
	 * Frame initiator: initiate image buffer, menu, panel
	 */
	public void initFrame() {
		this.setPreferredSize(d);
		try {
			//ImageINITIALIZER
			Map m = new Map();
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

	}
	/**
	 * Create Main options menu
	 */
	public void createMenu() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("FILE");
		AddMenu = new JMenu("ADD");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		createFileMenu();
		createAddMenu();
		this.setJMenuBar(menuBar);
		System.out.println("add fileMenu and menu bar");


	}

	/**
	 * Create file menu
	 */
	public void createFileMenu() {
		JMenuItem open = new JMenuItem("Open File");
		JMenuItem save = new JMenuItem("Save File");
		JTextArea jTextArea1 = null;
		
		//OPEN FILE PLATFORM
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {                                 
				JFileChooser chooser= new JFileChooser();
				chooser.setCurrentDirectory(new File("c:\\temp"));
				chooser.setFileFilter(new FileNameExtensionFilter("csv","CSV"));
				int value = chooser.showOpenDialog(null);
				File f= chooser.getSelectedFile();
				String filename= f.getAbsolutePath();

				try{
					FileReader reader = new FileReader(filename);
					BufferedReader br = new BufferedReader(reader);
					jTextArea1.read(br,null);
					br.close();
					jTextArea1.requestFocus();
				}catch(Exception em){
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});

		fileMenu.add(open);
		fileMenu.add(save);
		menuBar.add(fileMenu);
	}

	/**
	 * Create Add menu
	 */
	public void createAddMenu() {
		JMenuItem addPacman = new JMenuItem("Add Pacman");
		JMenuItem addFruit = new JMenuItem("Add Fruit");

		AddMenu.add(addPacman);
		AddMenu.add(addFruit);
		menuBar.add(AddMenu);
	}
	/**
	 * Create Main Panel with map image on it
	 */
	public void createPanel() {
		_panel = new JPanelBG();
		_panel.setLayout(new BorderLayout());

		System.out.println("add panel");

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
			super.paint(g);
			int w = _panel.getWidth();
			int h = _panel.getHeight();
			Image img = mapImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);

			g.drawImage(img, 0, 0, null);
		}
	};


	public static void main(String[] args) {
		MyFrame mainGame = new MyFrame();
		mainGame.setSize(d);
		mainGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGame.setVisible(true);
	}

}
