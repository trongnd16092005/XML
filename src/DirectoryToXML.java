import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryToXML {
    public static void main(String[] args) {
        String directoryPath = getInputDirectoryPath();
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File xmlFile = new File("directory.xml");
            try {
                FileWriter writer = new FileWriter(xmlFile);
                writer.write("<" + directory.getName() + ">\n");
                listDirectory(directory, writer);
                writer.write("</" + directory.getName() + ">\n");
                writer.close();
                System.out.println("Đã tạo file XML thành công.");
            } catch (IOException e) {
                System.out.println("Lỗi khi tạo file XML.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Đường dẫn không hợp lệ hoặc không phải là thư mục.");
        }
    }

    private static void listDirectory(File directory, FileWriter writer) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    writer.write("<" + file.getName() + ">\n");
                    listDirectory(file, writer);
                    writer.write("</" + file.getName() + ">\n");
                } else {
                    writer.write("<file>" + file.getName() + "</file>\n");
                }
            }
        }
    }

    private static String getInputDirectoryPath() {
        System.out.println("Hãy nhập đường dẫn thư mục cần tạo");
        String path = null;
        Scanner scanner = new Scanner(System.in);
        path = scanner.nextLine();
        return path;
    }
}