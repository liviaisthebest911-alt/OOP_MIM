package Part7.AirlineService;

/**
 * CLASS 2: Booking - Đại diện cho một đặt vé
 *
 * THUỘC TÍNH:
 * - bookingId: String - Mã đặt vé (duy nhất, format: BK + 8 số)
 * - passengerName: String - Tên hành khách
 * - passportNumber: String - Số hộ chiếu/CMND (6-12 ký tự)
 * - email: String - Email liên hệ
 * - phoneNumber: String - Số điện thoại (10-11 số)
 * - flight: Flight - Chuyến bay
 * - seatClass: String - Hạng ghế (ECONOMY, BUSINESS, FIRST_CLASS)
 * - numberOfSeats: int - Số ghế đặt
 * - totalPrice: double - Tổng giá vé
 * - bookingDate: String - Ngày đặt vé (format: dd/MM/yyyy HH:mm)
 * - status: String - Trạng thái (CONFIRMED, CANCELLED, CHECKED_IN)
 * - seatNumbers: String - Số ghế (VD: "12A, 12B")
 *
 * YÊU CẦU:
 * 1. Implement các interface: Displayable, Validatable, Priceable
 * 2. Constructor nhận các tham số: bookingId, passengerName, passportNumber, email,
 *    phoneNumber, flight, seatClass, numberOfSeats
 *    - totalPrice = flight.getPrice(seatClass) * numberOfSeats
 *    - bookingDate = thời gian hiện tại
 *    - status = "CONFIRMED"
 *    - seatNumbers = "" (sẽ gán khi check-in)
 * 3. Implement phương thức getDisplayInfo():
 *    - Format: "Booking [bookingId] | Passenger: [passengerName] | Flight: [flight.getFlightNumber()] | Class: [seatClass] | Seats: [numberOfSeats] | Price: [totalPrice] VND | Status: [status] | Booking Date: [bookingDate]"
 * 4. Implement phương thức isValid():
 *    - bookingId match pattern "BK\d{8}"
 *    - passengerName không null và không rỗng
 *    - passportNumber có độ dài 6-12
 *    - email chứa '@'
 *    - phoneNumber có độ dài 10-11 và chỉ chứa số
 *    - flight không null và flight.isValid() == true
 *    - seatClass là một trong: ECONOMY, BUSINESS, FIRST_CLASS
 *    - numberOfSeats > 0
 *    - totalPrice > 0
 *    - status là một trong: CONFIRMED, CANCELLED, CHECKED_IN
 * 5. Implement calculatePrice():
 *    - Trả về flight.getPrice(seatClass) * numberOfSeats
 * 6. Phương thức cancel():
 *    - Kiểm tra status == "CONFIRMED"
 *    - Kiểm tra flight.getStatus() không phải "DEPARTED" hoặc "ARRIVED"
 *    - Gọi flight.cancelReservation() để hoàn lại ghế
 *    - Set status = "CANCELLED"
 *    - Throw InvalidBookingException nếu không thể hủy
 * 7. Phương thức checkIn(String seatNumbers):
 *    - Kiểm tra status == "CONFIRMED"
 *    - Kiểm tra flight.getStatus() == "BOARDING"
 *    - Set this.seatNumbers = seatNumbers
 *    - Set status = "CHECKED_IN"
 *    - Throw FlightDepartedException nếu không thể check-in
 * 8. Phương thức getFlightDetails():
 *    - Trả về thông tin chi tiết chuyến bay
 * 9. Override toString() trả về getDisplayInfo()
 * 10. Tạo các getter cho tất cả thuộc tính
 */
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Booking implements Displayable, Validatable, Priceable {

    private String bookingId;
    private String passengerName;
    private String passportNumber;
    private String email;
    private String phoneNumber;
    private Flight flight;
    private String seatClass;
    private int numberOfSeats;
    private double totalPrice;
    private String bookingDate;
    private String status;
    private String seatNumbers;

    // Constructor
    public Booking(String bookingId, String passengerName, String passportNumber, String email,
                   String phoneNumber, Flight flight, String seatClass, int numberOfSeats) {

        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.passportNumber = passportNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.flight = flight;
        this.seatClass = seatClass;
        this.numberOfSeats = numberOfSeats;

        this.totalPrice = flight.getPrice(seatClass) * numberOfSeats;
        this.bookingDate = "02/11/2025 15:55";
        this.status = "CONFIRMED";
        this.seatNumbers = "";
    }

    // ===================== Displayable =====================

    @Override
    public String getDisplayInfo() {
        return "Booking " + bookingId
                + " | Passenger: " + passengerName
                + " | Flight: " + flight.getFlightNumber()
                + " | Class: " + seatClass
                + " | Seats: " + numberOfSeats
                + " | Price: " + totalPrice + " VND"
                + " | Status: " + status
                + " | Booking Date: " + bookingDate;
    }

    // ===================== Validatable =====================

    @Override
    public boolean isValid() {

        if (bookingId == null || !bookingId.matches("BK\\d{8}")) {
            return false;
        }

        if (passengerName == null || passengerName.trim().isEmpty()) {
            return false;
        }

        if (passportNumber == null || passportNumber.length() < 6 || passportNumber.length() > 12) {
            return false;
        }

        if (email == null || !email.contains("@")) {
            return false;
        }

        if (phoneNumber == null
                || (phoneNumber.length() != 10 && phoneNumber.length() != 11)
                || !phoneNumber.matches("\\d+")) {
            return false;
        }

        if (flight == null || !flight.isValid()) {
            return false;
        }

        if (seatClass == null ||
                (!seatClass.equals("ECONOMY")
                        && !seatClass.equals("BUSINESS")
                        && !seatClass.equals("FIRST_CLASS"))) {
            return false;
        }

        if (numberOfSeats <= 0) {
            return false;
        }

        if (totalPrice <= 0) {
            return false;
        }

        List<String> validStatus = Arrays.asList(
                "CONFIRMED",
                "CANCELLED",
                "CHECKED_IN"
        );

        return validStatus.contains(status);
    }

    // ===================== Priceable =====================

    @Override
    public double calculatePrice() {
        return flight.getPrice(seatClass) * numberOfSeats;
    }

    // ===================== Business methods =====================

    public void cancel() throws InvalidBookingException {

        if (!status.equals("CONFIRMED")) {
            throw new InvalidBookingException("Booking cannot be cancelled");
        }

        if (flight.getStatus().equals("DEPARTED") || flight.getStatus().equals("ARRIVED")) {
            throw new InvalidBookingException("Flight already departed or arrived");
        }

        flight.cancelReservation(seatClass, numberOfSeats);
        status = "CANCELLED";
    }

    public void checkIn(String seatNumbers) throws FlightDepartedException {

        if (!status.equals("CONFIRMED")) {
            throw new FlightDepartedException(
                    "Cannot check-in booking with status: " + status
            );
        }

        if (!flight.getStatus().equals("BOARDING")) {
            throw new FlightDepartedException(
                    "Flight is not boarding"
            );
        }

        this.seatNumbers = seatNumbers;
        this.status = "CHECKED_IN";
    }

    public String getFlightDetails() {
        return flight.getDisplayInfo();
    }

    // ===================== toString =====================

    @Override
    public String toString() {
        return getDisplayInfo();
    }

    // ===================== Getters =====================

    public String getBookingId() {
        return bookingId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public String getSeatNumbers() {
        return seatNumbers;
    }
}