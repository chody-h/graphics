package cs355.model;

import java.awt.Color;

// 	generic shape class
public class MyShape {
//	color
//	accessor methods for color
	private Color c;
	
	public MyShape(Color color) {
		c = color;
	}
	
	public Color GetColor() {
		return c;
	}
}
