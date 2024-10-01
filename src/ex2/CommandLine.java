package ex2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CommandLine {
	private File defaultDir;

	public CommandLine() {
		// TODO Auto-generated constructor stub
		this.defaultDir = new File("C:\\temp");
		;

	}

	public void cd(File path) {
		try {
			if (path.exists() && path.isDirectory()) {
				this.defaultDir = path.getParentFile();
				if (defaultDir != null) {
					System.out.println("Parent File is " + defaultDir.getAbsolutePath());

				} else {
					System.out.println("No found Parent File");
				}

			} else {
				System.out.println("Invalid File" + defaultDir.getAbsolutePath());
			}
		} catch (NullPointerException npe) {
			// TODO: handle exception
			System.out.println("Not found the parent file");
		}

	}

	public File getDefaultDir() {

		return defaultDir;
	}

	public void cd_subfolder(File newDefault) {
	    try {
	        // Kiểm tra nếu thư mục tồn tại và là thư mục hợp lệ
	        if (newDefault.exists() && newDefault.isDirectory()) {
	            // Kiểm tra xem thư mục newDefault có nằm trong defaultDir không
	            if (newDefault.getCanonicalPath().startsWith(this.defaultDir.getCanonicalPath())) {
	                this.defaultDir = newDefault; // Cập nhật thư mục hiện tại
	                System.out.println("Chuyển đến thư mục: " + defaultDir.getAbsolutePath());
	            } else {
	                System.out.println("Thư mục không thuộc thư mục hiện tại.");
	            }
	        } else {
	            System.out.println("Thư mục không tồn tại hoặc không hợp lệ.");
	        }
	    } catch (Exception e) {
	        System.out.println("Lỗi: " + e.getMessage());
	    }
	}

	public static void dir(File path, int indentLevel) {
		String indentation = ">";
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
			System.out.println("Delete File " + path.getName());
			return path.delete();
		} else if (path.isDirectory()) {
			System.out.println("Delete Folder " + path.getName());
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
		Scanner scanner = new Scanner(System.in);
		CommandLine cmd = new CommandLine();

		System.out.println(
				"1.DIR: Hiển thị nội dung 1 cấp của thư mục hiện hành. Chỉ hiển thị tên (không hiển thị đường dẫn đầy đủ");
		System.out.println("2.CD subfolder: Thay đổi thư mục hiện hành (mặc định) về thư mục subfolder");
		System.out.println("3.CD ..: Trở về thư mục cha (parrent folder)");
		System.out.println("4.DELETE file/folder: xóa file hoặc folder trong thư mục hiện hành");
		System.out.println("5.EXIT: kết thúc");
		System.out.println("===================================");

		int choice = -1;
		while (choice != 5) {
			System.out.println("\nChọn lệnh (1-5): ");
			// Đọc lựa chọn của người dùng
			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();
				scanner.nextLine(); // Đọc phần còn lại của dòng để tránh bỏ qua
			} else {
				System.out.println("Vui lòng nhập số từ 1 đến 5.");
				scanner.nextLine(); // Bỏ qua đầu vào không hợp lệ
				continue; // Quay lại vòng lặp để lấy đầu vào hợp lệ
			}
			switch (choice) {

			case 1:
				dir(cmd.getDefaultDir(), 0);
				break;
			case 3:
				cmd.cd(cmd.getDefaultDir());
				break;
			case 2:
				System.out.println("Please choose your subfolder: ");
				String input = scanner.nextLine();
				File file = new File(cmd.getDefaultDir(),input);
				cmd.cd_subfolder(file);
				break;
			case 4:
				del(cmd.defaultDir);
				break;

			default:
				System.out.println("Invalid Number");
				break;
			}
		}

	}

}
