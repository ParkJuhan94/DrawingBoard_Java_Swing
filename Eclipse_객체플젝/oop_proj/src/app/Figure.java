package app;

import java.awt.Color;
import java.awt.Graphics;

public class Figure {
	public int x, y, w, h, color, paintType, figType = 0;
	protected String str;	
	
	public Figure(int paintType, int color, String str, int x, int y, int w, int h) {
		this.paintType = paintType;
		this.color = color;		
		this.str = str;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	// event 하고 그림이 남아있지 않음
	public void draw(Graphics g) {
		if(color == 1) {
			g.setColor(Color.BLACK);	
		}else if(color == 2) {
			g.setColor(Color.RED);			
		}else if(color == 3) {
			g.setColor(Color.BLUE);
		}
	}
	
	public void groupMove(int x, int y) {
		//	empty
	}
}