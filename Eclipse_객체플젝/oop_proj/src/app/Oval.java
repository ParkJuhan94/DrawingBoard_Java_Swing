package app;

import java.awt.Graphics;

class Oval extends Figure {

	public Oval(int paintType, int color, String str, int x, int y, int w, int h) {
		super(paintType, color, str, x, y, w, h);
		figType = 2;		
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if(paintType == 0) {
			g.drawOval(x, y, w, h);	
		}else if(paintType == 1){
			g.fillOval(x, y, w, h);
		}
	}
}