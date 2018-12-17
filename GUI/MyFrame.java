package GUI;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import Resourses.Map;

public class MyFrame extends JFrame{

	private JPanel _panel;
	private BufferedImage mapImage;
	private int mapImageWidth, mapImageHeight;
	private File mapFile;
	private static Dimension d = new Dimension(400, 250);
	private JMenuBar menuBar;
	private JMenu fileMenu;

	public MyFrame() {
		initFrame();
	}

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

	public void createMenu() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("FILE");
		fileMenu.setMnemonic(KeyEvent.VK_A);

		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);
		System.out.println("add fileMenu and menu bar");


	}

	public void createPanel() {
		_panel = new JPanelBG();
		_panel.setLayout(new BorderLayout());

		System.out.println("add panel");

		this.add(_panel);
	}
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
