package Part2.Pizza;
import java.util.*;
import java.time.*;

public class Order
{
    private int orderId;
    private List<Pizza> pizzas;
    private LocalDateTime orderTime;

    public Order(int orderId){
        this.orderId=orderId;
        this.pizzas=new ArrayList<>();
        this.orderTime = LocalDateTime.of(2025, 9, 15, 22, 30,59);
    }

    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }

    public double calculateTotal(){
        double total = 0;
        for(Pizza pz : pizzas){
            total += pz.calculatePrice();
        }
        return total;
    }

    public void printInvoice() {
        System.out.println();
        System.out.println("================ HÓA ĐƠN ================");
        System.out.println("Mã đơn hàng: " + orderId);
        System.out.println("----------------------------------------");
        System.out.println("Chi tiết đơn hàng:");

        if (pizzas.isEmpty()) {
            System.out.println("(Đơn hàng trống)");
        } else {
            for (Pizza pizza : pizzas) {
                pizza.display();
            }
        }

        System.out.println("----------------------------------------");
        System.out.printf("TỔNG CỘNG: %.0f VND\n", calculateTotal());
        System.out.println("========================================");
    }

    public int getOrderId() {
        return this.orderId;
    }
}
