package cs355.solution;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class MyMouseMotionListener implements java.awt.event.MouseMotionListener {

	private MyCS355Controller contr;
	
	public MyMouseMotionListener(MyCS355Controller c) {
		contr = c;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Point e = new Point(arg0.getX(), arg0.getY());
		contr.UpdateShape(e);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
