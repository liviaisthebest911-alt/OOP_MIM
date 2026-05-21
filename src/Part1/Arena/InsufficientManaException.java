package Part1.Arena;

/**
 * Một ngoại lệ tùy chỉnh được ném ra khi một nhân vật cố gắng
 * sử dụng một kỹ năng mà không đủ mana.
 */
public class InsufficientManaException extends RuntimeException {
    /**
     * Constructor nhận một thông báo lỗi.
     * @param message Thông báo mô tả lỗi.
     */
    public InsufficientManaException(String message) {
        // Gọi constructor của lớp cha (RuntimeException) để lưu lại thông báo lỗi.
        super(message);
    }
}