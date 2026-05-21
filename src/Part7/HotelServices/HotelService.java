package Part7.HotelServices;

import java.util.ArrayList;
import java.util.List;

public class HotelService {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public HotelService() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void addRoom(Room room) throws InvalidReservationException {
        if (!room.isValid()) {
            throw new InvalidReservationException("Invalid room data");
        }
        rooms.add(room);
    }

    public Room findRoomByNumber(String roomNumber) throws RoomNotAvailableException {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        throw new RoomNotAvailableException("Room " + roomNumber + " not found");
    }

    public void createReservation(Reservation reservation) throws InvalidReservationException {
        if (!reservation.isValid()) {
            throw new InvalidReservationException("Invalid reservation data");
        }
        reservations.add(reservation);
    }

    public Reservation findReservationById(String reservationId) throws InvalidReservationException {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId)) {
                return reservation;
            }
        }
        throw new InvalidReservationException("Reservation " + reservationId + " not found");
    }

    public List<Room> getAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isBooked()) {
                available.add(room);
            }
        }
        return available;
    }

    public List<Room> getAvailableRoomsByType(String roomType) {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isBooked() && room.getType().equals(roomType)) {
                available.add(room);
            }
        }
        return available;
    }

    public List<Reservation> getReservationsByStatus(String status) {
        List<Reservation> filtered = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getStatus().equals(status)) {
                filtered.add(reservation);
            }
        }
        return filtered;
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }
}
