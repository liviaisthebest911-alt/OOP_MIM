package Claude.bai2;

import java.io.*;
import java.util.*;

// ===============================
// Custom Checked Exception
// ===============================


// ===============================
// Main Class
// ===============================
public class CSVProcessor {

    /**
     * Hàm parse 1 dòng CSV thành đối tượng Employee
     */
    public static Employee parseEmployee(String line)
            throws InvalidEmployeeDataException {

        // Tách dữ liệu theo dấu ,
        String[] parts = line.split(",", -1);

        // Kiểm tra đủ số cột chưa
        if (parts.length < 5) {
            throw new InvalidEmployeeDataException("Thieu du lieu: " + line);
        }

        // Lấy dữ liệu từng cột
        String type = parts[0].trim();
        String id = parts[1].trim();
        String name = parts[2].trim();

        // department nằm ở parts[3]
        // nhưng hàm này chưa cần dùng

        // ===============================
        // FULLTIME
        // ===============================
        if (type.equalsIgnoreCase("FULLTIME")) {

            try {
                double salary = Double.parseDouble(parts[4].trim());
                Employee emp = new FullTimeEmployee(id, name, salary);
                return emp;

            } catch (NumberFormatException e) {

                throw new InvalidEmployeeDataException(
                        "Luong FULLTIME khong hop le: " + line
                );
            }
        }

        // ===============================
        // PARTTIME
        // ===============================
        else if (type.equalsIgnoreCase("PARTTIME")) {


            if (parts.length < 6) {

                throw new InvalidEmployeeDataException("PARTTIME thieu du lieu: " + line);
            }

            try {

                double hourlyRate = Double.parseDouble(parts[4].trim());
                double workingHours = Double.parseDouble(parts[5].trim());

                Employee emp = new PartTimeEmployee(id, name, hourlyRate, workingHours);
                return emp;

            } catch (NumberFormatException e) {

                throw new InvalidEmployeeDataException("Du lieu PARTTIME khong hop le: " + line);
            }
        }

        // ===============================
        // CONTRACTOR
        // ===============================
        else if (type.equalsIgnoreCase("CONTRACTOR")) {

            try {
                double contractSalary = Double.parseDouble(parts[4].trim());
                Employee emp = new Contractor(id, name, contractSalary);
                return emp;

            } catch (NumberFormatException e) {

                throw new InvalidEmployeeDataException("Luong CONTRACTOR khong hop le: " + line);
            }
        }

        // ===============================
        // Sai type
        // ===============================
        else {

            throw new InvalidEmployeeDataException("Loai nhan vien khong hop le: " + type);
        }
    }

    // ===============================
    // MAIN
    // ===============================
    public static void main(String[] args) {

        // File input
        String inputFile = "employees.csv";

        // File output
        String reportFile = "report.txt";

        // File lỗi
        String errorFile = "errors.log";

        // ===============================
        // department -> danh sách employee
        // ===============================
        Map<String, List<Employee>> departmentMap = new TreeMap<>();

        // Danh sách lỗi
        List<String> errorList = new ArrayList<>();

        // ===============================
        // ĐỌC FILE CSV
        // ===============================
        try {

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // Bỏ qua dòng header
            reader.readLine();

            String line;

            // Đọc từng dòng
            while (true) {

                line = reader.readLine();

                // Hết file
                if (line == null) {
                    break;
                }

                // Bỏ qua dòng trống
                if (line.isBlank()) {
                    continue;
                }

                // Tách cột
                String[] parts = line.split(",", -1);

                // Lấy department
                String department;

                if (parts.length > 3) {
                    department = parts[3].trim();
                } else {
                    department = "UNKNOWN";
                }

                try {

                    // Parse employee
                    Employee employee = parseEmployee(line);

                    // Nếu department chưa tồn tại
                    if (!departmentMap.containsKey(department)) {

                        List<Employee> newList =new ArrayList<>();
                        departmentMap.put(department, newList);
                    }

                    // Thêm employee vào phòng ban
                    List<Employee> employeeList = departmentMap.get(department);
                    employeeList.add(employee);

                } catch (InvalidEmployeeDataException e) {

                    String errorMessage = "[LOI] " + e.getMessage();
                    errorList.add(errorMessage);
                }
            }

            // Đóng file
            reader.close();

        }

        // Không tìm thấy file
        catch (FileNotFoundException e) {

            System.out.println("Khong tim thay file: " + inputFile);
            return;
        }

        // Lỗi đọc file
        catch (IOException e) {

            System.out.println("Loi doc file: " + e.getMessage());
            return;
        }

        // ===============================
        // GHI FILE REPORT
        // ===============================
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(reportFile))) {

            bw.write("===== BAO CAO TONG HOP =====\n");
            double grandTotal = 0;

            for (Map.Entry<String, List<Employee>> entry : deptMap.entrySet()) {
                bw.write("Phong ban: " + entry.getKey() + "\n");
                double deptTotal = 0;
                for (Employee e : entry.getValue()) {
                    double sal = e.calculateSalary();
                    deptTotal += sal;
                    bw.write(String.format(
                            "  - %-20s (%s): %,15.2f VND\n",
                            e.getName(), e.getEmployeeType(), sal));
                }
                bw.write(String.format("  Tong: %,.2f VND\n\n", deptTotal));
                grandTotal += deptTotal;
            }
            bw.write(String.format(
                    "===== TONG CONG: %,.2f VND =====\n", grandTotal));
            bw.write("So dong loi: " + errorLines.size() + " (xem errors.log)\n");

        } catch (IOException e) {
            System.err.println("Loi ghi report: " + e.getMessage());
        }


        // ===============================
        // GHI FILE ERROR
        // ===============================
        if (errorList.size() > 0) {

            try {

                BufferedWriter errorWriter = new BufferedWriter(new FileWriter(errorFile));

                for (String error : errorList) {
                    errorWriter.write(error);
                    errorWriter.newLine();
                }
                errorWriter.close();

            }

            catch (IOException e) {

                System.out.println("Loi ghi errors.log");
            }
        }

        // ===============================
        // DONE
        // ===============================
        System.out.println("Hoan tat xu ly file.");
    }
}