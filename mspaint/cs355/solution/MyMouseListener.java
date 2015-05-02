package cs355.solution;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class MyMouseListener implements java.awt.event.MouseListener {
	
	private MyCS355Controller contr;
	
	public MyMouseListener(MyCS355Controller c) {
		contr = c;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
//		System.out.println("Clicked");
		mousePressed(arg0);
		mouseReleased(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
//		mouseReleased(arg0);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		Point s = new Point(arg0.getX(), arg0.getY());
		contr.DrawShape(s);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		Point e = new Point(arg0.getX(), arg0.getY());
		contr.UpdateShape(e);
	}

}
