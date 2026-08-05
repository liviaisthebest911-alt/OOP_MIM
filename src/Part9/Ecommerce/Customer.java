package Part9.Ecommerce;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CLASS: Customer
 * MÔ TẢ: Class đại diện cho một khách hàng trên sàn thương mại điện tử
 *
 * THUỘC TÍNH:
 * - customerId: String - Mã khách hàng (ví dụ: "C001")
 * - name: String - Tên khách hàng
 * - email: String - Email
 * - membershipLevel: String - Cấp độ thành viên (Bronze, Silver, Gold, Platinum)
 * - purchasedProducts: List<IProduct> - Danh sách sản phẩm đã mua
 *
 * YÊU CẦU:
 * 1. Implement interface ICustomer
 * 2. Tạo constructor với đầy đủ tham số
 * 3. Implement các getter methods
 * 4. Thêm method getPurchaseCount() (không có trong interface):
 *    - Trả về số lượng sản phẩm đã mua
 * 5. Thêm method getTotalSpent() (không có trong interface):
 *    - Tính tổng số tiền đã chi tiêu (tổng price của purchased products)
 *    - SỬ DỤNG STREAM: purchasedProducts.stream().mapToDouble(...).sum()
 * 6. Thêm method getAverageProductPrice() (không có trong interface):
 *    - Tính giá trung bình của các sản phẩm đã mua
 *    - SỬ DỤNG STREAM: purchasedProducts.stream().mapToDouble(...).average().orElse(0.0)
 * 7. Override toString() để trả về chuỗi theo format:
 *    "Customer{id='...', name='...', email='...', membership='...', purchases=...}"
 */
public class Customer implements ICustomer {
    private String customerId;
    private String name;
    private String email;
    private String membershipLevel;
    private List<IProduct> purchasedProducts;

    public Customer(String customerId, String name, String email,
                    String membershipLevel, List<IProduct> purchasedProducts) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.membershipLevel = membershipLevel;
        this.purchasedProducts = purchasedProducts;
    }

    @Override
    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getMembershipLevel() {
        return membershipLevel;
    }

    @Override
    public List<IProduct> getPurchasedProducts() {
        return purchasedProducts;
    }

    public int getPurchaseCount() {
        return purchasedProducts.size();
    }

    public double getTotalSpent() {
        return purchasedProducts.stream()
                .mapToDouble(IProduct::getPrice)
                .sum();
    }

    public double getAverageProductPrice() {
        return purchasedProducts.stream()
                .mapToDouble(IProduct::getPrice)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return String.format("Customer{id='%s', name='%s', email='%s', membership='%s', purchases=%d}",
                customerId, name, email, membershipLevel, purchasedProducts.size());
    }
}