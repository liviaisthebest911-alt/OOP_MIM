package Part2.Cinema;

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
}
