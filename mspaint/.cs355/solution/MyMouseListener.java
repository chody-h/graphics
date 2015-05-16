package cs355.solution;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class MyMouseListener implements java.awt.event.MouseListener {
	
	private MyCS355Controller contr;
	
	public MyMouseListener(MyCS355Controller c) {
		contr = c;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
//		System.out.println("Clicked");
//		Point s = new Point(arg0.getX(), arg0.getY());
//		contr.DrawShape(s);
//		contr.UpdateShape(s);
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
		Point2D s = new Point2D.Double(arg0.getX(), arg0.getY());
		contr.DrawpadPressed(s);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		Point2D e = new Point2D.Double(arg0.getX(), arg0.getY());
		contr.DrawpadDraggedReleased(e);
	}

}
