package app;

import java.awt.Graphics;

class Rect extends Figure {

	public Rect(int paintType, int color, String str, int x, int y, int w, int h) {
		super(paintType, color, str, x, y, w, h);
		figType = 1;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if(paintType == 0) {
			g.drawRect(x, y, w, h);	
		}else if(paintType == 1){
			g.fillRect(x, y, w, h);
		}
		
	}
}