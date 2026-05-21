package Part6.BankQueue;

/**
 * Class Customer - Đại diện cho một khách hàng
 *
 * Mô tả:
 * - Class này đại diện cho một khách hàng trong hàng đợi ngân hàng
 * - Cần implements interface ICustomer
 * - Implements Comparable để sắp xếp theo priority (priority cao hơn được phục vụ trước)
 *
 * Thuộc tính:
 * - ticketNumber: String - Số thứ tự (ví dụ: "A001", "B002")
 * - name: String - Tên khách hàng
 * - serviceType: String - Loại dịch vụ (ví dụ: "Deposit", "Withdraw", "Transfer")
 * - priority: int - Độ ưu tiên (1=thấp, 5=cao, VIP=10)
 *
 * Yêu cầu:
 * 1. Tạo constructor với đầy đủ 4 tham số
 * 2. Implement tất cả methods từ interface ICustomer
 * 3. Implement Comparable<ICustomer> để so sánh theo priority (giảm dần)
 *    Nếu priority bằng nhau thì so sánh theo ticketNumber (tăng dần)
 * 4. Override toString() để in thông tin khách hàng theo format:
 *    "Customer[ticket='<ticket>', name='<name>', service='<service>', priority=<priority>]"
 */
class Customer implements ICustomer, Comparable<ICustomer> {
    private String ticketNumber;
    private String name;
    private String serviceType;
    private int priority;

    public Customer(String ticketNumber, String name,String serviceType,int priority) {
        this.ticketNumber = ticketNumber;
        this.name=name;
        this.serviceType=serviceType;
        this.priority=priority;
    }

    @Override
    public String getTicketNumber() {
        return ticketNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getServiceType() {
        return serviceType;
    }

    @Override
    public int getPriority() {
        return priority;
    }  
    // So sánh ưu tiên
    @Override
    public int compareTo(ICustomer other) {
        // Priority cao hơn đứng trước
        if (this.priority != other.getPriority()) {
            return Integer.compare(other.getPriority(), priority);
        }

        // Nếu bằng priority thì ticketNumber nhỏ hơn đứng trước
        return other.getTicketNumber().compareTo(getTicketNumber());
    }

    @Override
    public String toString() {
        return "Customer[ticket='" + ticketNumber +
                "', name='" + name +
                "', service='" + serviceType +
                "', priority=" + priority + "]";
    }
}
