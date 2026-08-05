package Part2.Cinema;

import javax.swing.text.DateFormatter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Lớp Showtime đại diện cho một suất chiếu cụ thể của một bộ phim.
 * Nó chứa thông tin về phim, thời gian và sơ đồ ghế ngồi của phòng chiếu.
 * Đây là ví dụ về Composition: Showtime "has a" Movie và "has a" list of Seats.
 */
public class Showtime {
    private final Movie movie;
    private final LocalDateTime showtime;
    private final List<Seat> seats;
    private static final int NUM_ROWS = 5;
    private static final int SEATS_PER_ROW = 8;

    /**
     * Hàm khởi tạo cho một Showtime.
     * Tự động tạo ra một sơ đồ ghế ngồi cho phòng chiếu.
     * @param movie Phim được chiếu.
     * @param showtime Thời gian chiếu.
     */
    public Showtime(Movie movie, LocalDateTime showtime) {
        this.movie = movie;
        this.showtime = showtime;
        this.seats = new ArrayList<>();
        // Tạo sơ đồ ghế ngồi, ví dụ 5 hàng (A-E), mỗi hàng 8 ghế (1-8)
        for (char row = 'A'; row < 'A' + NUM_ROWS; row++) {
            for (int num = 1; num <= SEATS_PER_ROW; num++) {
                seats.add(new Seat(row + "" + num));
            }
        }
    }

    // Getters
    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getShowtime() {
        return showtime;
    }

    /**
     * Hiển thị sơ đồ ghế ngồi của suất chiếu.
     */
    public void displaySeats() {
        System.out.println("Sơ đồ ghế ngồi cho suất chiếu phim '" + movie.getTitle() + "' lúc " + getFormattedShowtime());
        System.out.println("-------------------- MÀN HÌNH --------------------");
        for (int i = 0; i < seats.size(); i++) {
            System.out.print(seats.get(i).toString() + " ");
            if ((i + 1) % SEATS_PER_ROW == 0) {
                System.out.println(); // Xuống dòng mới sau mỗi hàng
            }
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Chú thích: [XX] = Đã đặt, [A1] = Còn trống");
    }

    /**
     * Tìm một đối tượng Seat trong danh sách ghế của suất chiếu
     * dựa trên mã ghế (ví dụ: "A1", "B5").
     *
     * @param seatNumber Mã ghế cần tìm.
     * @return Đối tượng Seat nếu tìm thấy, ngược lại trả về null.
     */
    public Seat findSeat(String seatNumber) {
        for (Seat s : seats){
            if(s.getSeatNumber().equalsIgnoreCase(seatNumber)){
                return s;
            }
        }
        return null;
    }


    /**
     * Định dạng thời gian chiếu để hiển thị thân thiện hơn.
     * @return Chuỗi thời gian đã định dạng.
     */
    public String getFormattedShowtime() {
        return showtime.format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
    }



}
