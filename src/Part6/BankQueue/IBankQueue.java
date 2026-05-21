package Part6.BankQueue;

interface IBankQueue {
    boolean addCustomer(ICustomer customer);
    ICustomer serveNext();
    ICustomer peek();
    boolean isEmpty();
    int getQueueSize();
    java.util.List<ICustomer> getAllCustomers();
    int getCustomerPosition(String ticketNumber);
    java.util.List<ICustomer> getCustomersByService(String serviceType);
}