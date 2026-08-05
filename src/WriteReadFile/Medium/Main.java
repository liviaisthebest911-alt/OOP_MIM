package WriteReadFile.Medium;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String path = "student.dat";

        Student original = new Student(
                "S01",
                "Nguyen Van A",
                3.75
        );

        StudentRepository repo = new StudentRepository();

        try {

            // Save object
            repo.save(original, path);

            // Load object
            Student loaded = repo.load(path);

            // In kết quả
            System.out.println("Original: " + original);
            System.out.println("Loaded  : " + loaded);

            // Kiểm tra round-trip
            System.out.println();
            System.out.println("Round-trip success: "
                    + original.equals(loaded));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}