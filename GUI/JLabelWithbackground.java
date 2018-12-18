package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;

public class JLabelWithbackground extends JLabel {
	Image imageOrg = null; 
	Image image = null; 

	{ 
		addComponentListener(new ComponentAdapter() { 
			public void componentResized(ComponentEvent e) { 
				int w = JLabelWithbackground.this.getWidth(); 
				int h = JLabelWithbackground.this.getHeight(); 
				image = w>0&&h>0?imageOrg.getScaledInstance(w,h,java.awt.Image.SCALE_SMOOTH):imageOrg; 
				JLabelWithbackground.this.repaint(); 
			} 
		}); 
	} 
	public JLabelWithbackground(Image i) { 
		imageOrg=i; 
		image=i; 
		setOpaque(false); 
	} 
	private void addComponentListener(ComponentAdapter componentAdapter) {
		// TODO Auto-generated method stub
		
	}
	public void paint(Graphics g) { 
		if (image!=null) g.drawImage(image, 0, 0, null); 
		super.paint(g); 
	} 
	
}
