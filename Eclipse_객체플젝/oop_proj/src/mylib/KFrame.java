package mylib;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class KFrame extends KContainer{
	protected KMenuBar theMenuBar;
	protected KToolBar theToolBar;
	protected KContainer contentPane;
	protected JFrame realFrame;
	
	public KFrame() {
		isTop = true;
	}
	
	public void setRealFrame(JFrame jf) {
		realFrame = jf;
	}
	
	public JFrame getRealFrame() {
		return realFrame;
	}

	public void setKToolBar(KToolBar tb) {	
		add(tb);
		theToolBar = tb;			
	} 
	
	public void setKMenuBar(KMenuBar mb) {
		// 원래 메뉴바는 frame에 있는게 정상이지만...
		add(mb);
		theMenuBar = mb;			
	} 
	
	public void setKContentPane(KContainer c) {
		//툴바가 있으면 툴바가 1, contentpane을 2로 하면 된다.
		add(c);
		contentPane = c;
		c.setBounds(0, theMenuBar.y + theMenuBar.height + theToolBar.y + theToolBar.height, 
				realFrame.getWidth(), realFrame.getHeight());
	}
}
