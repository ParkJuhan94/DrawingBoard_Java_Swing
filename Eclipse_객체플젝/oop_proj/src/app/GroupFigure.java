package app;

import java.awt.Graphics;
import java.util.ArrayList;

public class GroupFigure extends Figure{
protected ArrayList<Figure> groupList = new ArrayList<>();
	
	public GroupFigure(int paintType, int color, String str, int x, int y, int w, int h) {
		super(paintType, color, str, x, y, w, h);
	}
	
	public void add(Figure f) {
		groupList.add(f);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawRect(x, y, w, h);
		for(int i = 0 ; i < groupList.size(); i++) {
			groupList.get(i).draw(g);
		}		
	}
	
	@Override
	public void groupMove(int x, int y) {
		for(int i = 0; i < groupList.size(); i++) {
			groupList.get(i).x += x;			
			groupList.get(i).y += y;
		}
	}
}
