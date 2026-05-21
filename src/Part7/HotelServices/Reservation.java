package Part7.HotelServices;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;



/**
 * CLASS 2: Reservation - Đại diện cho một đặt phòng
 *
 * THUỘC TÍNH:
 * - reservationId: String - Mã đặt phòng (duy nhất)
 * - guestName: String - Tên khách
 * - guestEmail: String - Email khách
 * - guestPhone: String - Số điện thoại khách
 * - room: Room - Phòng được đặt
 * - checkInDate: String - Ngày check-in (format: dd/MM/yyyy)
 * - checkOutDate: String - Ngày check-out (format: dd/MM/yyyy)
 * - numberOfGuests: int - Số khách
 * - totalAmount: double - Tổng tiền
 * - status: String - Trạng thái (PENDING, CONFIRMED, PAID, CANCELLED)
 * - specialRequests: String - Yêu cầu đặc biệt (có thể null)
 *
 * YÊU CẦU:
 * 1. Implement các interface: Displayable, Validatable, Priceable
 * 2. Constructor nhận các tham số: reservationId, guestName, guestEmail, guestPhone,
 *    room, checkInDate, checkOutDate, numberOfGuests
 *    - totalAmount tính từ room.calculateTotalPrice()
 *    - status mặc định = "PENDING"
 *    - specialRequests mặc định = null
 * 3. Implement phương thức getDisplayInfo():
 *    - Format: "Reservation [reservationId] | Guest: [guestName] | Email: [guestEmail] | Phone: [guestPhone] | Room: [room.getRoomNumber()] | Dates: [checkInDate] to [checkOutDate] | Guests: [numberOfGuests] | Total: [totalAmount] VND | Status: [status]"
 * 4. Implement phương thức isValid():
 *    - reservationId không null và không rỗng
 *    - guestName không null và không rỗng
 *    - guestEmail chứa '@'
 *    - guestPhone không null và có độ dài >= 10
 *    - room không null và room.isValid() == true
 *    - checkInDate và checkOutDate không null
 *    - numberOfGuests > 0 và numberOfGuests <= room.getMaxGuests()
 *    - status là một trong: PENDING, CONFIRMED, PAID, CANCELLED
 * 5. Implement calculateTotalPrice():
 *    - Gọi room.calculateNights() để tính số đêm
 *    - Trả về room.getPricePerNight() * số đêm
 * 6. Implement getPricePerNight():
 *    - Trả về room.getPricePerNight()
 * 7. Phương thức confirm():
 *    - Kiểm tra status == "PENDING"
 *    - Đặt phòng: room.book(guestName, checkInDate, checkOutDate)
 *    - Set status = "CONFIRMED"
 *    - Throw InvalidReservationException nếu status không phải PENDING
 * 8. Phương thức pay():
 *    - Kiểm tra status == "CONFIRMED"
 *    - Set status = "PAID"
 *    - Throw PaymentFailedException nếu không thể thanh toán
 * 9. Phương thức cancel():
 *    - Kiểm tra status không phải "PAID" và không phải "CANCELLED"
 *    - Nếu status == "CONFIRMED" thì gọi room.cancelBooking()
 *    - Set status = "CANCELLED"
 *    - Throw InvalidReservationException nếu không thể hủy
 * 10. Phương thức setSpecialRequests(String requests):
 *     - Set specialRequests
 * 11. Phương thức getSpecialRequests(): trả về specialRequests
 * 12. Override toString() trả về getDisplayInfo()
 * 13. Tạo các getter cho tất cả thuộc tính
 */

public class Reservation implements Displayable, Validatable, Priceable {
    private String reservationId;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private Room room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private int numberOfGuests;
    private double totalAmount;
    private String status;
    private String specialRequests;

    private static final Set<String> VALID_STATUS =
            Set.of("PENDING", "CONFIRMED", "PAID", "CANCELLED");

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private LocalDate parseDate(String d) {
        return LocalDate.parse(d, FORMATTER);
    }

    private String format(LocalDate d) { return d.format(FORMATTER); }

    public Reservation(String reservationId, String guestName, String guestEmail,
                       String guestPhone, Room room,
                       String checkIn, String checkOut,
                       int numberOfGuests) {

        this.reservationId = reservationId;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guestPhone = guestPhone;
        this.room = room;
        this.checkInDate = parseDate(checkIn);
        this.checkOutDate = parseDate(checkOut);
        this.numberOfGuests = numberOfGuests;

        this.status = "PENDING";
        this.specialRequests = null;

        this.totalAmount = calculateTotalPrice();
    }

    @Override
    public String getDisplayInfo() {
        return String.format(
                "Reservation %s | Guest: %s | Email: %s | Phone: %s | Room: %s | Dates: %s to %s | Guests: %d | Total: %.1f VND | Status: %s",
                reservationId,
                guestName,
                guestEmail,
                guestPhone,
                room.getRoomNumber(),
                format(checkInDate),
                format(checkOutDate),
                numberOfGuests,
                totalAmount,
                status
        );
    }

    @Override
    public double calculateTotalPrice() {

        long nights = ChronoUnit.DAYS.between(
                checkInDate,
                checkOutDate
        );

        return room.getPricePerNight() * nights;
    }
    @Override
    public boolean isValid() {
        return reservationId != null && !reservationId.isEmpty()
                && guestName != null && !guestName.isEmpty()
                && guestEmail.contains("@")
                && guestPhone != null && guestPhone.length() >= 10
                && room != null && room.isValid()
                && numberOfGuests > 0 && numberOfGuests <= room.getMaxGuests()
                && VALID_STATUS.contains(status);
    }

    @Override
    public double getPricePerNight() {
        return room.getPricePerNight();
    }

    public void confirm() throws InvalidReservationException {
        if (!"PENDING".equals(status)) {
            throw new InvalidReservationException(
                    "Cannot confirm reservation with status: " + status
            );
        }

        try {
            room.book(
                    guestName,
                    format(checkInDate),
                    format(checkOutDate)
            );

        } catch (RoomNotAvailableException e) {

            throw new InvalidReservationException(
                    "Cannot confirm: " + e.getMessage()
            );
        }

        status = "CONFIRMED";
    }

    public void setSpecialRequests(String req) {
        this.specialRequests = req;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    // ============ MISSING GETTER METHODS ============

    public String getReservationId() {
        return reservationId;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }





    @Override
    public String toString() {
        return getDisplayInfo();
    }
}