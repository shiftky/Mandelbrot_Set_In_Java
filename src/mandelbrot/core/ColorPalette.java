package mandelbrot.core;

import java.awt.Color;

public class ColorPalette {
	private int current_palette = 0; 
	private boolean smoothing = false;
	private Color[][] colors;
	private static final int[][][] colpal = {
		{ {0, 0, 0}, {0, 0, 255}, {0, 255, 255}, {255, 255, 255}, {0, 128, 255} },
		{ {0, 10, 20}, {50, 100, 240}, {20, 3, 26}, {230, 60, 20},
		  {25, 10, 9}, {230, 170, 0}, {20, 40, 10}, {0, 100, 0},
		  {5, 10, 10}, {210, 70, 30}, {90, 0, 50}, {180, 90, 120},
		  {0, 20, 40}, {30, 70, 200} },
		{ {70, 0, 20}, {100, 0, 100}, {255, 0, 0}, {255, 200, 0} },
		{ {40, 70, 10}, {40, 170, 10}, {100, 255, 70}, {255, 255, 255} },
		{ {0, 0, 0}, {255, 255, 255}, {128, 128, 128} }
	};
	
	public ColorPalette(int c, boolean s) {
		current_palette = c;
		smoothing = s;
		initColorPalettes();
	}

	public Color color(int count) {
	    int palSize = colors[current_palette].length;
	    Color color = colors[current_palette][count / 256 % palSize];
	    if (smoothing) {
		    Color color2 = colors[current_palette][(count / 256 + palSize - 1) % palSize];
		    int k1 = count % 256;
		    int k2 = 255 - k1;
		    int red = (k1 * color.getRed() + k2 * color2.getRed()) / 255;
		    int green = (k1 * color.getGreen() + k2 * color2.getGreen()) / 255;
		    int blue = (k1 * color.getBlue() + k2 * color2.getBlue()) / 255;
		    color = new Color(red, green, blue);
	    }
	    return color;
	}

	private void initColorPalettes() {
	    colors = new Color[colpal.length][];
	    for (int p = 0; p < colpal.length; p++) {
		    colors[p] = new Color[colpal[p].length * 12];
		    for (int i = 0; i < colpal[p].length; i++) {
			    int[] c1 = colpal[p][i];
			    int[] c2 = colpal[p][(i + 1) % colpal[p].length];
			    for (int j = 0; j < 12; j++) {
				    colors[p][i * 12 + j] = new Color(
					    (c1[0] * (11 - j) + c2[0] * j) / 11,
					    (c1[1] * (11 - j) + c2[1] * j) / 11,
					    (c1[2] * (11 - j) + c2[2] * j) / 11);
			    }
		    }
	    }
	}
}