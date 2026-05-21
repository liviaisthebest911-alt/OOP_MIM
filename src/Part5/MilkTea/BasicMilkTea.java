package Part5.MilkTea;

class BasicMilkTea implements MilkTea {

    private String orderId;
    private String customerName;
    private double tasteScore;
    private int quantity;

    // Constructor đúng: 4 tham số
    public BasicMilkTea(String orderId, String customerName,
                        double tasteScore, int quantity) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.tasteScore = tasteScore;
        this.quantity = quantity;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public double getTasteScore() {
        return tasteScore;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    // Mỗi ly 45k
    @Override
    public double calculateTotal() {
        return quantity * 45000;
    }

    // Điều kiện nhận quà
    @Override
    public boolean canGetGift() {
        return quantity >= 5 && tasteScore >= 8.0;
    }

    @Override
    public String getQualityRank() {
        if (tasteScore >= 9) {
            return "Xuất sắc";
        } else if (tasteScore >= 8) {
            return "Giỏi";
        } else if (tasteScore >= 6.5) {
            return "Khá";
        } else {
            return "Yếu";
        }
    }

    @Override
    public String toString() {
        return "[TRÀ SỮA THƯỜNG] Mã đơn: " + orderId + "\n" +
                "Khách: " + customerName + "\n" +
                "Điểm ngon: " + tasteScore + "\n" +
                "Số ly: " + quantity;
    }
}