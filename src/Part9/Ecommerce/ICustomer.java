package Part9.Ecommerce;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface ICustomer {
    String getCustomerId();
    String getName();
    String getEmail();
    String getMembershipLevel();
    java.util.List<IProduct> getPurchasedProducts();
}