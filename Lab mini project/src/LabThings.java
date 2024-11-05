import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Garment Class
class Garment {
    private String id;
    private String name;
    private String description;
    private String size;
    private String color;
    private double price;
    private int stockQuantity;
    private Fabric fabric;

    public Garment(String id, String name, String description, String size, String color, double price, int stockQuantity, Fabric fabric) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.fabric = fabric;
    }

    public void updateStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public double calculateDiscountPrice(double discountPercentage) {
        return price - (price * discountPercentage / 100);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }
}

// Fabric Class
class Fabric {
    private String id;
    private String type;
    private String color;
    private double pricePerMeter;

    public Fabric(String id, String type, String color, double pricePerMeter) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.pricePerMeter = pricePerMeter;
    }
}

// Order Class
class Order {
    private String orderId;
    private Date orderDate;
    private List<Garment> garments = new ArrayList<>();
    private double totalAmount;

    public Order(String orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public void addGarment(Garment garment) {
        garments.add(garment);
    }

    public double calculateTotalAmount() {
        totalAmount = 0;
        for (Garment garment : garments) {
            totalAmount += garment.calculateDiscountPrice(0); 
        }
        return totalAmount;
    }

    public void printOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Total Amount: $" + calculateTotalAmount());
        System.out.println("Garments in order:");
        for (Garment garment : garments) {
            System.out.println("- " + garment.getName() + " (" + garment.getSize() + ", " + garment.getColor() + ")");
        }
    }
}

// Inventory Class
class Inventory {
    private List<Garment> garments = new ArrayList<>();

    public void addGarment(Garment garment) {
        garments.add(garment);
    }

    public void removeGarment(String id) {
        garments.removeIf(garment -> garment.getId().equals(id));
    }

    public Garment findGarment(String id) {
        for (Garment garment : garments) {
            if (garment.getId().equals(id)) {
                return garment;
            }
        }
        return null;
    }

    public void listGarments() {
        for (Garment garment : garments) {
            System.out.println("ID: " + garment.getId() + ", Name: " + garment.getName() + ", Size: " + garment.getSize() + ", Color: " + garment.getColor());
        }
    }
}

// Main Class
public class LabThings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        
        // Adding sample data
        Fabric cotton = new Fabric("F001", "Cotton", "White", 10.5);
        Garment shirt = new Garment("G001", "Shirt", "Casual shirt", "M", "Blue", 20.0, 10, cotton);
        inventory.addGarment(shirt);

        while (true) {
            System.out.println("\n--- Garment Management System ---");
            System.out.println("1. Add Garment to Inventory");
            System.out.println("2. List Garments");
            System.out.println("3. Place Order");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Add Garment
                    System.out.print("Enter Garment ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Size: ");
                    String size = scanner.nextLine();
                    System.out.print("Enter Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Stock Quantity: ");
                    int stockQuantity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    Garment newGarment = new Garment(id, name, description, size, color, price, stockQuantity, cotton);
                    inventory.addGarment(newGarment);
                    System.out.println("Garment added successfully!");
                    break;

                case 2:
                    // List Garments
                    System.out.println("\n--- Garment Inventory ---");
                    inventory.listGarments();
                    break;

                case 3:
                    // Place Order
                    System.out.print("Enter Order ID: ");
                    String orderId = scanner.nextLine();
                    Order order = new Order(orderId, new Date());
                    System.out.print("Enter Garment ID to add to order: ");
                    String garmentId = scanner.nextLine();
                    Garment garment = inventory.findGarment(garmentId);
                    
                    if (garment != null) {
                        order.addGarment(garment);
                        order.printOrderDetails();
                    } else {
                        System.out.println("Garment not found!");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
