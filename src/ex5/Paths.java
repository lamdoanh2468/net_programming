package ex5;

import java.io.File;

public class Paths {

	public static void findAll(String path, String ext1, String ext2) {
		File filePath = new File(path);
		if (filePath.exists()) {
			File[] listFiles = filePath.listFiles();
			if (listFiles != null) {
				for (File file : listFiles) {
					if (file.isFile()) {
						if (file.getName().endsWith(ext1) || file.getName().endsWith(ext2)) {
							System.out.println(file.getAbsolutePath());
						}
					} else if (file.isDirectory()) {
						findAll(file.getAbsolutePath(), ext1, ext2);
					}
				}

			}
		} else {
			System.out.println("Path not exist");
		}

	}

	public static void main(String[] args) {
		String path = "D:\\java_workspace";
		findAll(path, "txt", "java");
	}
}
