package WriteReadFile;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        // Thêm dữ liệu mẫu
        manager.addStudent(new Student("S001", "Nguyen Van An",  3.8));
        manager.addStudent(new Student("S002", "Tran Thi Binh",  3.5));
        manager.addStudent(new Student("S003", "Le Quoc Cuong",  2.9));

        System.out.println("=== Danh sách ban đầu ===");
        manager.printAll();

        // --- Demo Text File ---
        System.out.println("\n--- Ghi file TEXT ---");
        manager.saveToTextFile();

        System.out.println("\n--- Đọc lại từ TEXT ---");
        manager.loadFromTextFile();
        manager.printAll();

        // --- Demo Binary File ---
        System.out.println("\n--- Ghi file BINARY ---");
        manager.saveToBinaryFile();

        System.out.println("\n--- Đọc lại từ BINARY ---");
        manager.loadFromBinaryFile();
        manager.printAll();
    }
}