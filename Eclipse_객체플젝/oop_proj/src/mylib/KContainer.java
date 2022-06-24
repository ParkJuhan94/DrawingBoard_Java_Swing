package mylib;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KContainer extends KComponent{
	public ArrayList<KComponent> compoList = new ArrayList<>();
	public String text;		
	
	public KContainer() {}
	
	public KContainer(String text) {
		super(text);
	}

	public void add(KComponent c) {
		compoList.add(c);
		c.setParent(this);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		paintChildren(g);
	}
	
	public void paintChildren(Graphics g) {
		for(KComponent k : compoList) {
			k.paint(g);
		}
	}
	
	@Override
	public void processMouseEvent(MouseEvent e) {
		//	Container이니까 먼저 child에 적용되는 마우스 이벤트인지 체크
		KComponent kc = findChildRecursive(e.getX(), e.getY());
		
		if(kc != null) {
			kc.processMouseEvent(e);
			return;
		}
		// contains(x,y) 인가 ??
		if(contains(e.getX(), e.getY())) {
			super.processMouseEvent(e);	//	KComponent의 메소드를 호출
		}
	}
	
	@Override
	public KComponent findChildRecursive(int x, int y) {
		// 먼저 자식 찾기 
		for(KComponent k : compoList) {
			KComponent found = k.findChildRecursive(x, y);
			if(found != null)
					return found;
		}
		
		// 자식 없으면 자기 자신을 반환
		if(visible && contains(x, y))
			return this;
		
		return null;
	}		
}