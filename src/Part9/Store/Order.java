package Part9.Store;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * CLASS: Order
 * MÔ TẢ: Class đại diện cho một đơn hàng
 *
 * THUỘC TÍNH:
 * - orderId: String - Mã đơn hàng (ví dụ: "ORD001")
 * - customerName: String - Tên khách hàng
 * - products: List<IProduct> - Danh sách sản phẩm trong đơn hàng
 * - status: String - Trạng thái đơn hàng (Pending, Processing, Shipped, Delivered, Cancelled)
 * - orderDate: LocalDate - Ngày đặt hàng
 *
 * YÊU CẦU:
 * 1. Implement interface IOrder
 * 2. Tạo constructor với đầy đủ tham số
 * 3. Implement các getter methods
 * 4. Thêm method getTotalAmount() (không có trong interface):
 *    - Tính tổng giá trị đơn hàng
 *    - SỬ DỤNG STREAM: products.stream().mapToDouble(...).sum()
 * 5. Override toString() để trả về chuỗi theo format:
 *    "Order{id='...', customer='...', productCount=..., total=..., status='...', date=...}"
 */
public class Order implements IOrder {
    private String orderId;
    private String customerName;
    private List<IProduct> products;
    private String status;
    private LocalDate orderDate;

    public Order(String orderId, String customerName, List<IProduct> products,
                 String status, LocalDate orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.products = products;
        this.status = status;
        this.orderDate = orderDate;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public List<IProduct> getProducts() {
        return products;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public LocalDate getOrderDate() {
        return orderDate;
    }

    // Method bổ sung - PHẢI SỬ DỤNG STREAM
    public double getTotalAmount() {
        return products.stream()
                .mapToDouble(IProduct::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        return String.format("Order{id='%s', customer='%s', productCount=%d, total=%.0f, status='%s', date=%s}",
                orderId, customerName, products.size(), getTotalAmount(), status, orderDate);
    }
}
