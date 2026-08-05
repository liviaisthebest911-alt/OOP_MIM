package Part2.Car;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Lớp RentalAgency là trung tâm quản lý của hệ thống.
 * Quản lý danh sách xe, khách hàng và các giao dịch thuê/trả.
 */
public class RentalAgency {
    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<RentalRecord> rentalRecords;

    public RentalAgency() {
        this.vehicles = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentalRecords = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Xử lý nghiệp vụ cho thuê xe.
     * <p>
     * YÊU CẦU SINH VIÊN HOÀN THIỆN:
     * 1. Tìm đối tượng Customer trong danh sách `customers` dựa vào `customerId`.
     * 2. Tìm đối tượng Vehicle trong danh sách `vehicles` dựa vào `vehicleId`.
     * 3. Kiểm tra các điều kiện sau:
     * - Nếu không tìm thấy khách hàng, in ra thông báo lỗi: "\nLỗi: Không tìm thấy khách hàng với ID [customerId]" và kết thúc hàm.
     * - Nếu không tìm thấy xe, in ra thông báo lỗi: "\nLỗi: Không tìm thấy xe với ID [vehicleId]" và kết thúc hàm.
     * - Nếu xe đã được cho thuê (isAvailable() == false), in ra thông báo lỗi: "\nLỗi: Xe [Hãng] [Model] hiện không có sẵn." và kết thúc hàm.
     * 4. Nếu tất cả điều kiện đều hợp lệ:
     * - Cập nhật trạng thái của xe thành không có sẵn (setAvailable(false)).
     * - Tạo một đối tượng `RentalRecord` mới với thông tin xe và khách hàng vừa tìm được.
     * - Thêm bản ghi vừa tạo vào danh sách `rentalRecords`.
     * - In ra thông báo thành công: "\nThành công: Khách hàng [Tên khách hàng] đã thuê xe [Hãng] [Model].\n"
     *
     * @param customerId ID khách hàng.
     * @param vehicleId  ID xe.
     */
    public void rentVehicle(String customerId, String vehicleId) {

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("\nLỗi: Không tìm thấy khách hàng với ID " + customerId);
            return;
        }

        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("\nLỗi: Không tìm thấy xe với ID " + vehicleId);
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("\nLỗi: Xe " + vehicle.getBrand() + " "
                    + vehicle.getModel() + " hiện không có sẵn.");
            return;
        }

        // Hợp lệ → thực hiện thuê
        vehicle.setAvailable(false);

        RentalRecord record = new RentalRecord( vehicle,customer);
        rentalRecords.add(record);

        System.out.println("\nThành công: Khách hàng "
                + customer.getName()
                + " đã thuê xe "
                + vehicle.getBrand() + " "
                + vehicle.getModel() + ".");


    }

    /**
     * Xử lý nghiệp vụ trả xe.
     * <p>
     * YÊU CẦU SINH VIÊN HOÀN THIỆN:
     * 1. Tìm đối tượng Vehicle trong danh sách `vehicles` dựa vào `vehicleId`. Nếu không tìm thấy, in thông báo lỗi và kết thúc.
     * 2. Tìm bản ghi thuê xe (RentalRecord) đang hoạt động cho chiếc xe này. Một bản ghi được coi là đang hoạt động nếu:
     * - ID của xe trong bản ghi trùng với `vehicleId`.
     * - Ngày trả xe (returnDate) của bản ghi đó vẫn là `null`.
     * 3. Nếu không tìm thấy bản ghi đang hoạt động, in ra thông báo lỗi: "\nLỗi: Không tìm thấy giao dịch thuê đang hoạt động cho xe này." và kết thúc hàm.
     * 4. Nếu tìm thấy:
     * - Cập nhật trạng thái của xe thành có sẵn (setAvailable(true)).
     * - Cập nhật ngày trả xe cho bản ghi thuê xe là ngày hiện tại (setReturnDate(LocalDate.now())).
     * - Gọi hàm `calculateTotalCost()` của bản ghi để tính tổng chi phí.
     * - In ra thông báo thành công: "\nThành công: Xe [Hãng] [Model] đã được trả. Tổng chi phí: [Chi phí] VND\n"
     *
     * @param vehicleId ID của xe được trả.
     */
    public void returnVehicle(String vehicleId) {

        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("\nLỗi: Không tìm thấy xe với ID " + vehicleId);
            return;
        }

        RentalRecord activeRecord = null;

        for (RentalRecord record : rentalRecords) {
            if (record.getVehicle().getId().equals(vehicleId)
                    && record.getReturnDate() == null) {
                activeRecord = record;
                break;
            }
        }

        if (activeRecord == null) {
            System.out.println("\nLỗi: Không tìm thấy giao dịch thuê đang hoạt động cho xe này.");
            return;
        }

        // Cập nhật trạng thái
        vehicle.setAvailable(true);
        activeRecord.setReturnDate(LocalDate.now());

        double cost = activeRecord.calculateTotalCost();
        String formattedCost = String.format("%,.0f", cost);

        System.out.println();
        System.out.println("Thành công: Xe "
                + vehicle.getBrand() + " "
                + vehicle.getModel()
                + " đã được trả. Tổng chi phí: "
                + formattedCost + " VND");

    }

    /**
     * Hiển thị tất cả các xe đang có sẵn.
     * Thể hiện tính Đa hình khi gọi displayDetails().
     */
    public void displayAvailableVehicles() {
        System.out.println("\n===== DANH SÁCH XE CÓ SẴN =====");
        boolean found = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                vehicle.displayDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tất cả xe đã được cho thuê.");
        }
        System.out.println("===============================");
    }

    // Phương thức private hỗ trợ tìm kiếm
    private Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id)) return c;
        }
        return null;
    }

    private Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getId().equals(id)) return v;
        }
        return null;
    }


    public void processFile(String fileName) {

        try (BufferedReader rd = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = rd.readLine()) != null) {

                line = line.trim();

                // Bỏ qua comment và dòng rỗng
                if (line.isBlank() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(",");

                // ================= VEHICLE =================
                if (line.startsWith("VEHICLE")) {

                    String typeVehicle = parts[1];
                    String id = parts[2];
                    String brand = parts[3];
                    String model = parts[4];

                    double giaThue = Double.parseDouble(parts[5]);

                    if (typeVehicle.equalsIgnoreCase("CAR")) {

                        int soChoNgoi = Integer.parseInt(parts[6]);

                        vehicles.add(new Car(id, brand, model, giaThue, soChoNgoi));

                    } else if (typeVehicle.equalsIgnoreCase("MOTORCYCLE")) {

                        int engineCapacity = Integer.parseInt(parts[6]);

                        vehicles.add(new Motorcycle(id, brand, model, giaThue, engineCapacity));
                    }

                }

                // ================= CUSTOMER =================
                else if (line.startsWith("CUSTOMER")) {

                    String customerId = parts[1];
                    String name = parts[2];

                    Customer c = new Customer(customerId, name);

                    addCustomer(c);

                }

                // ================= RENT =================
                else if (line.startsWith("RENT")) {

                    String customerId = parts[1];
                    String maXe = parts[2];

                    rentVehicle(customerId, maXe);



                }

                // ================= RETURN =================
                else if (line.startsWith("RETURN")) {

                    String xeId = parts[1];

                    Vehicle vehicle =
                            findVehicleById(xeId);

                    if (vehicle == null) {

                        System.out.println(
                                "\nLỗi: Không tìm thấy xe với ID "
                                        + xeId
                        );

                        continue;
                    }


                } else if (parts[0].equals("RETURN")) {

                    String vehicleId = parts[1];

                    // GỌI METHOD CÓ SẴN
                    returnVehicle(vehicleId);
                }

            }

        } catch (IOException e) {

            System.out.println("Lỗi đọc file: "
                    + e.getMessage());
        }
    }
}
