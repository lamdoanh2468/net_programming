package ex1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TH1 {

	public static boolean delete(File path) {
		if (path.isFile()) {
			path.delete();
		}
		if (path.isDirectory()) {
			File[] listFiles = path.listFiles();
			if (listFiles != null) {
				for (File file : listFiles) {
					if (!delete(file)) {
						return false;
					}
				}
			}
			return path.delete();// Xoá thư mục cha
		} else {
			return false;
		}

	}

	public static boolean delete_extend(File path) {
		if (path.isFile()) {
			path.delete();
		}
		if (path.isDirectory()) {
			File[] listFile = path.listFiles();
			if (listFile != null) {
				for (File file : listFile) {
					if (!delete_extend(file)) {
						return false;
					}
				}
			}
			return true;
		} else {
			return false;
		}

	}

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\C_CPP\\CLion 2024.2.2\\");
//		try {
//			if(file.createNewFile()) {
//				System.out.println("Create Successfully");
//			}else {
//				System.out.println("File is created");
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(delete_extend(file)); // delete method

	}

}
