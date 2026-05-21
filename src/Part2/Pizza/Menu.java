package Part2.Pizza;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private static final Map<String, Double> PIZZA_PRICES = new HashMap<>();
    private static final Map<String, Double> TOPPING_PRICES = new HashMap<>();

    static {
        PIZZA_PRICES.put("Hải sản-S", 150000.0);
        PIZZA_PRICES.put("Hải sản-M", 200000.0);
        PIZZA_PRICES.put("Hải sản-L", 250000.0);
        PIZZA_PRICES.put("Thập cẩm-S", 140000.0);
        PIZZA_PRICES.put("Thập cẩm-M", 190000.0);
        PIZZA_PRICES.put("Thập cẩm-L", 240000.0);

        // Giá Topping
        TOPPING_PRICES.put("Phô mai", 20000.0);
        TOPPING_PRICES.put("Xúc xích", 25000.0);
        TOPPING_PRICES.put("Nấm", 15000.0);

    }

    public static double getBasicPrice(String name, String size){
        String key = name +"-"+size;
        return PIZZA_PRICES.getOrDefault(key, 0.0);
    }

    public static double getToppingPrice(String name){
        return TOPPING_PRICES.getOrDefault(name, 0.0);
    }


}
