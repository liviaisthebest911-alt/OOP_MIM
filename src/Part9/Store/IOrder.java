package Part9.Store;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface IOrder {
    String getOrderId();
    String getCustomerName();
    java.util.List<IProduct> getProducts();
    String getStatus();
    java.time.LocalDate getOrderDate();
}