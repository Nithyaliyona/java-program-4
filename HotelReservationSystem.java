import java.util.*;

class Main {
    private String roomNumber;
    private String category;
    private boolean isAvailable;
    private double price;

    public Room(String roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
        this.price = price;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void reserve() {
        isAvailable = false;
    }

    public void release() {
        isAvailable = true;
    }

    public double getPrice() {
        return price;
    }
}

class Reservation {
    private String guestName;
    private Room room;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(String guestName, Room room, String checkInDate, String checkOutDate) {
        this.guestName = guestName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public void displayReservation() {
        System.out.println("=== Reservation Details ===");
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Room Category: " + room.getCategory());
        System.out.println("Check-in Date: " + checkInDate);
        System.out.println("Check-out Date: " + checkOutDate);
        System.out.println("Total Price: $" + room.getPrice());
        System.out.println("===========================");
    }
}

public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    static {
        // Sample rooms
        rooms.add(new Room("101", "Single", 100.0));
        rooms.add(new Room("102", "Double", 150.0));
        rooms.add(new Room("201", "Suite", 250.0));
        rooms.add(new Room("202", "Double", 150.0));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== Hotel Reservation System ===");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }

    private static void searchAvailableRooms() {
        System.out.println("=== Available Rooms ===");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Category: " + room.getCategory() + ", Price: $" + room.getPrice());
            }
        }
        System.out.println("=======================");
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();
        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber) && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            String checkInDate = scanner.nextLine();
            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            String checkOutDate = scanner.nextLine();

            selectedRoom.reserve();
            Reservation reservation = new Reservation(guestName, selectedRoom, checkInDate, checkOutDate);
            reservations.add(reservation);
            System.out.println("Reservation made successfully!");
        } else {
            System.out.println("Room is either not available or does not exist.");
        }
    }

    private static void viewReservations() {
        System.out.println("=== Current Reservations ===");
        for (Reservation reservation : reservations) {
            reservation.displayReservation();
        }
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        }
        System.out.println("============================");
    }
}
