package Part1.Arena;

/**
 * Interface định nghĩa các hành vi cơ bản cho một đối tượng có khả năng chiến đấu.
 */
public interface Combatant {
    /**
     * Tấn công một đối thủ.
     * @param target Đối thủ bị tấn công.
     */
    void attack(Combatant target);

    /**
     * Nhận sát thương từ một đòn tấn công.
     * @param damage Lượng sát thương phải nhận.
     */
    void takeDamage(int damage);

    /**
     * Kiểm tra xem đối tượng còn sống hay không.
     * @return true nếu còn sống, false nếu đã bị hạ gục.
     */
    boolean isAlive();
}