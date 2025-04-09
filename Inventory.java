import java.util.*;
import java.io.*;

public class Inventory
{
    List<Product> products = new ArrayList<>();
    
    public void addProduct(Product product)
    {
        products.add(product);
    }
    
    public void updateProductStock(String name, int amount)
    {
        for (Product product : products)
        {
            if (product.name.equalsIgnoreCase(name))
            {
                product.updateStock(amount);
                System.out.println("Updated stock for " + product.name);
                return;
            }
        }
        System.out.println("Product not found!");
    }
    
    public Product searchProductByName(String name)
    {
        products.sort(Comparator.comparing(p -> p.name));
        
        int index = binarySearch(products, name);
        
        if (index >= 0)
        {
            return products.get(index);
        }
        else
        {
            return null;
        }
    }

    
    public static int binarySearch(List<Product> list, String target)
    {
    int left = 0;
    int right = list.size() -1;
    
    while (left <= right)
    {
        int mid = left + (right - left) / 2;
        Product midProduct = list.get(mid);
        
        int comparison = midProduct.name.compareToIgnoreCase(target);
        
        if (comparison == 0)
        {
            return mid;
        } 
        else if (comparison < 0)
        {
            left = mid + 1;
        }
        else 
        {
            right = mid - 1;
        }
    }
    return -1;
    }   
    
    public void quickSortByPrice(int low, int high)
    {
        if (low < high)
        {
            int pi = partitionByPrice(low, high);
            quickSortByPrice(low, pi -1);
            quickSortByPrice(pi + 1, high);
        }
    }
    
    private int partitionByPrice(int low, int high)
    {
        double pivot = products.get(high).price;
        int i = low - 1;
        for (int j = low; j < high; j++)
        {
            if(products.get(j).price <= pivot)
            {
                i++;
                Collections.swap(products, i, j);
            }
        }
        Collections.swap(products, i + 1, high);
        return i + 1;
    }
    
    public void quickSortByStock (int low, int high)
    {
        if (low < high)
        {
            int pi = partitionByStock(low, high);
            quickSortByStock(low, pi - 1);
            quickSortByStock(pi + 1, high);
        }
    }
    
    private int partitionByStock(int low, int high)
    {
        int pivot = products.get(high).stock;
        int i = low - 1;
        for (int j = low; j < high; j++)
        {
            if (products.get(j).stock <= pivot)
            {
                i++;
                Collections.swap(products, i, j);
            }
        }
        Collections.swap(products, i + 1, high);
        return i + 1;
    }
    
    public void displayAllProducts()
    {
        for (Product product : products)
        {
            product.displayProduct();
        }
    }
    
    public void readProductsFromFile(String filename) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filename)); 
        String line;
        while ((line = reader.readLine()) != null)
        {
            String[] details = line.split(",");
            String name = details[0].trim();
            String category = details[1].trim();
            int stock = Integer.parseInt(details[2].trim());
            double price = Double.parseDouble(details[3].trim());
            addProduct(new Product(name, category, stock, price));
        }
        reader.close();
    }
    
    public void writeProductsToFile(String filename) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Product product : products)
        {
            writer.write(product.name + "," + product.category + "," + product.stock + "," + product.price + "\n");
        }
        writer.close();
    }
}
