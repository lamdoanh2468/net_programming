package ex3;

import java.io.File;

public class TreeDir {
	public static void dirTree(String folder) {
		dirTree(folder, 0);

	}

	public static long getFolderSize(File folder) {
		long length = 0;
		File[] listFiles = folder.listFiles();

		if (listFiles != null) {
			for (File file : listFiles) {
				if (file.isFile()) {
					length += file.length();
				} else {
					length += getFolderSize(file);
				}
			}
		}
		return length;

	}

	public static void dirTree(String folder, int indentLevel) {
		String indentation = "| ";
		File path = new File(folder);
		if (path.isFile()) {
			for (int i = 1; i < indentLevel; i++) {
				System.out.print(indentation);
			}
			System.out.println("+- " + path.getName().toLowerCase());
			return;
		}
		if (path.isDirectory()) {
			for (int i = 0; i < indentLevel; i++) {
				System.out.print(indentation);
			}
			if (indentLevel == 0) {
				System.out.println(path.getName().toUpperCase() +": " +  " " + getFolderSize(path));

			}else {
				System.out.println("+- " + path.getName().toUpperCase() + " " + getFolderSize(path));
			}
			File[] listFile = path.listFiles();
			if (listFile != null) {
				for (File file : listFile) {
					dirTree(file.getAbsolutePath(), indentLevel + 1);
				}
			}

		}
	}

	public static void main(String[] args) {
		dirTree("D:\\Python");

	}

}
