package Part4.Antique;

import java.util.*;

class PremiumAntique implements Antique {
    private String itemId;
    private String itemName;
    private double rarityScore;
    private int quantity;
    private double insuranceFee;

    public PremiumAntique(String itemId, String itemName, double rarityScore, int quantity, double insuranceFee) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.rarityScore = rarityScore;
        this.quantity = quantity;
        this.insuranceFee = insuranceFee;
    }

    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public double getRarityScore() { return rarityScore; }
    public int getQuantity() { return quantity; }

    public double calculateTotalValue() {
        double baseValue = quantity * 2000000;
        return baseValue + insuranceFee;
    }

    public boolean canDisplayInExhibition() {
        return quantity >= 2 && rarityScore >= 8.0;
    }

    public String getClassification() {
        if (rarityScore >= 9.0) return "Cực hiếm";
        if (rarityScore >= 8.0) return "Hiếm";
        if (rarityScore >= 6.5) return "Khá hiếm";
        if (rarityScore >= 5.0) return "Bình thường";
        return "Phổ biến";
    }

    @Override
    public String toString() {
        return "[ĐỒ CỔ CAO CẤP] Mã món: " + itemId +
                "\nTên món: " + itemName +
                "\nĐộ hiếm: " + rarityScore +
                "\nSố lượng: " + quantity +
                "\nPhí bảo hiểm: " + String.format("%1.f",(long)insuranceFee) + " VNĐ";
    }
}