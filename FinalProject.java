import java.io.*;
import java.util.*;

public class FinalProject{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        // Load existing products from file
        inventory.readProductsFromFile("products.txt");

        boolean running = true;

        while (running) {
            // Display menu options
            System.out.println("\nTech Product Inventory Management System");
            System.out.println("1. Add new product");
            System.out.println("2. Update product stock");
            System.out.println("3. Search product by name");
            System.out.println("4. Sort products by price");
            System.out.println("5. Sort products by stock level");
            System.out.println("6. Display all products");
            System.out.println("7. Save and exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            // Process the user's choice using if-else statements
            if (choice == 1) {
                // Add new product
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();
                System.out.print("Enter product category: ");
                String category = scanner.nextLine();
                System.out.print("Enter stock quantity: ");
                int stock = scanner.nextInt();
                System.out.print("Enter product price: ");
                double price = scanner.nextDouble();
                inventory.addProduct(new Product(name, category, stock, price));
                System.out.println("Product added successfully!");
            } else if (choice == 2) {
                // Update product stock
                System.out.print("Enter product name to update stock: ");
                String name = scanner.nextLine();
                System.out.print("Enter quantity to add or remove (positive/negative): ");
                int amount = scanner.nextInt();
                inventory.updateProductStock(name, amount);
            } else if (choice == 3) {
                // Search for product by name
                System.out.print("Enter product name to search: ");
                String name = scanner.nextLine();
                Product product = inventory.searchProductByName(name);
                if (product != null) {
                    product.displayProduct();
                } else {
                    System.out.println("Product not found.");
                }
            } else if (choice == 4) {
                // Sort by price using QuickSort
                inventory.quickSortByPrice(0, inventory.products.size() - 1);
                System.out.println("Products sorted by price.");
            } else if (choice == 5) {
                // Sort by stock level using QuickSort
                inventory.quickSortByStock(0, inventory.products.size() - 1);
                System.out.println("Products sorted by stock level.");
            } else if (choice == 6) {
                // Display all products
                inventory.displayAllProducts();
            } else if (choice == 7) {
                // Save and exit
                inventory.writeProductsToFile("products.txt");
                System.out.println("Changes saved. Exiting...");
                running = false;
            } else 
            {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
