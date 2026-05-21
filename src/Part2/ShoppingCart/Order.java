package Part2.ShoppingCart;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Lớp Order đại diện cho một đơn hàng đã được xác nhận.
 * Nó chứa một bản sao bất biến của thông tin từ giỏ hàng tại thời điểm thanh toán.
 */
public class Order {
    private final String orderId;
    private final User user;
    private static int c = 1;
    private final List<CartItem> orderedItems;
    private final double totalAmount;
    private final LocalDateTime orderDate;

    /**
     * Hàm khởi tạo cho một Order.
     * Được tạo từ một User và giỏ hàng của họ.
     * @param user Người dùng đặt hàng.
     */
    public Order(User user) {
        this.orderId = "" + c;
        c++;
        this.user = user;
        // Tạo một bản sao của các món hàng để đảm bảo đơn hàng không bị thay đổi
        // nếu giỏ hàng sau đó thay đổi.
        this.orderedItems = user.getCart().getItems();
        this.totalAmount = user.getCart().calculateTotal();
        this.orderDate = LocalDateTime.of(2025, 9, 15, 22, 30, 59);
    }

    /**
     * Hiển thị tóm tắt thông tin của đơn hàng.
     */
    public void displayOrderSummary() {
        System.out.println("\n\n*** ĐẶT HÀNG THÀNH CÔNG ***");
        System.out.println("=========================================");
        System.out.println("Mã đơn hàng: " + orderId);
        System.out.println("Khách hàng: " + user.getName() + " (ID: " + user.getUserId() + ")");
        System.out.println("Ngày đặt: " + orderDate.format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")));
        System.out.println("\n--- Chi tiết sản phẩm ---");
        for (CartItem item : orderedItems) {
            System.out.printf("- %s (SL: %d) - Thành tiền: %,.0f VND\n",
                    item.getProduct().getName(), item.getQuantity(), item.getSubtotal());
        }
        System.out.println("-----------------------------------------");
        System.out.printf("TỔNG THANH TOÁN: %,.0f VND\n", totalAmount);
        System.out.println("=========================================");
    }
}