package Part6.Inventory;

interface IProduct {
    String getProductId();
    String getName();
    double getPrice();
    int getQuantity();
    String getCategory();


    void setQuantity(int quantity);
    void setPrice(double price);
}