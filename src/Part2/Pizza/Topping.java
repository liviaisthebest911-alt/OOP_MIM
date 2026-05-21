package Part2.Pizza;

/**
 * Lớp Topping đại diện cho một loại phần phủ thêm cho Pizza.
 * Mỗi topping có tên và giá riêng.
 */
public class Topping {
    private String name;
    private double price;

    /**
     * Hàm khởi tạo cho một Topping.
     *
     * @param name  Tên của topping (ví dụ: "Phô mai", "Xúc xích").
     * @param price Giá của topping.
     */
    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}