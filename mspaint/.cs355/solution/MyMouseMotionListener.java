package cs355.solution;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class MyMouseMotionListener implements java.awt.event.MouseMotionListener {

	private MyCS355Controller contr;
	
	public MyMouseMotionListener(MyCS355Controller c) {
		contr = c;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
//		System.out.println("Dragged");
		Point2D e = new Point2D.Double(arg0.getX(), arg0.getY());
		contr.DrawpadDraggedReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}
