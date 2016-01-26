import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import me.daniel.dwtk.WidgetManager;
import me.daniel.dwtk.widgets.Button;
import me.daniel.dwtk.widgets.TextBox;
import me.daniel.dwtk.widgets.events.WidgetEvent;
import me.daniel.dwtk.widgets.events.WidgetListener;

public class Example extends Canvas implements Runnable {
	private static final long serialVersionUID = -6461257018929905314L;
	private WidgetManager wm;

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		wm.paint(g);
	}
	
	public Example() {
		Thread t = new Thread(this);
		t.start();
		wm = new WidgetManager(this);
		final TextBox tbox = wm.newTextBox("", 0, true, 10, 10, 400, 200);
		tbox.setTextColor(Color.YELLOW);
		Button button = wm.newButton("My button", 1, true, 10, 220, 100, 30);
		button.addListener(new WidgetListener() {
			
			@Override
			public void run(WidgetEvent e) {
				switch(e.getEventType()) {
				case CLICK:
					tbox.setTextColor((tbox.getTextColor().equals(Color.YELLOW)? Color.CYAN : Color.YELLOW));
					break;
				case MOUSEMOVE:
					tbox.setText(tbox.getText() + ".");
					break;
				}
			}
		});
	}
	
	//Allows double buffering. Not needed for DWTK, but greatly improves visual quality.
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
