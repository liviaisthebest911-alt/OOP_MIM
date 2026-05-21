package Part7.HotelServices;

public class InvalidReservationException extends Exception {
    public InvalidReservationException(String message) {
        super(message);
    }
}