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
	
	public void EdgeDetect() {
		int[][] calculated = new int[h][w];
		int h;
		int w;
		
		for (h = 1; h < this.h-1; h++) {
			for (w = 1; w < this.w-1; w++) {
				double value_x = 0;
				double value_y = 0;

				int val = original[h-1][w-1];
				value_x += val*-1;
				value_y += val*-1;

				val = original[h][w-1];
				value_x += val*-2;
				value_y += val*0;

				val = original[h+1][w-1];
				value_x += val*-1;
				value_y += val*1;


				val = original[h-1][w];
				value_x += val*0;
				value_y += val*-2;

				val = original[h][w];
				value_x += val*0;
				value_y += val*0;

				val = original[h+1][w];
				value_x += val*0;
				value_y += val*2;


				val = original[h-1][w+1];
				value_x += val*1;
				value_y += val*-1;

				val = original[h][w+1];
				value_x += val*2;
				value_y += val*0;

				val = original[h+1][w+1];
				value_x += val*1;
				value_y += val*1;

				value_x /= 8.0;
				value_y /= 8.0;
				value_x = Math.pow(value_x, 2);
				value_y = Math.pow(value_y, 2);
				calculated[h][w] = Math.round((float)(Math.pow(value_x+value_y, 0.5)));
			}
		}
		original = calculated;
	}
	
	public void Sharpen(int A) {
		int[][] calculated = new int[h][w];
		for (int h = 0; h < this.h; h++) {
			for (int w = 0; w < this.w; w++) {
				double value = original[h][w] * (A+4);
				if (w-1 >= 0) {
					value += original[h][w-1] * (-1);
				}
				if (w+1 < this.w) {
					value += original[h][w+1] * (-1);
				}
				if (h-1 >= 0) {
					value += original[h-1][w] * (-1);
				}
				if (h+1 < this.h) {
					value += original[h+1][w] * (-1);
				}
				value /= A;
				if (value < 0) value = 0;
				if (value > 255) value = 255;
				calculated[h][w] = Math.round((float)value);
			}
		}
		original = calculated;
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
				if (val > 255) val = 255;
				else if (val < 0) val = 0;
				original[h][w] = val;
			}
		}
	}
	
	public void Brighten(int change) {
		for (int h = 0; h < this.h; h++) {
			for (int w = 0; w < this.w; w++) {
				int val = original[h][w] + change;
				if (val > 255) val = 255;
				else if (val < 0) val = 0;
				original[h][w] = val;
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
