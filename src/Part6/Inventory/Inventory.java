package Part6.Inventory;

import java.util.*;

/**
 * Class Inventory - Quản lý kho sản phẩm
 *
 * Mô tả:
 * - Class này quản lý kho sản phẩm sử dụng Map
 * - Cần implements interface IInventory
 * - Sử dụng HashMap với key là productId, value là IProduct
 *
 * Thuộc tính:
 * - products: Map<String, IProduct> - Map lưu trữ sản phẩm (sử dụng HashMap)
 *
 * Yêu cầu:
 * 1. Tạo constructor khởi tạo HashMap rỗng
 * 2. Implement các methods:
 *    - addProduct(): Thêm sản phẩm, return false nếu productId đã tồn tại
 *    - removeProduct(): Xóa sản phẩm theo ID, return true nếu xóa thành công
 *    - getProduct(): Lấy sản phẩm theo ID (return null nếu không tìm thấy)
 *    - updateQuantity(): Cập nhật số lượng, return true nếu thành công
 *    - updatePrice(): Cập nhật giá, return true nếu thành công
 *    - getAllProducts(): Trả về Map tất cả sản phẩm
 *    - getProductsByCategory(): Trả về List sản phẩm theo danh mục
 *    - getAllCategories(): Trả về Set các danh mục (không trùng lặp)
 *    - getTotalProducts(): Trả về tổng số sản phẩm
 *    - getTotalInventoryValue(): Trả về tổng giá trị kho (price * quantity)
 *    - getLowStockProducts(): Trả về sản phẩm có quantity <= threshold
 *    - getMostExpensiveProduct(): Trả về sản phẩm có giá cao nhất
 * 3. Override toString() để in danh sách sản phẩm
 */
class Inventory implements IInventory {
    private Map<String, IProduct> products;

    public Inventory() {
        this.products = new HashMap<>();
    }

    @Override
    public boolean addProduct(IProduct product) {
        if (products.containsKey(product.getProductId())) return false;
        products.put(product.getProductId(), product);
        return true;
    }

    @Override
    public boolean removeProduct(String productId) {
        return products.remove(productId) != null;
    }

    @Override
    public IProduct getProduct(String productId) {
        return products.get(productId);
    }

    @Override
    public boolean updateQuantity(String productId, int newQuantity) {
        IProduct p = products.get(productId);
        if (p == null) return false;
        p.setQuantity(newQuantity);
        return true;
    }

    @Override
    public boolean updatePrice(String productId, double newPrice) {
        IProduct p = products.get(productId);
        if (p == null) return false;
        p.setPrice(newPrice);
        return true;
    }

    @Override
    public Map<String, IProduct> getAllProducts() {
        return new HashMap<>(products);
    }

    @Override
    public List<IProduct> getProductsByCategory(String category) {
        List<IProduct> list = new ArrayList<>();
        for (IProduct p : products.values()) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public Set<String> getAllCategories() {
        Set<String> set = new HashSet<>();
        for (IProduct p : products.values()) {
            set.add(p.getCategory());
        }
        return set;
    }

    @Override
    public int getTotalProducts() {
        return products.size();
    }

    @Override
    public double getTotalInventoryValue() {
        double total = 0;
        for (IProduct p : products.values()) {
            total += p.getPrice() * p.getQuantity();
        }
        return total;
    }

    @Override
    public List<IProduct> getLowStockProducts(int threshold) {
        List<IProduct> list = new ArrayList<>();
        for (IProduct p : products.values()) {
            if (p.getQuantity() <= threshold) {
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public IProduct getMostExpensiveProduct() {
        if (products.isEmpty()) return null;

        IProduct max = null;
        for (IProduct p : products.values()) {
            if (max == null || p.getPrice() > max.getPrice()) {
                max = p;
            }
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (IProduct p : products.values()) {
            sb.append(p).append("\n");
        }
        return sb.toString().trim();
    }
}
