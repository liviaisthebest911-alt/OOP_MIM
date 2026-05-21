package Part2.Order;

/*
**Hiểu cách sử dụng hằng số (final) và kiểu liệt kê (mô phỏng bằng String hoặc Enum cơ bản).
*Thực hiện các phương thức tính toán dựa trên nhiều thuộc tính.
*Xử lý định dạng thời gian và số liệu tiền tệ.
*/

public class Order {

    // Hằng số
    public static final String BUY = "BUY";
    public static final String SELL = "SELL";

    public static final String PENDING = "PENDING";
    public static final String MATCHED = "MATCHED";
    public static final String CANCELLED = "CANCELLED";

    // Attributes
    private final String orderId;
    private final String symbol;
    private final String type;

    private double price;
    private int quantity;
    private String status;

    // Constructor
    public Order(String orderId, String symbol,
                 String type, double price, int quantity) {

        if(price <= 0 || quantity <= 0){
            throw new IllegalArgumentException(
                    "Price và quantity phải > 0"
            );
        }

        this.orderId = orderId;
        this.symbol = symbol;
        this.type = type;
        this.price = price;
        this.quantity = quantity;

        this.status = PENDING;
    }

    // Constructor đầy đủ
    public Order(String orderId, String symbol,
                 String type, double price,
                 int quantity, String status) {

        this(orderId, symbol, type, price, quantity);
        this.status = status;
    }

    // Getter
    public String getOrderId() {
        return orderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    // Setter
    public void setPrice(double price) {
        if(price > 0){
            this.price = price;
        }
    }

    public void setQuantity(int quantity) {
        if(quantity > 0){
            this.quantity = quantity;
        }
    }

    // Tính tổng giá trị
    public double getTotalValue(){
        return price * quantity;
    }

    // Tính phí
    public double getFee(){

        if(type.equals(BUY)){
            return getTotalValue() * 0.001;
        }

        return getTotalValue() * 0.0015;
    }

    // Match lệnh
    public void match(){
        status = MATCHED;
    }

    // Hủy lệnh
    public void cancel(){

        if(status.equals(PENDING)){
            status = CANCELLED;
        }
    }

    @Override
    public String toString() {
        return "[" + orderId + "] "
                + symbol + " | "
                + type + " | "
                + price + " x "
                + quantity
                + " | Status: "
                + status;
    }
}
