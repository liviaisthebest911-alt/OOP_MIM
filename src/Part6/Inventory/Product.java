package Part6.Inventory;

/**
 * Class Product - Đại diện cho một sản phẩm
 *
 * Mô tả:
 * - Class này đại diện cho một sản phẩm trong kho
 * - Cần implements interface IProduct
 *
 * Thuộc tính:
 * - productId: String - Mã sản phẩm (unique)
 * - name: String - Tên sản phẩm
 * - price: double - Giá sản phẩm
 * - quantity: int - Số lượng trong kho
 * - category: String - Danh mục sản phẩm
 *
 * Yêu cầu:
 * 1. Tạo constructor với đầy đủ 5 tham số
 * 2. Implement tất cả methods từ interface IProduct
 * 3. Thêm setter methods cho quantity và price (để có thể cập nhật)
 *    - setQuantity(int quantity)
 *    - setPrice(double price)
 * 4. Override toString() để in thông tin sản phẩm theo format:
 *    "Product[id='<id>', name='<name>', price=<price>, qty=<qty>, category='<cat>']"
 */
class Product implements IProduct {
    private String productId;
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(String productId,String name, double price,int quantity, String category ) {
        this.productId = productId;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.category=category;
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
    public double getPrice() {

        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getCategory() {
        return category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product[id='" + productId +
                "', name='" + name +
                "', price=" + String.format("%.2f", price) +
                ", qty=" + quantity +
                ", category='" + category + "']";
    }
}


