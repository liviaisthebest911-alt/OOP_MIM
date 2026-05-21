package Part6.BankQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Class BankQueue - Quản lý hàng đợi ngân hàng
 *
 * Mô tả:
 * - Class này quản lý hàng đợi khách hàng trong ngân hàng
 * - Cần implements interface IBankQueue
 * - Sử dụng PriorityQueue để tự động sắp xếp khách hàng theo độ ưu tiên
 *
 * Thuộc tính:
 * - queue: Queue<ICustomer> - Hàng đợi khách hàng (sử dụng PriorityQueue)
 *
 * Yêu cầu:
 * 1. Tạo constructor khởi tạo PriorityQueue rỗng
 * 2. Implement các methods:
 *    - addCustomer(): Thêm khách hàng vào hàng đợi, return true nếu thành công
 *    - serveNext(): Lấy ra và xóa khách hàng tiếp theo (return null nếu queue rỗng)
 *    - peek(): Xem khách hàng tiếp theo mà không xóa (return null nếu queue rỗng)
 *    - isEmpty(): Kiểm tra hàng đợi có rỗng không
 *    - getQueueSize(): Trả về số lượng khách hàng trong hàng đợi
 *    - getAllCustomers(): Trả về List tất cả khách hàng (không thay đổi queue)
 *    - getCustomerPosition(): Trả về vị trí của khách hàng (1-based, -1 nếu không tìm thấy)
 *    - getCustomersByService(): Trả về List khách hàng theo loại dịch vụ
 * 3. Override toString() để in danh sách khách hàng theo thứ tự ưu tiên
 */
class BankQueue implements IBankQueue {
    private Queue<ICustomer> queue;

    public BankQueue(){
        this.queue=new PriorityQueue<>();
    }

    @Override
    public boolean addCustomer(ICustomer customer) {
        return queue.offer(customer);
    }

    @Override
    public ICustomer serveNext() {
        return queue.poll();
    }

    @Override
    public ICustomer peek() {
        if (queue == null) return null;

        return queue.peek();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int getQueueSize() {
        return queue.size();
    }

    @Override
    public List<ICustomer> getAllCustomers() {
        PriorityQueue<ICustomer> list = new PriorityQueue<>(queue);
        List<ICustomer> result = new ArrayList<>();

        while (!list.isEmpty()){
            result.add(list.poll());
        }

        return result;
    }
    //getCustomerPosition(): Trả về vị trí của khách hàng (1-based, -1 nếu không tìm thấy)
    @Override
    public int getCustomerPosition(String ticketNumber) {
        List<ICustomer> list = getAllCustomers();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTicketNumber().equals(ticketNumber)) {
                return i + 1;
            }
        }
        return -1;
    }

    @Override
    public List<ICustomer> getCustomersByService(String serviceType) {
        List<ICustomer> all = getAllCustomers();
        List<ICustomer> result = new ArrayList<>();

        for (ICustomer c : all) {
            if (c.getServiceType().equalsIgnoreCase(serviceType)) {
                result.add(c);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<ICustomer> list = getAllCustomers();

        for (ICustomer c : list) {
            sb.append(c).append("\n");
        }
        return sb.toString();
    }
}
