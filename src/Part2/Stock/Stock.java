package Part2.Stock;

public class Stock {

    private String symbol;
    private String name;
    private double referencePrice;
    private double currentPrice;
    private long totalVolume;

    public Stock() {
    }


    public Stock(String symbol, String name, double referencePrice, double currentPrice, long totalVolume) {
        this.symbol = symbol;
        this.name = name;
        this.referencePrice = referencePrice;
        this.currentPrice = currentPrice;
        this.totalVolume = totalVolume;
    }


    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
        this.referencePrice = 0;
        this.currentPrice = 0;
        this.totalVolume = 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(double referencePrice) {
        this.referencePrice = referencePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public long getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(long totalVolume) {
        this.totalVolume = totalVolume;
    }

    public double getChangePrice() {
        return currentPrice - referencePrice;
    }

    public double getChangePercentage() {

        if(referencePrice == 0) {
            return 0;
        }

        return (getChangePrice() / referencePrice) * 100;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Ref: %.1f | Curr: %.1f", symbol, name, referencePrice, currentPrice);
    }
}