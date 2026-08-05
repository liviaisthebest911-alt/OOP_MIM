package WriteReadFile;


import java.io.*;
import java.util.*;

public class StudentManager
{
    private List<Student> students = new ArrayList<>();
    private static final String TEXT_FILE = "students.txt";
    private static final String BINARY_FILE = "students.dat";

    // =========================================================
    // PHẦN 1: FILE TEXT — dùng BufferedReader / BufferedWriter
    //

    /**
     * Ghi danh sách sinh viên ra file TEXT.
     * Mỗi dòng = 1 sinh viên theo định dạng: id,name,gpa
     *
     * try-with-resources: Java tự động gọi writer.close()
     * kể cả khi có exception → không bao giờ bị resource leak.
     */

    public void saveToTextFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEXT_FILE))) {

            for (Student s : students) {
                writer.write(s.toString()); // "S001,Nguyen Van A,3.5"
                writer.newLine();           // xuống dòng (OS-independent)
            }
            System.out.println("Đã ghi " + students.size() + " sinh viên vào " + TEXT_FILE);

        } catch (IOException e) {
            // IOException bao gồm: file không tạo được, ổ đĩa đầy, không có quyền ghi...
            System.err.println("Lỗi ghi file text: " + e.getMessage());
        }



    }

    /**
     * Đọc danh sách sinh viên từ file TEXT.
     * Phải tự parse từng dòng — đây là điểm bất tiện của Text I/O.
     */

    public void loadFromTextFile() {

        // Xóa dữ liệu cũ trước khi đọc file mới
        students.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(TEXT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.isBlank()) continue;

                // Format:
                // S01 - Nguyen Van A - GPA: 3.5
                String[] parts = line.split(" - ");

                if (parts.length != 3) {

                    System.err.println("Dong khong hop le: " + line);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();

                // bỏ "GPA:"
                String gpaText = parts[2].replace("GPA:", "").trim();

                double gpa = Double.parseDouble(gpaText);

                students.add(new Student(id, name, gpa));
            }

            System.out.println("Da nap " + students.size() + " sinh vien tu " + TEXT_FILE);

        } catch (IOException e) {

            System.err.println("Loi khi doc file: "+ e.getMessage());
        }



    }

    // =========================================================
    // PHẦN 2: FILE BINARY — dùng ObjectOutputStream / InputStream
    // =========================================================

    public void saveToBinaryFile(){
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(BINARY_FILE)))){
            oos.writeObject(students);
            System.out.println("Đã serialize " + students.size() + " sinh viên vào " + BINARY_FILE);

        } catch (IOException e) {
            System.err.println("Lỗi ghi file binary: " + e.getMessage());
        }
    }

    /**
     * Đọc từ file nhị phân và nạp lại ArrayList.
     *
     * ClassNotFoundException: xảy ra khi file .dat được tạo từ
     * phiên bản class cũ, nhưng class Student hiện tại đã thay đổi
     * và serialVersionUID không khớp.
     */


    public void loadFromBinaryFile(){
        try(ObjectInputStream osi = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(BINARY_FILE)))){
            students = (List<Student>) osi.readObject();
            System.out.println("Đã deserialize " + students.size() + " sinh viên từ " + BINARY_FILE);
        } catch (FileNotFoundException e) {
            System.err.println("File binary không tồn tại: " + BINARY_FILE);
        } catch (ClassNotFoundException e) {
            // Class Student không tìm thấy hoặc serialVersionUID không khớp
            System.err.println("Class không tương thích: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Lỗi đọc file binary: " + e.getMessage());
        }
    }

    // Các method tiện ích
    public void addStudent(Student s)    { students.add(s); }
    public List<Student> getStudents()   { return students; }

    public void printAll() {
        if (students.isEmpty()) {
            System.out.println("(danh sách trống)");
            return;
        }
        students.forEach(s -> System.out.printf("%-8s %-20s %.2f%n",
                s.getId(), s.getName(), s.getGpa()));
    }


}