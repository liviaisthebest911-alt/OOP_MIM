package Part4.Antique;

import java.util.*;
class RegularAntique implements Antique {
    private String itemId;
    private String itemName;
    private double rarityScore; // diem do hiem tu 0 -10
    private int quantity;

    public RegularAntique(String itemId,String itemName,double rarityScore,int quantity) {
        this.itemId = itemId;
        this.itemName=itemName;
        this.rarityScore=rarityScore;
        this.quantity=quantity;
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public double getRarityScore() {
        return rarityScore;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public double calculateTotalValue() {
        return quantity*2000000;
    }

    @Override
    public boolean canDisplayInExhibition() {
        if(quantity >= 2 && rarityScore >= 8.0){
            return true;
        }
        return false;
    }

    @Override
    public String getClassification() {
        String[] parts = {"Phổ biến","Bình thường","Khá hiếm","Hiếm","Cực hiếm"};
        int k=0;
        if(rarityScore >= 5.0) k++;
        if (rarityScore >= 6.5) k++;
        if(rarityScore >= 8.0) k++;
        if(rarityScore >= 9.0) k++;

        return parts[k];
    }

    public String toString(){
        return "[ĐỒ CỔ THƯỜNG] Mã món: "+itemId+"\n"+
                "Tên món: "+itemName+"\n"+
                "Độ hiếm: "+rarityScore+"\n"+
                "Số lượng: "+quantity;
    }
}
