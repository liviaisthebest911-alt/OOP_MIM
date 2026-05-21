package Part6.Movie;

/**
 * Class MovieComparators - Chứa các Comparator để sắp xếp
 *
 * Mô tả:
 * - Class này chứa các static Comparator để sắp xếp Movie theo nhiều cách khác nhau
 *
 * Yêu cầu:
 * Tạo các static Comparator sau:
 *
 * 1. byRating: Comparator<IMovie>
 *    - Sắp xếp theo rating TĂNG DẦN (từ thấp đến cao)
 *
 * 2. byYear: Comparator<IMovie>
 *    - Sắp xếp theo year TĂNG DẦN (phim cũ trước, mới sau)
 *    - Nếu year bằng nhau thì theo title (A-Z)
 *
 * 3. byTitle: Comparator<IMovie>
 *    - Sắp xếp theo title (A-Z, alphabetically)
 *
 * 4. byDuration: Comparator<IMovie>
 *    - Sắp xếp theo duration TĂNG DẦN (phim ngắn trước)
 *
 * 5. byGenreThenRating: Comparator<IMovie>
 *    - Sắp xếp theo genre (A-Z) trước
 *    - Nếu genre giống nhau thì theo rating GIẢM DẦN
 */

import java.util.*;

class MovieComparators {
    public static Comparator<IMovie> byRating =
            Comparator.comparingDouble(IMovie::getRating);

    public static Comparator<IMovie> byYear =
            // ĐÚNG ✓
            Comparator.comparingInt(IMovie::getYear)
                    .thenComparing(IMovie::getTitle);

    public static Comparator<IMovie> byTitle =
            Comparator.comparing(IMovie::getTitle);


    public static Comparator<IMovie> byDuration =
            Comparator.comparingInt(IMovie::getDuration);

    public static Comparator<IMovie> byGenreThenRating  =
            Comparator.comparing(IMovie::getGenre)
                    .thenComparingDouble(m-> -m.getRating());
}
