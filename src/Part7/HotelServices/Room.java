package Part7.HotelServices;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Room implements Displayable, Validatable, Priceable {
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private int maxGuests;
    private boolean isBooked;
    private String bookedBy;
    private String checkInDate;
    private String checkOutDate;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String[] VALID_TYPES = {"SINGLE", "DOUBLE", "SUITE", "DELUXE"};

    public Room(String roomNumber, String type, double pricePerNight, int maxGuests) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.maxGuests = maxGuests;
        this.isBooked = false;
        this.bookedBy = null;
        this.checkInDate = null;
        this.checkOutDate = null;
    }

    @Override
    public String getDisplayInfo() {
        String status;
        if (isBooked) {
            status = String.format("Booked by %s (%s - %s)", bookedBy, checkInDate, checkOutDate);
        } else {
            status = "Available";
        }
        return String.format(
                "Room %s | Type: %s | Price: %.1f VND/night | Max Guests: %d | Status: %s",
                roomNumber, type, pricePerNight, maxGuests, status
        );
    }

    @Override
    public boolean isValid() {
        boolean validType = false;
        for (String t : VALID_TYPES) {
            if (t.equals(type)) { validType = true; break; }
        }
        return roomNumber != null && !roomNumber.isEmpty()
                && validType
                && pricePerNight > 0
                && maxGuests > 0;
    }

    @Override
    public double calculateTotalPrice() {
        if (checkInDate == null || checkOutDate == null) return 0;
        LocalDate start = LocalDate.parse(checkInDate, FORMATTER);
        LocalDate end = LocalDate.parse(checkOutDate, FORMATTER);
        long nights = java.time.temporal.ChronoUnit.DAYS.between(start, end);
        return pricePerNight * nights;
    }

    @Override
    public double getPricePerNight() {
        return pricePerNight;
    }

    public int calculateNights(String checkIn, String checkOut) {
        LocalDate start = LocalDate.parse(checkIn, FORMATTER);
        LocalDate end = LocalDate.parse(checkOut, FORMATTER);
        return (int) ChronoUnit.DAYS.between(start, end);
    }

    public void book(String guestName, String checkIn, String checkOut) throws RoomNotAvailableException {
        if (isBooked) {
            throw new RoomNotAvailableException("Room " + roomNumber + " is already booked by " + bookedBy);
        }
        this.bookedBy = guestName;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.isBooked = true;
    }

    public void cancelBooking() {
        this.isBooked = false;
        this.bookedBy = null;
        this.checkInDate = null;
        this.checkOutDate = null;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public int getMaxGuests() { return maxGuests; }
    public boolean isBooked() { return isBooked; }
    public String getBookedBy() { return bookedBy; }

    @Override
    public String toString() { return getDisplayInfo(); }
}
















