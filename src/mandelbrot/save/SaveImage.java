package mandelbrot.save;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import mandelbrot.save.ExtensionFileFilter;

public class SaveImage {
	public static boolean save(BufferedImage buffimg, Component parent) throws IOException {
		ExtensionFileFilter filter[] = {
			new ExtensionFileFilter(".png" , "PNG file (*.png)") ,
			new ExtensionFileFilter(".jpg" , "JPEG file (*.jpg)") ,
			new ExtensionFileFilter(".bmp" , "BMP file (*.bmp)") ,
		};
	
		JFileChooser filechooser = new JFileChooser();
		for (int i=0; i<filter.length; i++) {
			filechooser.addChoosableFileFilter(filter[i]);
		}
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.setFileFilter(filter[0]);
	
		boolean result = false;
		int selected = filechooser.showSaveDialog(parent);
		if (selected == JFileChooser.APPROVE_OPTION){
			File file = filechooser.getSelectedFile();
			String ext = ((ExtensionFileFilter) filechooser.getFileFilter()).getExtension();
			String save_path = file.getPath() + ext;
	
			result = ImageIO.write(buffimg, ext.substring(1), new File(save_path));
		}
		return result; 
	}
}