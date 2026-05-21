package Part6.Inventory;

interface IInventory {
    boolean addProduct(IProduct product);
    boolean removeProduct(String productId);
    IProduct getProduct(String productId);
    boolean updateQuantity(String productId, int newQuantity);
    boolean updatePrice(String productId, double newPrice);
    java.util.Map<String, IProduct> getAllProducts();
    java.util.List<IProduct> getProductsByCategory(String category);
    java.util.Set<String> getAllCategories();
    int getTotalProducts();
    double getTotalInventoryValue();
    java.util.List<IProduct> getLowStockProducts(int threshold);
    IProduct getMostExpensiveProduct();
}
