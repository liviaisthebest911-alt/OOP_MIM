package Part4.AssetHierarchy;

public class Stock extends Asset{
    private String exchange;

    public Stock(String symbol, String name, double basePrice, String exchange) {
        super(symbol, name, basePrice);
        this.exchange = exchange;
    }

    @Override
    double calculateValue(double quantity) {
        return basePrice*quantity;
    }

    public String toString() {
        return super.toString() + " | Exchange: " + exchange;
    }
}
