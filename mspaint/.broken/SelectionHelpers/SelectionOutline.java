package cs355.SelectionHelpers;

public class SelectionOutline extends DrawnSelectionItem {
	// width
	private double w;
	// height
	private double h;
	// T - oval, F - rectangle
	private boolean o;
	
	public SelectionOutline(double width, double height, boolean isOval) {
		w = width;
		h = height;
		o = isOval;
	}
	
	public double GetWidth() {
		return w;
	}
	
	public double GetHeight() {
		return h;
	}
	
	public boolean IsOval() {
		return o;
	}
}
