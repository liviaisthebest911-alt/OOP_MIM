package Part2.Pizza;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    // ... (các thuộc tính và constructor giữ nguyên) ...
    private String name;
    private String size;
    private double basePrice;
    private List<Topping> toppings;

    public Pizza(String name, String size, double basePrice) {
        this.name = name;
        this.size = size;
        this.basePrice = basePrice;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    /**
     * TÍNH TỔNG GIÁ TIỀN CỦA PIZZA NÀY.
     * Yêu cầu:
     * 1. Tính tổng giá của tất cả các topping trong danh sách `toppings`.
     * 2. Tổng giá của pizza = `basePrice` + tổng giá topping vừa tính.
     * 3. Trả về tổng giá cuối cùng của pizza.
     * Gợi ý: Dùng vòng lặp để duyệt qua danh sách `toppings` và cộng dồn giá của chúng.
     *
     * @return Tổng giá tiền của pizza (bao gồm cả topping).
     */
    public double calculatePrice() {
        double total = 0.0;
        for(Topping tp : toppings){
            total += tp.getPrice();
        }

        return basePrice + total;
    }

    // ... (phương thức display() giữ nguyên) ...
    public void display() {
        System.out.printf("- Pizza %s (Size: %s) - Giá cơ bản: %,.0f VND\n", name, size, basePrice);
        if (!toppings.isEmpty()) {
            System.out.println("  + Toppings:");
            for (Topping topping : toppings) {
                System.out.printf("    - %s: %,.0f VND\n", topping.getName(), topping.getPrice());
            }
        }
        System.out.printf("  => Tổng giá Pizza: %,.0f VND\n", calculatePrice());
    }
}