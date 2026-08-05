package Part2.Cinema;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cinema {
    private String name;
    private List<Movie> movies;
    private List<Showtime> showtimes;

    public Cinema(String name) {
        this.name = name;
        this.movies=new ArrayList<>();
        this.showtimes = new ArrayList<>();
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public void addShowtime(Showtime showtime){
        showtimes.add(showtime);
    }

    public void displayMovies() {
        System.out.println("\n===== CÁC PHIM ĐANG CHIẾU TẠI " + name.toUpperCase() + " =====");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).toString());
        }
    }

    /**
     * Hiển thị các suất chiếu có sẵn cho một bộ phim cụ thể.
     * @param movie Phim cần tìm suất chiếu.
     * @return Danh sách các suất chiếu tìm thấy.
     */
    public List<Showtime> findShowtimesForMovie(Movie movie) {
        System.out.println("\n--- Các suất chiếu có sẵn cho phim '" + movie.getTitle() + "' ---");
        List<Showtime> availableShowtimes = new ArrayList<>();
        for (Showtime st : showtimes) {
            if (st.getMovie().equals(movie)) {
                availableShowtimes.add(st);
                System.out.println("- " + st.getFormattedShowtime());
            }
        }
        if (availableShowtimes.isEmpty()) {
            System.out.println("Hiện không có suất chiếu nào cho phim này.");
        }
        return availableShowtimes;
    }

    public Ticket bookTickets(Showtime showtime, List<String> seatNumbers){
        List<Seat> seatsToBook = new ArrayList<>();   //  danh sách tạm thời để lưu các đối tượng Seat hợp lệ.
        for(String seatNum : seatNumbers){
            Seat seat = showtime.findSeat(seatNum);
            if(seat == null) {
                System.out.println("erorr");
                return null;
            }else {
                if(seat.isBooked()){
                    System.out.println("ERORR");
                    return null;
                }
            }
            seatsToBook.add(seat);
        }

        //xac nhan dat ve
        for(Seat seat : seatsToBook){
            seat.book();
        }

        Ticket ticket = new Ticket(showtime, seatsToBook);  // tao ve

        System.out.println("->SUCESSECFULL ");
        return ticket;
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    public String getName() {
        return name;
    }

    public void processFile(String fileName) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader rd = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = rd.readLine()) != null) {
                line = line.trim();

                if (line.isBlank() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(";");

                // =================================================
                // MOVIE
                // =================================================
                if (parts[0].equalsIgnoreCase("MOVIE")) {

                    String title = parts[1];
                    String genre = parts[2];
                    int duration = Integer.parseInt(parts[3]);

                    Movie movie = new Movie(title, genre, duration);

                    addMovie(movie);

                    System.out.println("Đã thêm phim: " + title);
                }

                // =================================================
                // SHOWTIME
                // =================================================
                else if (parts[0].equalsIgnoreCase("SHOWTIME")) {

                    String movieTitle = parts[1];
                    LocalDateTime showTime = LocalDateTime.parse(parts[2], formatter);

                    Movie foundMovie = null;
                    for (Movie movie : movies) {

                        if (movie.getTitle().equalsIgnoreCase(movieTitle)) {

                            foundMovie = movie;
                            break;
                        }
                    }

                    if (foundMovie == null) {

                        System.out.println("Không tìm thấy phim: " + movieTitle);
                        continue;
                    }

                    Showtime showtime = new Showtime(foundMovie, showTime);


                    addShowtime(showtime);

                    System.out.println("Đã thêm suất chiếu cho phim " + movieTitle);
                }

                // =================================================
                // DISPLAY_SEATS
                // =================================================
                else if (parts[0].equalsIgnoreCase("DISPLAY_SEATS")) {

                    String movieTitle = parts[1];

                    LocalDateTime showTime = LocalDateTime.parse(parts[2], formatter);

                    Showtime foundShowtime = null;

                    for (Showtime st : showtimes) {

                        boolean sameMovie = st.getMovie().getTitle().equalsIgnoreCase(movieTitle);

                        boolean sameTime = st.getShowtime().equals(showTime);

                        if (sameMovie && sameTime) {

                            foundShowtime = st;
                            break;
                        }
                    }

                    if (foundShowtime == null) {

                        System.out.println("Không tìm thấy suất chiếu.");
                        continue;
                    }

                    foundShowtime.displaySeats();
                }

                // =================================================
                // BOOK
                // =================================================
                else if (parts[0].equalsIgnoreCase("BOOK")) {

                    String movieTitle = parts[1];

                    LocalDateTime showTime = LocalDateTime.parse(parts[2], formatter);

                    Showtime foundShowtime = null;

                    // tìm suất chiếu
                    for (Showtime st : showtimes) {

                        boolean sameMovie = st.getMovie().getTitle().equalsIgnoreCase(movieTitle);

                        boolean sameTime = st.getShowtime().equals(showTime);

                        if (sameMovie && sameTime) {

                            foundShowtime = st;
                            break;
                        }
                    }

                    if (foundShowtime == null) {

                        System.out.println("Không tìm thấy suất chiếu.");
                        continue;
                    }

                    // C4,C5
                    List<String> seatNumbers = Arrays.asList(parts[3].split(","));

                    Ticket ticket = bookTickets(foundShowtime, seatNumbers);

                    if (ticket != null) {
                        ticket.displayTicketDetails();
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }
}
