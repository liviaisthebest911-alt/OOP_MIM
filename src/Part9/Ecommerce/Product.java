package Part9.Ecommerce;

/**
 * CLASS: Product
 * MÔ TẢ: Class đại diện cho một sản phẩm trên sàn thương mại điện tử
 *
 * THUỘC TÍNH:
 * - productId: String - Mã sản phẩm (ví dụ: "P001")
 * - name: String - Tên sản phẩm
 * - category: String - Danh mục (Electronics, Fashion, Home, Books, Sports, Beauty)
 * - price: double - Giá gốc (VND)
 * - stock: int - Số lượng tồn kho
 * - discount: double - Phần trăm giảm giá (0-100)
 *
 * YÊU CẦU:
 * 1. Implement interface IProduct
 * 2. Tạo constructor với đầy đủ tham số
 * 3. Implement các getter methods
 * 4. Thêm method getFinalPrice() (không có trong interface):
 *    - Tính giá sau khi giảm: price * (1 - discount/100)
 * 5. Override toString() để trả về chuỗi theo format:
 *    "Product{id='...', name='...', category='...', price=..., stock=..., discount=...%}"
 */
public class Product implements IProduct {
    private String productId;
    private String name;
    private String category;
    private double price;
    private int stock;
    private double discount;

    public Product(String productId, String name, String category,
                   double price, int stock, double discount) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
    }

    @Override
    public String getProductId() {
        return productId;
    }

    @Override
    public String getName() {
        return name;
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
    public int getStock() {
        return stock;
    }

    @Override
    public double getDiscount() {
        return discount;
    }

    public double getFinalPrice() {
        return price * (1 - discount / 100);
    }

    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', category='%s', price=%.0f, stock=%d, discount=%.0f%%}",
                productId, name, category, price, stock, discount);
    }
}