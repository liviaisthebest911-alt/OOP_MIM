package Part4.AssetHierarchy;
import java.util.Scanner;

public class JAVA_OOP_202 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Asset[] assets = new Asset[n];
        double[] quantities = new double[n];

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String symbol = scanner.next();
            String name = scanner.next();
            double basePrice = scanner.nextDouble();
            String extraInfo = scanner.next();
            double quantity = scanner.nextDouble();

            if (type.equalsIgnoreCase("Stock")) {
                assets[i] = new Stock(symbol, name, basePrice, extraInfo);
            } else if (type.equalsIgnoreCase("Crypto")) {
                assets[i] = new Crypto(symbol, name, basePrice, extraInfo);
            }

            quantities[i] = quantity;
        }

        double portfolioValue = 0;
        for (int i = 0; i < assets.length; i++) {
            if (assets[i] != null) {
                portfolioValue += assets[i].calculateValue(quantities[i]);
                System.out.println(assets[i]);
            }
        }

        System.out.println("Portfolio Value: " + portfolioValue);

        scanner.close();
    }
}