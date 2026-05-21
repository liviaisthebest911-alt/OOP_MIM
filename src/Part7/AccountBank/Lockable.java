package Part7.AccountBank;

/**
 * Interface cho đối tượng có thể khóa
 */
public interface Lockable {
    /**
     * Khóa đối tượng
     */
    void lock();

    /**
     * Mở khóa đối tượng
     */
    void unlock();

    /**
     * Kiểm tra đối tượng có bị khóa không
     * @return true nếu bị khóa
     */
    boolean isLocked();
}
