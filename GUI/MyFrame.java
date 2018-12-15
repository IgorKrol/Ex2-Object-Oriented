package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Resourses.Map;
import java.awt.Color;

public class MyFrame extends JFrame {

	private JPanel contentPane;
	private Map ariel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyFrame() {
//		String path = "F:\\Works\\Ex2ObjectOriented\\src\\Resourses\\Ariel1.png";
		ariel = new Map();
		String path = ariel.getFile().getAbsolutePath();
		System.out.println(path);
		Image img = new ImageIcon(path).getImage();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanelWithBackground(img);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(0,0,2000));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);
	}

}
