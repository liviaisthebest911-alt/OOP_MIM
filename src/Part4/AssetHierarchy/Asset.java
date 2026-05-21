package Part4.AssetHierarchy;

public abstract class Asset {
    protected String symbol;
    protected String name;
    protected double basePrice;

    public Asset(String symbol, String name, double basePrice) {
        this.symbol = symbol;
        this.name = name;
        this.basePrice = basePrice;
    }

    abstract double calculateValue(double quantity);

    @Override
    public String toString() {
        return String.format("[%s] %s | Price: %.2f", symbol, name, basePrice);
    }

}
