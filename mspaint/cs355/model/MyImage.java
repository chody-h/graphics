package cs355.model;

public class MyImage {
	
	int h = 0;
	int w = 0;
	int [][] original;

	public MyImage() {
		// TODO Auto-generated constructor stub
	}
	
	public int GetHeight() {
		return h;
	}
	
	public int GetWidth() {
		return w;
	}
	
	public int[][] GetPixels() {
		return original;
	}
	
	public void SetHeight(int height) {
		h = height;
	}
	
	public void SetWidth(int width) {
		w = width;
	}
	
	public void SetPixels(int[][] pixels) {
		original = pixels;
	}

}
