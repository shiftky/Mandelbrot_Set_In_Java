package mandelbrot;

import java.awt.Color;

public class ColorPalette {
	private int current_palette = 0; 
	private boolean smoothing = false;
	private Color[][] colors;
	private static final int[][][] colpal = {
		{ { 12, 0, 0, 64 }, { 12, 0, 0, 255 }, { 10, 0, 255, 255 },
		  { 12, 128, 255, 255 }, { 14, 64, 128, 255 } },
		{ { 12, 0, 10, 20 }, { 12, 50, 100, 240 }, { 12, 20, 3, 26 },
		  { 12, 230, 60, 20 }, { 12, 25, 10, 9 },
		  { 12, 230, 170, 0 }, { 12, 20, 40, 10 }, { 12, 0, 100, 0 },
		  { 12, 5, 10, 10 }, { 12, 210, 70, 30 }, { 12, 90, 0, 50 },
		  { 12, 180, 90, 120 }, { 12, 0, 20, 40 },{ 12, 30, 70, 200 } },
		{ { 10, 70, 0, 20 }, { 10, 100, 0, 100 },
		  { 14, 255, 0, 0 }, { 10, 255, 200, 0 } },
		{ { 8, 40, 70, 10 }, { 9, 40, 170, 10 },
		  { 6, 100, 255, 70 }, { 8, 255, 255, 255 } },
		{ { 16, 0, 0, 0 }, { 32, 255, 255, 255 } } };
	
	public ColorPalette(int c, boolean s) {
		current_palette = c;
		smoothing = s;
		initColorPalettes();
	}

	public Color color(int count) {
		if ( count == -1 ) {
			return Color.black;
		}
		Color color = getColor(count / 256);
		if (smoothing){
			Color color2 = getColor(count / 256 - 1);
			int k1 = count % 256;
			int k2 = 255 - k1;
			int red = (k1 * color.getRed() + k2 * color2.getRed()) / 255;
			int green = (k1 * color.getGreen() + k2 * color2.getGreen()) / 255;
			int blue = (k1 * color.getBlue() + k2 * color2.getBlue()) / 255;
			color = new Color(red, green, blue);
		}
		return color;
	}

	private Color getColor(int i) {
		int palSize = colors[current_palette].length;
		return colors[current_palette][(i + palSize) % palSize];
	}

	private void initColorPalettes() {
		colors = new Color[colpal.length][];
		for (int p = 0; p < colpal.length; p++) {
			int n = 0;
			for (int i = 0; i < colpal[p].length; i++) {
				n += colpal[p][i][0];
			}
			colors[p] = new Color[n];
			n = 0;
			for (int i = 0; i < colpal[p].length; i++) {
				int[] c1 = colpal[p][i];
				int[] c2 = colpal[p][(i + 1) % colpal[p].length];
				for (int j = 0; j < c1[0]; j++) {
					colors[p][n + j] = new Color(
						(c1[1] * (c1[0] - 1 - j) + c2[1] * j) / (c1[0] - 1),
						(c1[2] * (c1[0] - 1 - j) + c2[2] * j) / (c1[0] - 1),
						(c1[3] * (c1[0] - 1 - j) + c2[3] * j) / (c1[0] - 1));
				}
				n += c1[0];
			}
		}
	}
}
