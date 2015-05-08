package cs355.SelectionHelpers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

public class DrawnSelectionItem {

	protected Color c = new Color(0, 255, 255);	// cyan;
	protected Stroke s = new BasicStroke(1);
	
	public void SetColor(Color color) {
		c = color;
	}
	
	public Color GetColor() {
		return c;
	}
	
	public void SetStroke(Stroke stroke) {
		s = stroke;
	}
	
	public Stroke GetStroke() {
		return s;
	}
}
