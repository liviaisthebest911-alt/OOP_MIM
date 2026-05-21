package Part5.MilkTea;

import java.util.*;

/**
 * Class VoucherMilkTea - Trà sữa có voucher
 *
 * MÔ TẢ:
 * - VoucherMilkTea là một loại MilkTea
 * - Có 5 thuộc tính:
 *   + orderId (String): Mã đơn
 *   + customerName (String): Tên khách
 *   + tasteScore (double): Điểm ngon (0-10)
 *   + quantity (int): Số ly
 *   + voucherAmount (double): Tiền giảm (VNĐ)
 *
 * YÊU CẦU:
 * 1. Khai báo thuộc tính
 * 2. Tạo constructor
 * 3. Implement các phương thức:
 *    - getOrderId(), getCustomerName(), getTasteScore(), getQuantity(): return thuộc tính
 *    - calculateTotal():
 *        + Tổng gốc = quantity * 45000
 *        + Tổng thực = tổng gốc - voucherAmount
 *        + Nếu tổng thực < 0 thì return 0
 *    - canGetGift(): Đủ điều kiện nếu quantity >= 5 VÀ tasteScore >= 8.0
 *    - getQualityRank(): Giống BasicMilkTea
 * 4. Override toString():
 *    [TRÀ SỮA VOUCHER] Mã đơn: [orderId]
 *    Khách: [customerName]
 *    Điểm ngon: [tasteScore]
 *    Số ly: [quantity]
 *    Voucher: [voucherAmount] VNĐ
 */
import java.util.*;

/**
 * Class VoucherMilkTea - Trà sữa có voucher
 */
class VoucherMilkTea implements MilkTea {

    // Khai báo thuộc tính
    private String orderId;
    private String customerName;
    private double tasteScore;
    private int quantity;
    private double voucherAmount;

    // Constructor
    public VoucherMilkTea(String orderId, String customerName,
                          double tasteScore, int quantity,
                          double voucherAmount) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.tasteScore = tasteScore;
        this.quantity = quantity;
        this.voucherAmount = voucherAmount;
    }

    // Getter methods
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

    // Tính tổng tiền
    @Override
    public double calculateTotal() {
        double originalTotal = quantity * 45000;
        double finalTotal = originalTotal - voucherAmount;

        return Math.max(finalTotal, 0);
    }

    // Điều kiện nhận quà
    @Override
    public boolean canGetGift() {
        return quantity >= 5 && tasteScore >= 8.0;
    }

    // Xếp loại chất lượng
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

    // toString()
    @Override
    public String toString() {
        return "[TRÀ SỮA VOUCHER] Mã đơn: " + orderId + "\n" +
                "Khách: " + customerName + "\n" +
                "Điểm ngon: " + tasteScore + "\n" +
                "Số ly: " + quantity + "\n" +
                "Voucher: " + voucherAmount + " VNĐ";
    }
}
