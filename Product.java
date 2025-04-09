public class Product {
    String name;
    String category;
    int stock;
    double price;

    // Constructor
    public Product(String name, String category, int stock, double price) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    // Update product stock
    public void updateStock(int amount) {
        stock += amount;
    }

    // Display product details
    public void displayProduct() {
        System.out.println(name + " | Category: " + category + " | Stock: " + stock + " | Price: $" + price);
    }
}
