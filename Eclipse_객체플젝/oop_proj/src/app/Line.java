package app;

import java.awt.Graphics;

class Line extends Figure {

	public Line(int paintType, int color, String str, int x, int y, int w, int h) {
		super(paintType, color, str, x, y, w, h);
		figType = 3;
	}


	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawLine(x, y, w, h);
	}
}