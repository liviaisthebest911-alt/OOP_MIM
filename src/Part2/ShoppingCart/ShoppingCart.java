package Part2.ShoppingCart;

import Part1.BuldingCarClass.Car;

import java.util.*;

public class ShoppingCart {
    private final List<CartItem> items;


    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity){
        items.add(product);

       for (CartItem item : items ){
           if(item.equals(product)){
               item.increaseQuantity(quantity);

           }
       }

        CartItem cartItem = new CartItem(product, quantity);
        items.add(cartItem);
    }
    /**
     * Cập nhật số lượng cho một sản phẩm trong giỏ.
     * @param product Sản phẩm cần cập nhật.
     * @param newQuantity Số lượng mới.
     */
    public void updateQuantity(Product product, int newQuantity) {
        Optional<CartItem> item = findItemByProduct(product);
        if(item.isPresent()){
            if(newQuantity > 0){
                item.get().setQuantity(newQuantity);
            }else {
                removeProduct(product);
            }
        }


    }

    //xoa 1 san pham khoi gio hang

    public void removeProduct(Product product){
        items.removeIf(item -> item.getProduct().equals(product));
    }

    public double calculateTotal(){
        int total = 0;
        for (CartItem cartItem : items){
            total += cartItem.getSubtotal();
        }
        return total;
    }

    /**
     * Hiển thị chi tiết các món hàng trong giỏ.
     */
    public void displayCart() {
        System.out.println("\n--- CHI TIẾT GIỎ HÀNG ---");
        if (items.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
        } else {
            System.out.printf("%-20s | %-10s | %-15s | %-15s\n", "Sản phẩm", "Số lượng", "Đơn giá", "Thành tiền");
            System.out.println("--------------------------------------------------------------------");
            for (CartItem item : items) {
                Product p = item.getProduct();
                System.out.printf("%-20s | %-10d | %,.0f VND | %,.0f VND\n",
                        p.getName(), item.getQuantity(), p.getPrice(), item.getSubtotal());
            }
            System.out.println("--------------------------------------------------------------------");
            System.out.printf("TỔNG CỘNG: %,.0f VND\n", calculateTotal());
        }
        System.out.println("--------------------------");
    }

    /**
     * Trả về một bản sao của danh sách các món hàng để đảm bảo tính đóng gói.
     * @return Một danh sách các CartItem.
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items); // Trả về một bản sao
    }


    private Optional<CartItem> findItemByProduct(Product product){
        return items.stream()
                .filter(n -> n.getProduct().equals(product))
                .findFirst();
    }


}
