package Part9.Ecommerce;


/**
 * CLASS: Ecommerce
 * MÔ TẢ: Class quản lý sàn thương mại điện tử, sử dụng Java Stream và Lambda
 *
 * THUỘC TÍNH:
 * - products: List<IProduct> - Danh sách sản phẩm
 * - customers: List<ICustomer> - Danh sách khách hàng
 *
 * YÊU CẦU:
 * 1. Implement interface IEcommerce
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
 *    - findAvailableProducts(): Tìm sản phẩm còn hàng (stock > 0)
 *      (sắp xếp giảm dần theo stock)
 *
 *    - findProductsOnSale(): Tìm sản phẩm đang giảm giá (discount > 0)
 *      (sắp xếp giảm dần theo discount)
 *
 *    - findProductsInPriceRange(double minPrice, double maxPrice):
 *      Tìm sản phẩm trong khoảng giá [minPrice, maxPrice]
 *      (sắp xếp tăng dần theo price)
 *
 *    - getTop5MostExpensiveProducts(): Lấy 5 sản phẩm đắt nhất
 *      (sắp xếp giảm dần theo price, limit 5)
 *
 *    - getTotalInventoryValue(): Tính tổng giá trị kho hàng
 *      (SUM của price * stock cho tất cả sản phẩm)
 *
 *    - getProductCountByCategory(): Đếm số sản phẩm theo danh mục
 *      (groupingBy + counting)
 *
 *    - getAveragePriceByCategory(): Tính giá trung bình theo danh mục
 *      (groupingBy + averagingDouble)
 *
 *    - getAverageDiscount(): Tính phần trăm giảm giá trung bình
 *      (mapToDouble với discount, sau đó average)
 *
 *    === QUẢN LÝ KHÁCH HÀNG ===
 *    - addCustomer(ICustomer customer): Thêm khách hàng
 *
 *    - getAllCustomers(): Trả về tất cả khách hàng
 *
 *    - findCustomersByMembership(String membershipLevel): Tìm khách hàng theo cấp độ
 *      (so sánh không phân biệt hoa thường)
 *
 *    - findTopCustomers(int minPurchases): Tìm khách hàng mua >= minPurchases sản phẩm
 *      (filter theo size của purchasedProducts, sắp xếp giảm dần)
 *
 *    - getTotalSales(): Đếm tổng số sản phẩm đã bán
 *      (flatMap purchasedProducts, sau đó count)
 *
 *    - getTotalRevenue(): Tính tổng doanh thu
 *      (flatMap purchasedProducts, mapToDouble với price, sau đó sum)
 *
 *    - getCustomerCountByMembership(): Đếm số khách hàng theo cấp độ
 *      (groupingBy + counting)
 *
 *    - getAveragePurchasesPerCustomer(): Tính số mua hàng trung bình mỗi khách
 *      (mapToInt với size của purchasedProducts, sau đó average)
 *
 *    - getMostPopularProduct(): Tìm sản phẩm được mua nhiều nhất
 *      (flatMap purchasedProducts, groupingBy productId + counting, tìm max)
 *      (Trả về null nếu không có)
 *
 * 4. Override toString() để trả về:
 *    "Ecommerce{totalProducts=..., totalCustomers=..., totalRevenue=...}"
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Ecommerce implements IEcommerce {
    private List<IProduct> products;
    private List<ICustomer> customers;

    public Ecommerce() {
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    @Override
    public void addProduct(IProduct product) {
        products.add(product);
    }

    @Override
    public void addCustomer(ICustomer customer) {
        customers.add(customer);
    }

    @Override
    public List<IProduct> getAllProducts() {
        return products.stream().collect(Collectors.toList());
    }

    @Override
    public List<IProduct> findProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<IProduct> findAvailableProducts() {
        return products.stream()
                .filter(p -> p.getStock() > 0)
                .sorted(Comparator.comparingInt(IProduct::getStock).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<IProduct> findProductsOnSale() {
        return products.stream()
                .filter(p -> p.getDiscount() > 0)
                .sorted(Comparator.comparingDouble(IProduct::getDiscount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<IProduct> findProductsInPriceRange(double minPrice, double maxPrice) {
        return products.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .sorted(Comparator.comparingDouble(IProduct::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<IProduct> getTop5MostExpensiveProducts() {
        return products.stream()
                .sorted(Comparator.comparingDouble(IProduct::getPrice).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalInventoryValue() {
        return products.stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
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
    public double getAverageDiscount() {
        return products.stream()
                .mapToDouble(IProduct::getDiscount)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<ICustomer> getAllCustomers() {
        return customers.stream().collect(Collectors.toList());
    }

    @Override
    public List<ICustomer> findCustomersByMembership(String membershipLevel) {
        return customers.stream()
                .filter(c -> c.getMembershipLevel().equalsIgnoreCase(membershipLevel))
                .collect(Collectors.toList());
    }

    @Override
    public List<ICustomer> findTopCustomers(int minPurchases) {
        return customers.stream()
                .filter(c -> c.getPurchasedProducts().size() >= minPurchases)
                .sorted((c1, c2) ->
                        Integer.compare(
                                c2.getPurchasedProducts().size(),
                                c1.getPurchasedProducts().size()
                        ))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalSales() {
        return (int) customers.stream()
                .flatMap(c -> c.getPurchasedProducts().stream())
                .count();
    }

    @Override
    public double getTotalRevenue() {
        return customers.stream()
                .flatMap(c -> c.getPurchasedProducts().stream())
                .mapToDouble(IProduct::getPrice)
                .sum();
    }

    @Override
    public Map<String, Long> getCustomerCountByMembership() {
        return customers.stream()
                .collect(Collectors.groupingBy(
                        ICustomer::getMembershipLevel,
                        Collectors.counting()
                ));
    }

    @Override
    public double getAveragePurchasesPerCustomer() {
        return customers.stream()
                .mapToInt(c -> c.getPurchasedProducts().size())
                .average()
                .orElse(0.0);
    }

    @Override
    public IProduct getMostPopularProduct() {
        return customers.stream()
                .flatMap(c -> c.getPurchasedProducts().stream())
                .collect(Collectors.groupingBy(
                        IProduct::getProductId,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> products.stream()
                        .filter(p -> p.getProductId().equals(entry.getKey()))
                        .findFirst()
                        .orElse(null))
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.format(
                "Ecommerce{totalProducts=%d, totalCustomers=%d, totalRevenue=%.0f}",
                products.size(),
                customers.size(),
                getTotalRevenue()
        );
    }
}