package mylib;

import java.awt.event.MouseEvent;

public class KMenu extends KContainer{
	public boolean showMenu = false;
		
	public KMenu(String text) {
		super(text);		
	}
	
	public void toggleMenu() {
		showMenu = !showMenu;
		if(showMenu) {
			((KMenuBar)parent).closeOtherMenu(this);			
		}
		makeChildrenVisible(showMenu);
		repaint();
	}
		
	public void makeChildrenVisible(boolean b) {
		for(KComponent c : compoList) {
			c.visible = b;
		}
	}

	public void addMenuItem(KMenuItem kMenuitem) {
		add(kMenuitem);
		kMenuitem.setParent(this);
	}
	
	@Override
	public void processMouseEvent(MouseEvent e) {
		System.out.println("메뉴");
		switch (e.getID()) {		
		case MouseEvent.MOUSE_CLICKED:
			toggleMenu();
		}				
	}
}