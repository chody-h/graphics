package cs355.model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class MyImage {
	
	int h = 0;
	int w = 0;
	int [][] original;

	public MyImage(BufferedImage b) {
		h = b.getHeight();
		w = b.getWidth();
		original = new int[h][w];
		WritableRaster r = b.getRaster();
		for (int h = 0; h < this.h; h++) {
			for (int w = 0; w < this.w; w++) {
				original[h][w] = r.getSample(w, h, 1);
			}
		}
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
