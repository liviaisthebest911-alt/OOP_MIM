package Part9.StreamingPlatform;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CLASS: User
 * MÔ TẢ: Class đại diện cho một người dùng trên nền tảng streaming
 *
 * THUỘC TÍNH:
 * - userId: String - Mã người dùng (ví dụ: "U001")
 * - username: String - Tên người dùng
 * - age: int - Tuổi
 * - subscriptionType: String - Loại gói (Free, Basic, Premium, VIP)
 * - watchedMovies: List<IMovie> - Danh sách phim đã xem
 *
 * YÊU CẦU:
 * 1. Implement interface IUser
 * 2. Tạo constructor với đầy đủ tham số
 * 3. Implement các getter methods
 * 4. Thêm method getWatchedCount() (không có trong interface):
 *    - Trả về số lượng phim đã xem
 * 5. Thêm method getAverageRatingOfWatchedMovies() (không có trong interface):
 *    - Tính rating trung bình của các phim đã xem
 *    - SỬ DỤNG STREAM: watchedMovies.stream().mapToDouble(...).average().orElse(0.0)
 * 6. Thêm method getTotalWatchTime() (không có trong interface):
 *    - Tính tổng thời gian đã xem phim (tổng duration)
 *    - SỬ DỤNG STREAM: watchedMovies.stream().mapToInt(...).sum()
 * 7. Override toString() để trả về chuỗi theo format:
 *    "User{id='...', username='...', age=..., subscription='...', watched=...}"
 */
public class User implements IUser {
    private String userId;
    private String username;
    private int age;
    private String subscriptionType;
    private List<IMovie> watchedMovies;

    public User(String userId, String username, int age,
                String subscriptionType, List<IMovie> watchedMovies) {
        this.userId = userId;
        this.username = username;
        this.age = age;
        this.subscriptionType = subscriptionType;
        this.watchedMovies = watchedMovies;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getSubscriptionType() {
        return subscriptionType;
    }

    @Override
    public List<IMovie> getWatchedMovies() {
        return watchedMovies;
    }

    // Methods bổ sung - PHẢI SỬ DỤNG STREAM
    public int getWatchedCount() {
        return watchedMovies.size();
    }

    public double getAverageRatingOfWatchedMovies() {
        return watchedMovies.stream()
                .mapToDouble(IMovie::getRating)
                .average()
                .orElse(0.0);
    }

    public int getTotalWatchTime() {
        return watchedMovies.stream()
                .mapToInt(IMovie::getDuration)
                .sum();
    }

    @Override
    public String toString() {
        return String.format("User{id='%s', username='%s', age=%d, subscription='%s', watched=%d}",
                userId, username, age, subscriptionType, watchedMovies.size());
    }
}
