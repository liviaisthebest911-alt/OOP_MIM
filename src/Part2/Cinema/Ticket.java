package Part2.Cinema;

import java.util.List;
import java.util.UUID;

/**
 * Lớp Ticket đại diện cho một vé đã được đặt thành công.
 * Chứa thông tin về suất chiếu, các ghế đã đặt và tổng chi phí.
 */
public class Ticket {
    private final String ticketId;
    private final Showtime showtime;
    private final List<Seat> bookedSeats;
    private final double totalPrice;
    private static int count = 1;
    private static final double PRICE_PER_SEAT = 80000; // Giá vé cơ bản

    /**
     * Hàm khởi tạo cho một Ticket.
     * @param showtime Suất chiếu mà vé này thuộc về.
     * @param bookedSeats Danh sách các ghế được đặt.
     */
    public Ticket(Showtime showtime, List<Seat> bookedSeats) {
        this.ticketId = "" + count;
        count++;
        this.showtime = showtime;
        this.bookedSeats = bookedSeats;
        this.totalPrice = bookedSeats.size() * PRICE_PER_SEAT;
    }

    /**
     * Hiển thị thông tin chi tiết của vé.
     */
    public void displayTicketDetails() {
        System.out.println("\n========= THÔNG TIN VÉ =========");
        System.out.println("Mã vé: " + ticketId);
        System.out.println(showtime.getMovie().toString());
        System.out.println("Suất chiếu: " + showtime.getFormattedShowtime());
        System.out.print("Ghế đã đặt: ");
        for (Seat seat : bookedSeats) {
            System.out.print(seat.getSeatNumber() + " ");
        }
        System.out.println();
        System.out.printf("Tổng tiền: %,.0f VND\n", totalPrice);
        System.out.println("================================");
    }
}