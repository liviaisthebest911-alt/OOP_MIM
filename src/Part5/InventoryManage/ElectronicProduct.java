package Part5.InventoryManage;

class ElectronicProduct implements Product {
    private String productId;
    private String name;
    private double basePrice;
    private int stockQuantity;
    private int warrantyMonths;

    public ElectronicProduct(String productId, String name, double basePrice, int stockQuantity, int warrantyMonths) {
        this.productId = productId;
        this.name = name;
        this.basePrice = basePrice;
        this.stockQuantity = stockQuantity;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getProductId() {
        return productId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public double calculateFinalPrice(double discountPercent) {
        return basePrice * 1.08 * (1 - discountPercent / 100);
    }

    @Override
    public double calculateInventoryValue() {
        return calculateFinalPrice(0) * stockQuantity;
    }

    @Override
    public boolean needsRestock() {
        return stockQuantity < 10;
    }

    @Override
    public boolean updateStock(int quantity) {
        if (quantity > 0) {
            stockQuantity += quantity;
            return true;
        } else if (quantity < 0) {
            if (stockQuantity + quantity < 0) {
                return false;
            }
            stockQuantity += quantity;
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ĐIỆN TỬ] Mã SP: " + productId + "\n" +
                "Tên: " + name + "\n" +
                "Giá gốc: " + basePrice + " VNĐ\n" +
                "Tồn kho: " + stockQuantity + "\n" +
                "Bảo hành: " + warrantyMonths + " tháng";
    }
}
