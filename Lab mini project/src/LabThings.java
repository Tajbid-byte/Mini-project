
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    // Additional getters and setters for other fields can be added if needed.
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

    public double calculateCost(double meters) {
        return pricePerMeter * meters;
    }

    // Getters and Setters omitted for brevity
}

// Supplier Class
class Supplier {
    private String id;
    private String name;
    private String contactInfo;
    private List<Fabric> suppliedFabrics = new ArrayList<>();

    public Supplier(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void addFabric(Fabric fabric) {
        suppliedFabrics.add(fabric);
    }

    public List<Fabric> getSuppliedFabrics() {
        return suppliedFabrics;
    }

    // Getters and Setters omitted for brevity
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
            totalAmount += garment.calculateDiscountPrice(0); // Assuming no discount here
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

    // Getters and Setters omitted for brevity
}

// Customer Class
class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private List<Order> orders = new ArrayList<>();

    public Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public List<Order> viewOrders() {
        return orders;
    }

    // Getters and Setters omitted for brevity
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

    // Getters and Setters omitted for brevity
}

// Main Class
public class LabThings {
    public static void main(String[] args) {
        // Sample data and operations can be added here to test functionality
        Fabric cotton = new Fabric("F001", "Cotton", "White", 10.5);
        Garment shirt = new Garment("G001", "Shirt", "Casual shirt", "M", "Blue", 20.0, 10, cotton);

        Inventory inventory = new Inventory();
        inventory.addGarment(shirt);

        Order order = new Order("O001", new Date());
        order.addGarment(shirt);
        order.printOrderDetails();
    }
}
