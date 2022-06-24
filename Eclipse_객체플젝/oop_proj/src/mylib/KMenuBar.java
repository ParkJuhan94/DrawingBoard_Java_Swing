package mylib;

import java.awt.event.MouseEvent;

public class KMenuBar extends KContainer{
	public KMenuBar() {}
	
	public KMenuBar(String text) {		
		super(text);		
	}

	public void addMenu(KMenu kMenu) {
		add(kMenu);
		kMenu.setParent(this);		
	}
	
	public void closeOtherMenu(KMenu kMenu) {
		KMenu tmpkMenu = null;
		for(KComponent c : compoList) {
			tmpkMenu = (KMenu)c;			
			if(tmpkMenu != kMenu) {				
				tmpkMenu.makeChildrenVisible(false);
			}
		}					
	}
	
	@Override
	public void processMouseEvent(MouseEvent e) {
		System.out.println("메뉴바");
		
		switch (e.getID()) {		
		case MouseEvent.MOUSE_CLICKED:			
			for(KComponent c : compoList) {
				if(c.findChildRecursive(e.getX(), e.getY()) != null) {					
					c.processMouseEvent(e);	
					break;
				}
			}			
		}				
	}
}
