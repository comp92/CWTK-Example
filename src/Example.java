import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import me.daniel.cwtk.WidgetManager;

public class Example extends Canvas implements Runnable {
	private static final long serialVersionUID = -6461257018929905314L;
	private WidgetManager wm;

	public void paint(Graphics g) {
		g.drawString("Test", 100, 100);
		wm.paint(g);
	}
	
	public Example() {
		Thread t = new Thread(this);
		t.start();
		wm = new WidgetManager(this);
		wm.newTextBox("Test", 0, true, 10, 10, 400, 200);
	}
	
	public void update(Graphics g) {               
		Graphics offgc;                            
	    Image offscreen = null;                    
	    @SuppressWarnings("deprecation")           
		Dimension d = size();                      
	    offscreen = createImage(d.width, d.height);
	    offgc = offscreen.getGraphics();           
	    offgc.setColor(getBackground());           
	    offgc.fillRect(0, 0, d.width, d.height);   
	    offgc.setColor(getForeground());           
	    paint(offgc);                              
	    g.drawImage(offscreen, 0, 0, this);        
	}                                              
	
	public void run() {
		while(true) {
			repaint();
			try {
				Thread.sleep(17);
			} catch(InterruptedException e) {}
		}
	}
}
