package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Button;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpenFile {

	private JFrame frame;
	TextField textField;
	String path = "";
	
	public JFrame getFrame() {
		return frame;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpenFile window = new OpenFile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getFileString() {
		return path;
	}

	/**
	 * Create the application.
	 */
	public OpenFile() {
		getFile();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void getFile() {
		frame = new JFrame();
		frame.setBounds(100, 100, 455, 102);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new TextField();
		frame.getContentPane().add(textField, BorderLayout.SOUTH);
		
		Button button = new Button("Open");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				path = textField.getText();
				frame.setVisible(false);
				System.out.println("action");
			}
		});
		frame.getContentPane().add(button, BorderLayout.CENTER);
	}

}
