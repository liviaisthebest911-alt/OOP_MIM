package Part9.Store;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface IStore {
    void addProduct(IProduct product);
    void addOrder(IOrder order);
    java.util.List<IProduct> getAllProducts();
    java.util.List<IProduct> findProductsByCategory(String category);
    java.util.List<IProduct> findProductsByBrand(String brand);
    java.util.List<IProduct> findProductsInStock();
    java.util.List<IProduct> findExpensiveProducts(double minPrice);
    double getTotalInventoryValue();
    java.util.Map<String, Long> getProductCountByCategory();
    java.util.Map<String, Double> getAveragePriceByCategory();
    IProduct getMostExpensiveProduct();
    java.util.List<IOrder> getAllOrders();
    java.util.List<IOrder> findOrdersByCustomer(String customerName);
    java.util.List<IOrder> findOrdersByStatus(String status);
    double getTotalRevenue();
    java.util.Map<String, Long> getOrderCountByStatus();
    long getTotalProductsSold();
}
