package Part9.Ecommerce;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface IProduct {
    String getProductId();
    String getName();
    String getCategory();
    double getPrice();
    int getStock();
    double getDiscount();
}