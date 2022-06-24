package mylib;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KComponent {
	public int x, y, width, height;
	public String text = "";
	public ArrayList<KMouseListener> mlisteners = new ArrayList<>(); 
	public KContainer parent = null;
	public boolean isTop = false;
	public boolean visible = true;	
	
	public KComponent() {}
	
	public KComponent(String text) {
		this.text = text;
	}
	
	public boolean contains(int x, int y) {
		return this.x < x && x < this.x + width 
				&& this.y < y && y < this.y + height;
	}
	
	public void paint(Graphics g) {
		if(visible) {
			g.drawRect(x, y, width, height);
			g.drawString(text, x + 15, y + 17);	
		}		
	}
	
	public void setBounds(int i, int j, int width, int height) {
		this.x = i;
		this.y = j;
		this.width = width;
		this.height = height;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setParent(KContainer parent) {
		this.parent = parent;
	}
	
	public KContainer getParent() {
		return this.parent;
	}		
	
	public KContainer getFrame() {
		if(parent != null) {
			return parent.getFrame();
		}
		else return (KFrame)this;
	}
	
	public void processMouseEvent(MouseEvent e) {
		processMouserListeners(e);
	}

	public void processMouserListeners(MouseEvent e) {
		if(!mlisteners.isEmpty()) {
			for(KMouseListener l : mlisteners) {
				switch(e.getID()) {
				case MouseEvent.MOUSE_PRESSED:
					l.mousePressed(e); break;
				case MouseEvent.MOUSE_RELEASED:
					l.mousePressed(e); break;
				case MouseEvent.MOUSE_CLICKED:
					l.mousePressed(e); break;
				}
			}
		}
	}
	
	public void repaint() {
		((KFrame)getFrame()).realFrame.repaint();
	}
	
	public void toggleVisiblity() {
		visible = !visible;
	}
	

	public KComponent findChildRecursive(int x2, int y2) {
		if(contains(x2, y2)) {
			return this;
		}
		return null;
	}	
	
}
