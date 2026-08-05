package Part9.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * CLASS: Store
 * MÔ TẢ: Class quản lý cửa hàng, sử dụng Java Stream và Lambda
 *
 * THUỘC TÍNH:
 * - products: List<IProduct> - Danh sách sản phẩm
 * - orders: List<IOrder> - Danh sách đơn hàng
 *
 * YÊU CẦU:
 * 1. Implement interface IStore
 * 2. Khởi tạo 2 ArrayList trong constructor
 * 3. Implement các methods SAU ĐÂY PHẢI SỬ DỤNG JAVA STREAM VÀ LAMBDA:
 *
 *    === QUẢN LÝ SẢN PHẨM ===
 *    - addProduct(IProduct product): Thêm sản phẩm
 *
 *    - getAllProducts(): Trả về tất cả sản phẩm
 *
 *    - findProductsByCategory(String category): Tìm sản phẩm theo danh mục
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findProductsByBrand(String brand): Tìm sản phẩm theo thương hiệu
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findProductsInStock(): Tìm sản phẩm còn hàng (quantity > 0)
 *      (sắp xếp giảm dần theo quantity)
 *
 *    - findExpensiveProducts(double minPrice): Tìm sản phẩm có giá >= minPrice
 *      (sắp xếp giảm dần theo price)
 *
 *    - getTotalInventoryValue(): Tính tổng giá trị tồn kho
 *      (SUM của price * quantity cho tất cả sản phẩm)
 *      (Hint: mapToDouble với lambda (p -> p.getPrice() * p.getQuantity()))
 *
 *    - getProductCountByCategory(): Đếm số sản phẩm theo danh mục
 *      (groupingBy + counting)
 *
 *    - getAveragePriceByCategory(): Tính giá trung bình theo danh mục
 *      (groupingBy + averagingDouble)
 *
 *    - getMostExpensiveProduct(): Tìm sản phẩm đắt nhất
 *      (max với Comparator.comparingDouble, trả về null nếu không có)
 *
 *    === QUẢN LÝ ĐƠN HÀNG ===
 *    - addOrder(IOrder order): Thêm đơn hàng
 *
 *    - getAllOrders(): Trả về tất cả đơn hàng
 *
 *    - findOrdersByCustomer(String customerName): Tìm đơn hàng theo khách hàng
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findOrdersByStatus(String status): Tìm đơn hàng theo trạng thái
 *      (so sánh không phân biệt hoa thường)
 *
 *    - getTotalRevenue(): Tính tổng doanh thu từ các đơn KHÔNG BỊ HỦY
 *      (filter status != "Cancelled", sau đó sum total của mỗi order)
 *      (Hint: Dùng flatMap để lấy products từ orders, hoặc gọi getTotalAmount())
 *
 *    - getOrderCountByStatus(): Đếm số đơn hàng theo trạng thái
 *      (groupingBy + counting)
 *
 *    - getTotalProductsSold(): Đếm tổng số sản phẩm đã bán (trong tất cả orders)
 *      (flatMap để lấy products, sau đó count)
 *
 * 4. Override toString() để trả về:
 *    "Store{totalProducts=..., totalOrders=..., inventoryValue=...}"
 */
public class Store implements IStore {
    private List<IProduct> products;
    private List<IOrder> orders;

    public Store() {
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    // === QUẢN LÝ SẢN PHẨM ===

    @Override
    public void addProduct(IProduct product) {
        products.add(product);
    }

    @Override
    public List<IProduct> getAllProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public List<IProduct> findProductsByCategory(String category) {
        return products.stream()
                .filter(bk -> bk.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<IProduct> findProductsByBrand(String brand) {
        return products.stream()
                .filter(tl -> tl.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    @Override
    public List<IProduct> findProductsInStock() {
        return products.stream()
                .filter(tl -> tl.getQuantity() > 0)
                .sorted(Comparator.comparing(tl -> -tl.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<IProduct> findExpensiveProducts(double minPrice) {
        return products.stream()
                .filter(tk -> tk.getPrice() >= minPrice)
                .sorted(Comparator.comparingDouble(IProduct::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalInventoryValue() {
        return products.stream()
                .mapToDouble(p -> p.getPrice()*p.getQuantity())
                .sum();
    }

    @Override
    public Map<String, Long> getProductCountByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        IProduct::getCategory,
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Double> getAveragePriceByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        IProduct::getCategory,
                        Collectors.averagingDouble(IProduct::getPrice)
                ));
    }

    @Override
    public IProduct getMostExpensiveProduct() {
        return products.stream()
                .max(Comparator.comparingDouble(IProduct::getPrice))
                .orElse(null);
    }

    // === QUẢN LÝ ĐƠN HÀNG ===

    @Override
    public void addOrder(IOrder order) {
        orders.add(order);
    }

    @Override
    public List<IOrder> getAllOrders() {
        return orders.stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<IOrder> findOrdersByCustomer(String customerName) {
        return orders.stream()
                .filter(o -> o.getCustomerName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }

    @Override
    public List<IOrder> findOrdersByStatus(String status) {
        return orders.stream()
                .filter(o -> o.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }


    @Override
    public double getTotalRevenue() {
        return orders.stream()
                .filter(o -> !o.getStatus().equalsIgnoreCase("Cancelled"))
                // Sử dụng flatMap để lấy tất cả sản phẩm từ các đơn hàng không bị hủy
                .flatMap(o -> o.getProducts().stream())
                // Tính tổng giá của các sản phẩm đó
                .mapToDouble(IProduct::getPrice)
                .sum();
    }

    @Override
    public Map<String, Long> getOrderCountByStatus() {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        IOrder::getStatus,
                        Collectors.counting()
                ));
    }



    @Override
    public long getTotalProductsSold() {
        return orders.stream()
                // flatMap để lấy tất cả sản phẩm từ tất cả đơn hàng
                .flatMap(o -> o.getProducts().stream())
                .count();
    }

    @Override
    public String toString() {
        return String.format("Store{totalProducts=%d, totalOrders=%d, inventoryValue=%.0f}",
                products.size(), orders.size(), getTotalInventoryValue());
    }
}