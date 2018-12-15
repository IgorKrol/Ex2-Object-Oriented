package GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Resourses.Map;

public class MyFrameT extends JFrame implements MouseListener {
	
	private Map ariel = new Map();
	private JFrame frame;
	private Container window;
	private JPanel _panel;
	private Graphics _paper;
	private int x, y;
	private boolean pacMan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrameT window = new MyFrameT();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyFrameT() {
		initialize();
	}
	
	public void makeMap() {
		window = this.getContentPane();
		window.setLayout(new FlowLayout());
		ImageIcon g = new ImageIcon(ariel.getFile().getPath());
		Image img = g.getImage();
		
		_panel = new JPanelWithBackground(img);
		Dimension d= new Dimension(1433,642);
		_panel.setPreferredSize(d);
		
		
		
//		g.
//		_panel.paintComponents(g.getImage().getGraphics());
//		_panel.paint(g.getImage().getGraphics());
		window.add(_panel);

	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1433, 642);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
