package Part7.HotelServices;

public interface Bookable {
    /**
     * Đặt tài nguyên
     * @param guestName Tên khách
     * @param checkInDate Ngày check-in
     * @param checkOutDate Ngày check-out
     * @throws RoomNotAvailableException nếu không khả dụng
     */
    void book(String guestName, String checkInDate, String checkOutDate) throws RoomNotAvailableException;

    /**
     * Hủy đặt tài nguyên
     */
    void cancelBooking();

    /**
     * Kiểm tra tài nguyên có đang được đặt không
     * @return true nếu đang được đặt
     */
    boolean isBooked();
}