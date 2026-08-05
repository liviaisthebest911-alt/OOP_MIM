package Part9.StreamingPlatform;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface IMovie {
    String getMovieId();
    String getTitle();
    String getGenre();
    int getReleaseYear();
    double getRating();
    int getDuration();
}