package Part9.StreamingPlatform;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface IUser {
    String getUserId();
    String getUsername();
    int getAge();
    String getSubscriptionType();
    java.util.List<IMovie> getWatchedMovies();
}