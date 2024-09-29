package ex2;

import java.io.File;

public class CommandLine {
	private File defaultDir;

	public CommandLine() {
		// TODO Auto-generated constructor stub
		this.defaultDir = new File("C:\\temp");
		;

	}

	public static File cd(File path) {
		if (path.exists()) {
			return path.getParentFile();

		}
		return null;
	}

	public File getDefaultDir() {
		return defaultDir;
	}

	public void setDefaultDir(File defaultDir) {
		if (defaultDir.exists() && defaultDir.getParentFile() == this.defaultDir) {
			this.defaultDir = defaultDir;
		}
	}

	public static void dir(File path, int indentLevel) {
		String indentation = " ";
		if (path.isFile()) {
			for (int i = 1; i < indentLevel; i++) {
				System.out.print(indentation);
			}
			System.out.println("-File: " + path.getName());
		}
		if (path.isDirectory()) {
			System.out.println("+Folder: " + path.getName());
			File[] listFile = path.listFiles();
			if (listFile != null) {
				for (File file : listFile) {
					dir(file, indentLevel + 1);
				}
			}

		}
	}

	public static boolean del(File path) {
		if (path.isFile()) {
			return path.delete();
		} else if (path.isDirectory()) {
			File[] listFile = path.listFiles();
			if (listFile != null) {
				for (File file : listFile) {
					del(file);

				}
			}
			return path.delete();
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		File file1 = new File("D:\\C#");
		dir(file1, 0);

	}

}
