package Part9.StreamingPlatform;

/**
 * CLASS: Movie
 * MÔ TẢ: Class đại diện cho một bộ phim trên nền tảng streaming
 *
 * THUỘC TÍNH:
 * - movieId: String - Mã phim (ví dụ: "M001")
 * - title: String - Tên phim
 * - genre: String - Thể loại (Action, Comedy, Drama, Horror, Sci-Fi, Romance, Thriller)
 * - releaseYear: int - Năm phát hành
 * - rating: double - Điểm đánh giá (thang điểm 10.0)
 * - duration: int - Thời lượng (phút)
 *
 * YÊU CẦU:
 * 1. Implement interface IMovie
 * 2. Tạo constructor với đầy đủ tham số
 * 3. Implement các getter methods
 * 4. Override toString() để trả về chuỗi theo format:
 *    "Movie{id='...', title='...', genre='...', year=..., rating=..., duration=...min}"
 */
public class Movie implements IMovie {
    private String movieId;
    private String title;
    private String genre;
    private int releaseYear;
    private double rating;
    private int duration;

    public Movie(String movieId, String title, String genre,
                 int releaseYear, double rating, int duration) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.duration = duration;
    }

    @Override
    public String getMovieId() {
        return movieId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return String.format("Movie{id='%s', title='%s', genre='%s', year=%d, rating=%.1f, duration=%dmin}",
                movieId, title, genre, releaseYear, rating, duration);
    }
}
