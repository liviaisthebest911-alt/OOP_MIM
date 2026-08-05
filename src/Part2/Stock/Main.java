package Part2.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Stock> stocks = new ArrayList<>();

        int N = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < N; i++) {

            String line = sc.nextLine();

            String[] parts = line.split(",");
            String symbol = parts[0];
            String name = parts[1];
            double referencePrice = Double.parseDouble(parts[3]);
            double currentPrice = Double.parseDouble(parts[4]);
            long totalVolume  = Long.parseLong(parts[5]);

            Stock stock = new Stock(symbol, name, referencePrice, currentPrice, totalVolume);
            stocks.add(stock);



        }

        int M = Integer.parseInt(sc.nextLine());

        for (int i =0; i < M; i++){
            String lime = sc.nextLine();

            String[] pat = lime.split(" ");

            String symbol = pat[0];
            double newPrice = Double.parseDouble(pat[1]);

            for (Stock st : stocks){
                if(st.getSymbol().equals(symbol)) st.setCurrentPrice(newPrice);
            }

        }

        System.out.println("--- Danh sach chung khoan ---");

        for (Stock st : stocks){
            System.out.println(st);
        }

        Stock manhNhat = stocks.get(0);

        for (Stock stock : stocks){
            if(stock.getChangePercentage() > manhNhat.getChangePercentage()) manhNhat = stock;
        }

        Stock highestVol = stocks.get(0);

        for (Stock stock : stocks){
            if (stock.getTotalVolume() > highestVol.getTotalVolume()) highestVol = stock;
        }

        System.out.println(String.format("Top Gainer: %s (%+.2f%%)",manhNhat.getSymbol(),manhNhat.getChangePercentage()));

        System.out.println(String.format("Highest Volume: %s (%d)",highestVol.getSymbol(), highestVol.getTotalVolume()));



    }
}
