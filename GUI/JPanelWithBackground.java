package GUI;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Resourses.Map;
/**
 * JPanel with map as background
 * @author
 *
 */
public class JPanelWithBackground extends JPanel { 

	BufferedImage image;
	BufferedImage imageOrg;

	{ 
		addComponentListener(new ComponentAdapter() { 
			public void componentResized(ComponentEvent e) { 
				
				JPanelWithBackground.this.repaint(); 
			} 
		}); 
	} 

	public JPanelWithBackground(File i) { 
		try {
			imageOrg = ImageIO.read(i);
			image = ImageIO.read(i);
		}
		catch (IOException e) {
			System.err.println("IMAGEIO ERROR: JPANELWITHBACKGROUND");
			e.printStackTrace();
		}  
		setOpaque(false); 
	} 
	public void paint(Graphics g) { 
		int w = JPanelWithBackground.this.getWidth(); 
		int h = JPanelWithBackground.this.getHeight(); 
		if (w>0&&h>0) {
			Graphics2D img = image.createGraphics();
			Image reImage = imageOrg.getScaledInstance(w,h,java.awt.Image.SCALE_SMOOTH);
			img.drawImage(reImage,0,0,JPanelWithBackground.this);
			img.dispose();
		}
		else {
			image = imageOrg;
		}
		if (image!=null) g.drawImage(image, 0, 0, null); 
		super.paint(g); 
	} 

} 


