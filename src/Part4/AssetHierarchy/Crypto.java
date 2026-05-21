package Part4.AssetHierarchy;

public class Crypto extends Asset {
    private String blockChain;

    public Crypto(String symbol, String name, double basePrice, String blockChain) {
        super(symbol, name, basePrice);
        this.blockChain = blockChain;
    }

    @Override
    double calculateValue(double quantity) {
        return basePrice*quantity;
    }


    @Override
    public String toString() {
        return super.toString() + " | Blockchain: " + blockchain;
    }
}
