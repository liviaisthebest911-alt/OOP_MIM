package Part9.StreamingPlatform;


/**
 * CLASS: StreamingPlatform
 * MÔ TẢ: Class quản lý nền tảng streaming phim, sử dụng Java Stream và Lambda
 *
 * THUỘC TÍNH:
 * - movies: List<IMovie> - Danh sách phim
 * - users: List<IUser> - Danh sách người dùng
 *
 * YÊU CẦU:
 * 1. Implement interface IStreamingPlatform
 * 2. Khởi tạo 2 ArrayList trong constructor
 * 3. Implement các methods SAU ĐÂY PHẢI SỬ DỤNG JAVA STREAM VÀ LAMBDA:
 *
 *    === QUẢN LÝ PHIM ===
 *    - addMovie(IMovie movie): Thêm phim
 *
 *    - getAllMovies(): Trả về tất cả phim
 *
 *    - findMoviesByGenre(String genre): Tìm phim theo thể loại
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findMoviesByYear(int year): Tìm phim theo năm phát hành
 *
 *    - findHighRatedMovies(double minRating): Tìm phim có rating >= minRating
 *      (sắp xếp giảm dần theo rating)
 *
 *    - findShortMovies(int maxDuration): Tìm phim có thời lượng <= maxDuration
 *      (sắp xếp tăng dần theo duration)
 *
 *    - getTop10Movies(): Lấy 10 phim có rating cao nhất
 *      (sắp xếp giảm dần theo rating, limit 10)
 *
 *    - getAverageRating(): Tính rating trung bình tất cả phim
 *      (average, trả về 0.0 nếu không có phim)
 *
 *    - getMovieCountByGenre(): Đếm số phim theo thể loại
 *      (groupingBy + counting)
 *
 *    - getAverageRatingByGenre(): Tính rating trung bình theo thể loại
 *      (groupingBy + averagingDouble)
 *
 *    - getMovieCountByDecade(): Đếm số phim theo thập kỷ (1990s, 2000s, 2010s, 2020s)
 *      (Hint: groupingBy với lambda (m -> (m.getReleaseYear() / 10) * 10))
 *
 *    === QUẢN LÝ NGƯỜI DÙNG ===
 *    - addUser(IUser user): Thêm người dùng
 *
 *    - getAllUsers(): Trả về tất cả người dùng
 *
 *    - findUsersBySubscription(String subscriptionType): Tìm người dùng theo gói
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findActiveUsers(int minMoviesWatched): Tìm người dùng xem >= minMoviesWatched phim
 *      (filter theo size của watchedMovies, sắp xếp giảm dần)
 *
 *    - getTotalViews(): Đếm tổng số lượt xem phim (tất cả phim trong watchedMovies của users)
 *      (flatMap để lấy watchedMovies, sau đó count)
 *
 *    - getUserCountBySubscription(): Đếm số người dùng theo loại gói
 *      (groupingBy + counting)
 *
 *    - getAverageMoviesWatchedPerUser(): Tính số phim trung bình mỗi user xem
 *      (mapToInt với size của watchedMovies, sau đó average)
 *
 *    - getMostWatchedMovie(): Tìm phim được xem nhiều nhất
 *      (flatMap watchedMovies, groupingBy + counting, tìm max entry)
 *      (Trả về null nếu không có)
 *
 * 4. Override toString() để trả về:
 *    "StreamingPlatform{totalMovies=..., totalUsers=..., totalViews=...}"
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StreamingPlatform implements IStreamingPlatform {
    private List<IMovie> movies;
    private List<IUser> users;

    public StreamingPlatform() {
        this.movies = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    // === QUẢN LÝ PHIM ===

    @Override
    public void addMovie(IMovie movie) {
        movies.add(movie);
    }

    @Override
    public List<IMovie> getAllMovies() {
        return movies.stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<IMovie> findMoviesByGenre(String genre) {
        return movies.stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    @Override
    public List<IMovie> findMoviesByYear(int year) {
        return movies.stream()
                .filter(m -> m.getReleaseYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public List<IMovie> findHighRatedMovies(double minRating) {
        return movies.stream()
                .filter(m -> m.getRating() >= minRating)
                .sorted(Comparator.comparingDouble(IMovie::getRating).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<IMovie> findShortMovies(int maxDuration) {
        return movies.stream()
                .filter(m -> m.getDuration() <= maxDuration)
                .sorted(Comparator.comparingInt(IMovie::getDuration))
                .collect(Collectors.toList());
    }

    @Override
    public List<IMovie> getTop10Movies() {
        return movies.stream()
                .sorted(Comparator.comparingDouble(IMovie::getRating).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageRating() {
        return movies.stream()
                .mapToDouble(IMovie::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    public Map<String, Long> getMovieCountByGenre() {
        return movies.stream()
                .collect(Collectors.groupingBy(
                        IMovie::getGenre,
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Double> getAverageRatingByGenre() {
        return movies.stream()
                .collect(Collectors.groupingBy(
                        IMovie::getGenre,
                        Collectors.averagingDouble(IMovie::getRating)
                ));
    }

    @Override
    public Map<Integer, Long> getMovieCountByDecade() {
        return movies.stream()
                .collect(Collectors.groupingBy(
                        m -> (m.getReleaseYear() / 10) * 10,
                        Collectors.counting()
                ));
    }

    // === QUẢN LÝ NGƯỜI DÙNG ===

    @Override
    public void addUser(IUser user) {
        users.add(user);
    }

    @Override
    public List<IUser> getAllUsers() {
        return users.stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<IUser> findUsersBySubscription(String subscriptionType) {
        return users.stream()
                .filter(u -> u.getSubscriptionType().equalsIgnoreCase(subscriptionType))
                .collect(Collectors.toList());
    }

    @Override
    public List<IUser> findActiveUsers(int minMoviesWatched) {
        return users.stream()
                .filter(u -> u.getWatchedMovies().size() >= minMoviesWatched)
                .sorted((u1, u2) ->
                        Integer.compare(
                                u2.getWatchedMovies().size(),
                                u1.getWatchedMovies().size()
                        ))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalViews() {
        return (int) users.stream()
                .flatMap(u -> u.getWatchedMovies().stream())
                .count();
    }

    @Override
    public Map<String, Long> getUserCountBySubscription() {
        return users.stream()
                .collect(Collectors.groupingBy(
                        IUser::getSubscriptionType,
                        Collectors.counting()
                ));
    }

    @Override
    public double getAverageMoviesWatchedPerUser() {
        return users.stream()
                .mapToInt(u -> u.getWatchedMovies().size())
                .average()
                .orElse(0.0);
    }

    @Override
    public IMovie getMostWatchedMovie() {
        return users.stream()
                .flatMap(u -> u.getWatchedMovies().stream())
                .collect(Collectors.groupingBy(
                        IMovie::getMovieId,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> movies.stream()
                        .filter(m -> m.getMovieId().equals(entry.getKey()))
                        .findFirst()
                        .orElse(null))
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.format(
                "StreamingPlatform{totalMovies=%d, totalUsers=%d, totalViews=%d}",
                movies.size(), users.size(), getTotalViews()
        );
    }
}