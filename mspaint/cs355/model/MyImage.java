package cs355.model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;

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
	
	public void MedianBlur() {
		int[][] calculated = new int[h][w];
		for (int h = 0; h < this.h; h++) {
			for (int w = 0; w < this.w; w++) {
				ArrayList<Integer> neighbors = new ArrayList<Integer>();
				for (int i = -1; i < 2; i++) {
					int row_idx = h + i;
					if (row_idx >= this.h || row_idx < 0) {
						neighbors.add(0);
						continue;
					}
					for (int j = -1; j < 2; j++) {
						int col_idx = w + j;
						if (col_idx >= this.w || col_idx < 0) {
							neighbors.add(0);
							continue;
						}
						neighbors.add(original[row_idx][col_idx]);
					}
				}
				Collections.sort(neighbors);
				int middle = neighbors.size()/2;
				int median;
				if (middle % 2 == 1) {
					median = neighbors.get(middle);
				}
				else {
					median = (neighbors.get(middle-1)+neighbors.get(middle))/2;
				}
				calculated[h][w] = median;
			}
		}
		original = calculated;
	}
	
	public void UniformBlur() {
		int[][] calculated = new int[h][w];
		for (int h = 0; h < this.h; h++) {
			for (int w = 0; w < this.w; w++) {
				float sum = 0;
				float count = 0;
				for (int i = -1; i < 2; i++) {
					int row_idx = h + i;
					if (row_idx >= this.h || row_idx < 0) {
						count++;
						continue;
					}
					for (int j = -1; j < 2; j++) {
						int col_idx = w + j;
						if (col_idx >= this.w || col_idx < 0) {
							count++;
							continue;
						}
						sum += original[row_idx][col_idx];
						count += 1;
					}
				}
				calculated[h][w] = Math.round(sum/count);
			}
		}
		original = calculated;
	}
	
	public void Contrastify(int change) {
		for (int h = 0; h < this.h; h++) {
			for (int w = 0; w < this.w; w++) {
				int val = (int) (Math.pow((change+100.0)/100.0, 4) * (original[h][w]-128) + 128);
				original[h][w] = (val > 255) ? 255 : val;
				original[h][w] = (val < 0) ? 0 : original[h][w];
			}
		}
	}
	
	public void Brighten(int change) {
		for (int h = 0; h < this.h; h++) {
			for (int w = 0; w < this.w; w++) {
				int val = original[h][w] + change;
				original[h][w] = (val > 255) ? 255 : val;
				original[h][w] = (val < 0) ? 0 : original[h][w];
			}
		}
	}
	
	public BufferedImage GetImage() {
		BufferedImage b = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster r = b.getRaster();		
		for (int h = 0; h < this.h; h++) {
			for (int w = 0; w < this.w; w++) {
				r.setSample(w, h, 0, original[h][w]);
			}
		}
		return b;
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
