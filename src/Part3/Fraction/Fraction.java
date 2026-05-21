package Part3.Fraction;
/**Thuộc tính: numerator (tử số) và denominator (mẫu số) phải có kiểu dữ liệu int và được đặt ở phạm vi truy cập private.

 Hàm khởi tạo: * Fraction(int n, int d): Khởi tạo phân số với tử n và mẫu d. Nếu d = 0, mặc định mẫu sẽ được gán bằng 1.

 Phương thức:

1. getNumerator(), setNumerator(int n), getDenominator(), setDenominator(int d): Các getter/setter chuẩn.

2. add(Fraction other): Cộng phân số hiện tại với phân số other. Kết quả được lưu trực tiếp vào đối tượng hiện tại
 (không cần rút gọn ở bài tập này để tập trung vào logic cộng).

 3.toDouble(): Trả về giá trị thực của phân số (ví dụ: 1/2 trả về 0.5).

*/

 public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int n, int d) {
        numerator = n;
        denominator = (d == 0) ? 1 : d;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int n) {
        numerator = n;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int d) {
        denominator = (d == 0) ? 1 : d;
    }

    public void add(Fraction other) {
        numerator = numerator * other.denominator + other.numerator * denominator;
        denominator = denominator * other.denominator;
    }

    public double toDouble() {
        return (double) numerator / denominator;
    }
}