package GUI;



import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.*;

import GameComponents.Fruit;
import GameComponents.Pacman;
import Geom.Point2D;

/**
 *
 * 
 */
public class testMap extends JFrame implements MouseListener {
	//VARIABLES
	private Container window;
	private JPanel _panel;
	private Graphics _paper;
	private int x, y;
	private boolean pacMan;
//	
//	public class JPanelBG extends JPanel{
//		@Override
//		public void paint(Graphics g) {
//			super.paintComponent(g);
//			int w = _panel.getWidth();
//			int h = _panel.getHeight();
//			Image img = mapImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
////			System.out.println("Paint");
//			g.drawImage(img, 0, 0, null);
//			Point2D frameSizePixels = new Point2D(getWidth(), getHeight());
//			List <Pacman> pacmansList = mainGame.getPacmans();
//			g.setColor(Color.YELLOW);
//			for (Pacman pacman : pacmansList) {
//				Point2D pacPixels = m.CoordsToPixel(pacman.getCoords(), frameSizePixels);
//				System.out.println("Pac" + pacPixels);
//				g.fillOval((int)pacPixels.x(), (int)pacPixels.y(), 15, 15);
//			}
//			List <Fruit> fruitsList = mainGame.getFruits();
//			g.setColor(Color.RED);
//			for (Fruit fruit : fruitsList) {
//				Point2D fruPixels = m.CoordsToPixel(fruit.getCoords(), frameSizePixels);
//				System.out.println("Fru" + fruPixels);
//				g.fillOval((int)fruPixels.x(), (int)fruPixels.y(), 15, 15);
//			}
////			paint = this.getGraphics();
//		}
//	};

	//INITIATOR
	public testMap(){
		super("Ariel Map"); //setTitle("Map Counter");  // "super" Frame sets its title
		//	Moves and resizes this component. 
		//	The new location of the top-left corner is  specified by x and y, 
		//	and the new size is specified by width and height
		//	setBounds(x,y,width,height)
		this.setBounds(0,0,1433,642);
		
		// "super" Frame sets its initial window size
		//      Exit the program when the close-window button clicked
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

	}
	public void createGui(){              				
		//	A Container is a component  that can contain other GUI components
		window = this.getContentPane(); 
		window.setLayout(new FlowLayout());

		//	Add "panel" to be used for drawing            
		_panel = new JPanel();
		Dimension d= new Dimension(1433,642);
		_panel.setPreferredSize(d);		               
		window.add(_panel);

		// A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
		JMenuBar menuBar;   // the menu-bar
		JMenu menu;         // each menu in the menu-bar
		JMenuItem menuItem1, menuItem2; // an item in a menu

		menuBar = new JMenuBar();

		// First Menu
		menu = new JMenu("ADD");
		menu.setMnemonic(KeyEvent.VK_A);  // alt short-cut key
		menuBar.add(menu);  // the menu-bar adds this menu

		menuItem1 = new JMenuItem("pacman", KeyEvent.VK_F);
		menu.add(menuItem1); // the menu adds this item
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pacMan = true;
			}
		});
		menuItem2 = new JMenuItem("fruits", KeyEvent.VK_S);
		menu.add(menuItem2); // the menu adds this item
		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pacMan = false;
			}
		});                     

		setJMenuBar(menuBar);  // "this" JFrame sets its menu-bar

		// Prepare an ImageIcon
		String imgMapFilename = "Ariel1.png";
		
		ImageIcon imgBck = new ImageIcon(getClass().getResource(imgMapFilename));
		
		JLabel labelMap = new JLabel();
		labelMap.setIcon(imgBck);
		_panel.add(labelMap);

		// panel (source) fires the MouseEvent.
		//panel adds "this" object as a MouseEvent listener.
		_panel.addMouseListener(this);
		
		window.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		    	
		    	Image newImage = imgBck.getImage().getScaledInstance(_panel.getWidth(),_panel.getHeight(), Image.SCALE_DEFAULT);
		    	ImageIcon newIcon = new ImageIcon(newImage);
		    	labelMap.setIcon(newIcon);
//		    	_panel.add(labelMap);
		    	
		    	// do stuff
		    }
		});
	}

	protected void paintElement() {
		
		//	The method getGraphics is called to obtain a Graphics object
		_paper = _panel.getGraphics();
		if(pacMan){
			_paper.setColor(Color.YELLOW);
			_paper.fillOval(x,y,20,20);
		} else {
			_paper.setColor(Color.RED);
			_paper.fillOval(x,y,10,10);
		}
		_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
		_paper.drawString("("+Integer.toString(x)+", "+Integer.toString(y)+")",x,y-10);
	}
	
	//	public void mouseClicked(MouseEvent event){
	@Override
	public void mousePressed(MouseEvent event) {
		x = event.getX();
		y = event.getY();  
		paintElement();
	}
	// Not Used, but need to provide an empty body for compilation
	public void mouseReleased(MouseEvent event){}
	public void mouseClicked(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	
	public static void main(String[] args) {
		testMap frame = new testMap();
//		frame.setContentPane(new JLabel(new ImageIcon("Ariel1.png")));
		frame.setBounds(0, 0, 1433, 642);
		frame.createGui();
		frame.setVisible(true);
	}
}
