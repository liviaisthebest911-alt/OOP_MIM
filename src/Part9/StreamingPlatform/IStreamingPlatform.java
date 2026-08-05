package Part9.StreamingPlatform;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface IStreamingPlatform {
    void addMovie(IMovie movie);
    void addUser(IUser user);
    java.util.List<IMovie> getAllMovies();
    java.util.List<IMovie> findMoviesByGenre(String genre);
    java.util.List<IMovie> findMoviesByYear(int year);
    java.util.List<IMovie> findHighRatedMovies(double minRating);
    java.util.List<IMovie> findShortMovies(int maxDuration);
    java.util.List<IMovie> getTop10Movies();
    double getAverageRating();
    java.util.Map<String, Long> getMovieCountByGenre();
    java.util.Map<String, Double> getAverageRatingByGenre();
    java.util.Map<Integer, Long> getMovieCountByDecade();
    java.util.List<IUser> getAllUsers();
    java.util.List<IUser> findUsersBySubscription(String subscriptionType);
    java.util.List<IUser> findActiveUsers(int minMoviesWatched);
    int getTotalViews();
    java.util.Map<String, Long> getUserCountBySubscription();
    double getAverageMoviesWatchedPerUser();
    IMovie getMostWatchedMovie();
}