package Part2.VendingMachine;

/**
 * Lớp VendingMachine chứa toàn bộ logic hoạt động của một máy bán hàng tự động.
 * Lớp này quản lý trạng thái hiện tại của máy (số dư, sản phẩm đã chọn),
 * xử lý các giao dịch mua bán, và tương tác với kho hàng (Inventory).
 * Nó được thiết kế để mô phỏng các hoạt động chính như chọn hàng, bỏ tiền,
 * thực hiện giao dịch và hủy bỏ.
 */
public class VendingMachine {
    private Inventory<Item> itemInventory = new Inventory<>();
    private Inventory<Coin> cashInventory = new Inventory<>();
    private long currentBalance = 0;
    private Item selectedItem = null;

    /**
     * Hàm khởi tạo cho VendingMachine.
     * Tự động gọi phương thức initialize() để nạp sản phẩm và tiền vào máy.
     */
    public VendingMachine() {
        initialize();
    }

    /**
     * Phương thức private để thiết lập trạng thái ban đầu cho máy bán hàng.
     * Nạp một số lượng sản phẩm và tiền mặt mặc định vào kho.
     */
    private void initialize() {
        for (Item item : ProductCatalog.getAllItems()) {
            itemInventory.put(item, 5); // Mỗi sản phẩm có 5 món
        }
        // Có thể thêm logic nạp tiền ban đầu vào cashInventory nếu cần
    }

    /**
     * Hiển thị danh sách tất cả các sản phẩm có sẵn trong máy,
     * bao gồm tên, giá và số lượng tồn kho hiện tại.
     */
    public void displayProducts() {
        System.out.println("\n===== SẢN PHẨM HIỆN CÓ =====");
        for (Item item : ProductCatalog.getAllItems()) {
            System.out.printf("- %s: %,d VND (Còn lại: %d)\n",
                    item.getName(), item.getPrice(), itemInventory.getQuantity(item));
        }
        System.out.println("=============================");
    }
    public void selectItem(Item item) throws VendingMachineExceptions.SoldOutException {
        // TODO: Học sinh cần viết code ở đây
        if(itemInventory.hasItem(item)){
            selectedItem = item;
            System.out.println("Bạn đã chọn: " + item.getName() + ", Giá: " + item.getPrice() + " VND");
        }else{
            throw new VendingMachineExceptions.SoldOutException("Xin lỗi, " + item.getName() + " đã hết hàng.");

        }
    }

    public void insertCoin(long value) throws VendingMachineExceptions.InvalidCoinException {
        if (!Coin.isValid(value)) {
            throw new VendingMachineExceptions.InvalidCoinException(
                    "Mệnh giá " + value + " không hợp lệ."
            );
        }
        currentBalance += value;
        cashInventory.add(new Coin(value));
        System.out.println("Đã nhận: " + value + " VND. Số dư hiện tại: " + currentBalance + " VND");
    }
    public long executeTransaction()
            throws VendingMachineExceptions.NotEnoughMoneyException {

        if (selectedItem == null) {
            return 0;
        }

        long price = selectedItem.getPrice();

        if (currentBalance >= price) {

            itemInventory.deduct(selectedItem);

            long change = currentBalance - price;

            System.out.println("Giao dịch thành công! Vui lòng nhận " + selectedItem.getName());
            System.out.println("Tiền thối của bạn là: " + change + " VND");

            resetTransaction();

            return change;

        } else {

            long missing = price - currentBalance;

            throw new VendingMachineExceptions.NotEnoughMoneyException(
                    "Không đủ tiền. Vui lòng bỏ thêm " + missing + " VND."
            );
        }
    }


    /**
     * Hủy bỏ giao dịch hiện tại.
     * Máy sẽ trả lại toàn bộ số tiền mà người dùng đã bỏ vào và đặt lại trạng thái giao dịch.
     *
     * @return Toàn bộ số tiền được hoàn lại cho người dùng.
     */
    public long cancelTransaction() {
        System.out.println("Giao dịch đã được hủy. Vui lòng nhận lại tiền.");
        long refund = currentBalance;
        resetTransaction();
        return refund;
    }

    /**
     * Phương thức private để đặt lại trạng thái của máy sau khi một giao dịch
     * kết thúc (dù thành công hay bị hủy).
     * Xóa sản phẩm đã chọn và đặt lại số dư về 0.
     */
    private void resetTransaction() {
        currentBalance = 0;
        selectedItem = null;
    }

}
