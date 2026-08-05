package Part9.Store;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface IProduct {
    String getProductId();
    String getProductName();
    String getCategory();
    double getPrice();
    int getQuantity();
    String getBrand();
}
