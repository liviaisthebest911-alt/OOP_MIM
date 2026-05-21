package Part6.Movie;

/**
 * Class MovieCollection - Quản lý bộ sưu tập phim
 *
 * Mô tả:
 * - Class này quản lý bộ sưu tập phim sử dụng ArrayList
 * - Cần implements interface IMovieCollection
 * - Sử dụng Collections.sort() với Comparable và Comparator
 *
 * Thuộc tính:
 * - movies: List<IMovie> - Danh sách phim (sử dụng ArrayList)
 *
 * Yêu cầu:
 * 1. Tạo constructor khởi tạo ArrayList rỗng
 * 2. Implement các methods:
 *    - addMovie(): Thêm phim
 *    - removeMovie(): Xóa phim theo ID
 *    - getMovie(): Lấy phim theo ID
 *    - getAllMovies(): Trả về List tất cả phim
 *    - getTotalMovies(): Trả về tổng số phim
 *    - sortByNaturalOrder(): Sắp xếp theo thứ tự tự nhiên (dùng Comparable - rating giảm dần)
 *    - sortByRating(): Sắp xếp theo rating tăng dần (dùng MovieComparators.byRating)
 *    - sortByYear(): Sắp xếp theo year tăng dần (dùng MovieComparators.byYear)
 *    - sortByTitle(): Sắp xếp theo title A-Z (dùng MovieComparators.byTitle)
 *    - sortByDuration(): Sắp xếp theo duration tăng dần (dùng MovieComparators.byDuration)
 *    - sortByGenreThenRating(): Sắp xếp theo genre rồi rating (dùng MovieComparators)
 *    - getMoviesByGenre(): Lấy phim theo thể loại
 *    - getMoviesByDirector(): Lấy phim theo đạo diễn
 *    - getAverageRating(): Tính rating trung bình
 *    - getTopRatedMovies(): Lấy phim có rating >= minRating
 * 3. Override toString() để in danh sách phim
 *
 * LƯU Ý:
 * - Sử dụng Collections.sort() để sắp xếp
 * - Các method sort phải tạo copy của list trước khi sort (không thay đổi list gốc)
 *
 *
 */


import java.util.*;


class MovieCollection implements IMovieCollection {
    private List<IMovie> movies;

    public MovieCollection() {
        this.movies = new ArrayList<>();
    }

    @Override
    public boolean addMovie(IMovie movie) {
        movies.add(movie);
        return true;
    }

    @Override
    public boolean removeMovie(String movieId) {
        Iterator<IMovie> it = movies.iterator();
        while (it.hasNext()){
            IMovie m = it.next();
            if(m.getMovieId().equals(movieId)){
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public IMovie getMovie(String movieId) {
        for(IMovie iMovie : movies) {
            if(iMovie.getMovieId().equals(movieId)){
                return iMovie;
            }
        }
        return null;
    }

    @Override
    public List<IMovie> getAllMovies() {
        List<IMovie> list = new ArrayList<>();
        for (IMovie iMovie : movies){
            list.add(iMovie);
        }
        return list;
    }
    @Override
    public int getTotalMovies() {
        return movies.size();
    }

    @Override
    public List<IMovie> sortByNaturalOrder() {
        List<IMovie> sorted = new ArrayList<>(movies);
        Collections.sort(sorted);
        return sorted;
    }

    @Override
    public List<IMovie> sortByRating() {
        List<IMovie> sorted = new ArrayList<>(movies);
        Collections.sort(sorted,MovieComparators.byRating);
        return sorted;
    }

    @Override
    public List<IMovie> sortByYear() {
        List<IMovie> sorted = new ArrayList<>(movies);
        Collections.sort(sorted,MovieComparators.byYear);
        return sorted;
    }

    @Override
    public List<IMovie> sortByTitle() {
        List<IMovie> sorted = new ArrayList<>(movies);
        Collections.sort(sorted,MovieComparators.byTitle);
        return sorted;
    }

    @Override
    public List<IMovie> sortByDuration() {
        List<IMovie> sorted =new ArrayList<>(movies);
        Collections.sort(sorted, MovieComparators.byDuration);
        return sorted;
    }

    @Override
    public List<IMovie> sortByGenreThenRating() {
        List<IMovie> sorted =new ArrayList<>(movies);
        Collections.sort(sorted, MovieComparators.byGenreThenRating);
        return sorted;
    }

    @Override
    public List<IMovie> getMoviesByGenre(String genre) {
        List<IMovie> result = new ArrayList<>();
        for(IMovie m : movies){
            if(m.getGenre().equals(genre)){
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public List<IMovie> getMoviesByDirector(String director) {
        List<IMovie> result = new ArrayList<>();
        for(IMovie m : movies){
            if(m.getDirector().equals(director)){
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public double getAverageRating() {
        if(movies.isEmpty()) return 0;
        double sum=0;
        for(IMovie m : movies) {
            sum += m.getRating();
        }
        return sum / movies.size();
    }

    @Override
    public List<IMovie> getTopRatedMovies(double minRating) {
        List<IMovie> result = new ArrayList<>();
        for(IMovie m : movies) {
            if(m.getRating() >= minRating) result.add(m);
        }
        return result;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(IMovie movie : movies){
            stringBuilder.append(movie).append("\n");
        }
        return stringBuilder.toString();
    }
}
