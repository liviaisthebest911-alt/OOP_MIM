package Part5.MilkTea;

import java.util.*;

class MilkTeaManager {
    private List<MilkTea> orders;

    public MilkTeaManager() {
        this.orders = new ArrayList<>();
    }

    // Thêm trà sữa vào danh sách
    public void addOrder(MilkTea order) {
        orders.add(order);
    }

    // Lấy tất cả đơn hàng
    public List<MilkTea> getAllOrders() {
        return new ArrayList<>(orders);
    }

    // Tìm trà sữa theo id
    public MilkTea findOrderById(String orderId) {
        for (MilkTea order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    // Search gần đúng theo tên khách
    public List<MilkTea> searchOrdersByCustomerName(String keyword) {
        List<MilkTea> result = new ArrayList<>();

        for (MilkTea order : orders) {
            if (order.getCustomerName()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                result.add(order);
            }
        }

        return result;
    }

    // Danh sách đơn đủ điều kiện nhận quà
    public List<MilkTea> getOrdersCanGetGift() {
        List<MilkTea> result = new ArrayList<>();

        for (MilkTea order : orders) {
            if (order.canGetGift()) {
                result.add(order);
            }
        }

        return result;
    }

    // Top N theo tasteScore
    public List<MilkTea> getTopOrders(int n) {
        List<MilkTea> sorted = new ArrayList<>(orders);

        sorted.sort((a, b) ->
                Double.compare(b.getTasteScore(), a.getTasteScore()));

        if (n > sorted.size()) {
            n = sorted.size();
        }

        return sorted.subList(0, n);
    }

    // Sắp xếp theo tasteScore
    public List<MilkTea> sortByTasteScore(boolean ascending) {
        List<MilkTea> sorted = new ArrayList<>(orders);

        if (ascending) {
            sorted.sort(Comparator.comparingDouble(MilkTea::getTasteScore));
        } else {
            sorted.sort((a, b) ->
                    Double.compare(b.getTasteScore(),a.getTasteScore()));
        }

        return sorted;
    }

    // Sắp xếp theo tên khách A-Z
    public List<MilkTea> sortByCustomerName() {
        List<MilkTea> sorted = new ArrayList<>(orders);

        sorted.sort(Comparator.comparing(MilkTea::getCustomerName));

        return sorted;
    }

    // Trung bình tasteScore
    public double calculateAverageTasteScore() {
        if (orders.isEmpty()) {
            return 0.0;
        }

        double sum = 0;

        for (MilkTea order : orders) {
            sum += order.getTasteScore();
        }

        return sum / orders.size();
    }

    /**
     * Tính tổng doanh thu
     */
    public double calculateTotalRevenue() {
        double total = 0;

        for (MilkTea order : orders) {
            total += order.calculateTotal();
        }

        return total;
    }
}