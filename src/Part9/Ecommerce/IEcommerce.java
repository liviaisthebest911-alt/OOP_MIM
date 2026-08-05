package Part9.Ecommerce;

// ==================== INTERFACES (CUNG CẤP CHO SINH VIÊN) ====================

public interface IEcommerce {
    void addProduct(IProduct product);
    void addCustomer(ICustomer customer);
    java.util.List<IProduct> getAllProducts();
    java.util.List<IProduct> findProductsByCategory(String category);
    java.util.List<IProduct> findAvailableProducts();
    java.util.List<IProduct> findProductsOnSale();
    java.util.List<IProduct> findProductsInPriceRange(double minPrice, double maxPrice);
    java.util.List<IProduct> getTop5MostExpensiveProducts();
    double getTotalInventoryValue();
    java.util.Map<String, Long> getProductCountByCategory();
    java.util.Map<String, Double> getAveragePriceByCategory();
    double getAverageDiscount();
    java.util.List<ICustomer> getAllCustomers();
    java.util.List<ICustomer> findCustomersByMembership(String membershipLevel);
    java.util.List<ICustomer> findTopCustomers(int minPurchases);
    int getTotalSales();
    double getTotalRevenue();
    java.util.Map<String, Long> getCustomerCountByMembership();
    double getAveragePurchasesPerCustomer();
    IProduct getMostPopularProduct();
}