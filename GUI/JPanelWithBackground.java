package GUI;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Resourses.Map;
/**
 * Code Taken from stackoverflow
 *
 *
 */
public class JPanelWithBackground extends JPanel { 
	Image imageOrg = null; 
	Image image = null; 

	{ 
		addComponentListener(new ComponentAdapter() { 
			public void componentResized(ComponentEvent e) { 
				int w = JPanelWithBackground.this.getWidth(); 
				int h = JPanelWithBackground.this.getHeight(); 
				image = w>0&&h>0?imageOrg.getScaledInstance(w,h,java.awt.Image.SCALE_SMOOTH):imageOrg; 
				JPanelWithBackground.this.repaint(); 
			} 
		}); 
	} 
	public JPanelWithBackground(Image i) { 
		imageOrg=i; 
		image=i; 
		setOpaque(false); 
	} 
	public void paint(Graphics g) { 
		if (image!=null) g.drawImage(image, 0, 0, null); 
		super.paint(g); 
	} 
	
//	public static void main(String args[]) {
//
//		ImageIcon g = new ImageIcon(ariel.getFile().getPath());
//		Image image = g.getImage();
//		JFrame f = new JFrame(""); 
//		JPanel j = new JPanelWithBackground(image); 
//		j.setLayout(new FlowLayout()); 
//		j.add(new JButton("YoYo")); 
//		j.add(new JButton("MaMa")); 
//		f.add(j); 
//		f.setVisible(true); 
//	}
} 


