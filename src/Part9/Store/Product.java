package Part9.Store;

/**
 * CLASS: Product
 * MÔ TẢ: Class đại diện cho một sản phẩm trong cửa hàng
 *
 * THUỘC TÍNH:
 * - productId: String - Mã sản phẩm (ví dụ: "P001")
 * - productName: String - Tên sản phẩm
 * - category: String - Danh mục (Electronics, Clothing, Food, Books, Toys)
 * - price: double - Giá sản phẩm (VND)
 * - quantity: int - Số lượng tồn kho
 * - brand: String - Thương hiệu
 *
 * YÊU CẦU:
 * 1. Implement interface IProduct
 * 2. Tạo constructor với đầy đủ tham số
 * 3. Implement các getter methods
 * 4. Override toString() để trả về chuỗi theo format:
 *    "Product{id='...', name='...', category='...', price=..., quantity=..., brand='...'}"
 */
public class Product implements IProduct {
    private String productId;
    private String productName;
    private String category;
    private double price;
    private int quantity;
    private String brand;

    public Product(String productId, String productName, String category,
                   double price, int quantity, String brand) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
    }

    @Override
    public String getProductId() {
        return productId;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', category='%s', price=%.0f, quantity=%d, brand='%s'}",
                productId, productName, category, price, quantity, brand);
    }
}