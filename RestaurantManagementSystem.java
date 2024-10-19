import java.util.*;

// Class representing a Menu Item
class MenuItem {
    String name;
    double price;

    MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return name + " - Rs. " + price;
    }
}

// Class representing an Order
class Order {
    ArrayList<MenuItem> items = new ArrayList<>();

    void addItem(MenuItem item) {
        items.add(item);
    }

    double calculateTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.price;
        }
        return total;
    }

    void viewOrder() {
        System.out.println("Order Summary:");
        for (MenuItem item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Total Amount: Rs. " + calculateTotal());
    }
}

// Class representing a Table Reservation
class Reservation {
    int tableNumber;
    boolean isReserved;

    Reservation(int tableNumber) {
        this.tableNumber = tableNumber;
        this.isReserved = false;
    }

    void reserve() {
        if (!isReserved) {
            isReserved = true;
            System.out.println("Table " + tableNumber + " reserved successfully.");
        } else {
            System.out.println("Table " + tableNumber + " is already reserved.");
        }
    }

    void cancelReservation() {
        if (isReserved) {
            isReserved = false;
            System.out.println("Reservation for Table " + tableNumber + " cancelled.");
        } else {
            System.out.println("Table " + tableNumber + " is not reserved.");
        }
    }
}

// Class representing Inventory Items
class InventoryItem {
    String itemName;
    int quantity;

    InventoryItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String toString() {
        return itemName + " - " + quantity + " units";
    }
}

// Class representing Staff
class Staff {
    String name;
    String role;
    String staffID;

    Staff(String name, String role, String staffID) {
        this.name = name;
        this.role = role;
        this.staffID = staffID;
    }

    public String toString() {
        return "Name: " + name + ", Role: " + role + ", Contact: " + staffID;
    }
}

// Main Restaurant Management System
public class RestaurantManagementSystem {
    private static ArrayList<MenuItem> menu = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static ArrayList<InventoryItem> inventory = new ArrayList<>();
    private static ArrayList<Staff> staffList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample data
        menu.add(new MenuItem("Pizza", 250));
        menu.add(new MenuItem("Burger", 120));
        menu.add(new MenuItem("Pasta", 200));
        menu.add(new MenuItem("Maggi", 200));
        reservations.add(new Reservation(1));
        reservations.add(new Reservation(2));
        reservations.add(new Reservation(3));
        reservations.add(new Reservation(4));
        reservations.add(new Reservation(5));
        staffList.add(new Staff("Vijay Patil", "Manager", "777-ITB2-025"));
        staffList.add(new Staff("Dia Walunj", "Head Chef", "777-ITB2-064"));
        staffList.add(new Staff("Om Pawar", "Supervisor", "777-ITB2-030"));
        staffList.add(new Staff("Yash Pawar", "Server", "777-ITB2-032"));



        boolean running = true;

        while (running) {
            System.out.println("\n1. View Menu");
            System.out.println("2. Create Order");
            System.out.println("3. Reserve Table");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. View Inventory");
            System.out.println("6. Add Inventory Item");
            System.out.println("7. Manage Staff");
            System.out.println("8. Generate Report");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> viewMenu();
                case 2 -> createOrder();
                case 3 -> reserveTable();
                case 4 -> cancelReservation();
                case 5 -> viewInventory();
                case 6 -> addInventoryItem();
                case 7 -> manageStaff();
                case 8 -> generateReport();
                case 9 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewMenu() {
        System.out.println("\nMenu:");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }

    private static void createOrder() {
        Order order = new Order();
        while (true) {
            System.out.print("\nEnter item name to add to order (or 'done' to finish): ");
            String itemName = scanner.nextLine();
            if (itemName.equalsIgnoreCase("done")) break;

            MenuItem item = findMenuItem(itemName);
            if (item != null) {
                order.addItem(item);
                System.out.println(itemName + " added to order.");
            } else {
                System.out.println("Item not found in the menu.");
            }
        }
        orders.add(order);
        order.viewOrder();
        double totalAmount = order.calculateTotal();
        processPayment(totalAmount);
    }

    private static void processPayment(double totalAmount) {
        System.out.println("Total Amount to Pay: Rs. " + totalAmount);
        System.out.println("Choose Payment Method:");
        System.out.println("1. Cash");
        System.out.println("2. UPI");
        System.out.println("3. Credit Card");
        System.out.print("Enter your choice: ");
        int paymentChoice = scanner.nextInt();

        switch (paymentChoice) {
            case 1 -> System.out.println("Payment successful via Cash.");
            case 2 -> System.out.println("Payment successful via UPI.");
            case 3 -> System.out.println("Payment successful via Credit Card.");
            default -> System.out.println("Invalid payment option. Try again.");
        }
    }

    private static MenuItem findMenuItem(String name) {
        for (MenuItem item : menu) {
            if (item.name.equalsIgnoreCase(name)) return item;
        }
        return null;
    }

    private static void reserveTable() {
        System.out.print("Enter table number to reserve: ");
        int tableNumber = scanner.nextInt();
        Reservation reservation = findReservation(tableNumber);
        if (reservation != null) {
            reservation.reserve();
        } else {
            System.out.println("Table not found.");
        }
    }

    private static void cancelReservation() {
        System.out.print("Enter table number to cancel reservation: ");
        int tableNumber = scanner.nextInt();
        Reservation reservation = findReservation(tableNumber);
        if (reservation != null) {
            reservation.cancelReservation();
        } else {
            System.out.println("Table not found.");
        }
    }

    private static Reservation findReservation(int tableNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.tableNumber == tableNumber) return reservation;
        }
        return null;
    }

    private static void viewInventory() {
        System.out.println("\nInventory:");
        for (InventoryItem item : inventory) {
            System.out.println(item);
        }
    }

    private static void addInventoryItem() {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        inventory.add(new InventoryItem(itemName, quantity));
        System.out.println(itemName + " added to inventory.");
    }

    private static void manageStaff() {
        System.out.println("\n1. View Staff");
        System.out.println("2. Add Staff");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> viewStaff();
            case 2 -> addStaff();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void viewStaff() {
        System.out.println("\nStaff List:");
        for (Staff staff : staffList) {
            System.out.println(staff);
        }
    }

    private static void addStaff() {
        System.out.print("Enter staff name: ");
        String name = scanner.nextLine();
        System.out.print("Enter role: ");
        String role = scanner.nextLine();
        System.out.print("Enter contact info: ");
        String contact = scanner.nextLine();
        staffList.add(new Staff(name, role, contact));
        System.out.println("Staff added successfully.");
    }

    private static void generateReport() {
        System.out.println("\n--- Restaurant Report ---");
        System.out.println("Total Orders: " + orders.size());
        System.out.println("Total Reservations: " + reservations.size());
        System.out.println("Inventory Items: " + inventory.size());
        System.out.println("Staff Members: " + staffList.size());
    }
}
