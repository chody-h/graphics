package cs355.model;

import java.awt.Color;
import java.awt.Point;

public class MyRectangle extends MyShape {
//	location of height, width, & accessors
	private int w;
	private int h;
	
	public MyRectangle(Color color, Point center) {
		super(color, center);
		w = 0;
		h = 0;
	}
	
	public void Update(Point center, int width, int height) {
		SetCenter(center);
		w = width;
		h = height;
	}

	public void SetWidth(int width) {
		w = width;
	}

	public void SetHeight(int height) {
		h = height;
	}
	
	public int GetWidth() {
		return w;
	}
	
	public int GetHeight() {
		return h;
	}
}
