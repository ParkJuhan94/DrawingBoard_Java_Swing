package mylib;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class KPanel extends KContainer{
	@Override
	public void processMouseEvent(MouseEvent e) {
		System.out.println("패널 위치 y = " + this.y);
		e.translatePoint(x, y);
//		processMouserListeners(e);
		System.out.println("Panel touched " + e.getX() + "' " + e.getY());
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("Drawing KPanel");
	}
}
