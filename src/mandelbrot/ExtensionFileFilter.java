package mandelbrot;

import javax.swing.filechooser.FileFilter;

class ExtensionFileFilter extends FileFilter {
	public String extension, msg;

	public ExtensionFileFilter(String extension , String msg) {
		this.extension = extension;
		this.msg = msg;
	}

	public boolean accept(java.io.File f) {
		return f.getName().endsWith(extension);
	}

	public String getDescription() {
		return msg;
	}

	public String getExtension() {
		return extension;
	}
}