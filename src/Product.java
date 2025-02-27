import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Laptop", 1200.0),
                new Product("Phone", 800.0),
                new Product("Tablet", 600.0),
                new Product("Smartwatch", 300.0)
        );

        List<String> products2 = products.stream().sorted(Comparator.comparing(Product::getName)).map(product -> product.name).toList();
        System.out.println(products2);

        List<String> productsPrice = products.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).map(product -> product.name).toList();
        System.out.println(productsPrice);

        String mostExpensive = products.stream().max(Comparator.comparing(Product::getPrice)).map(product -> product.name).orElse(null);
        System.out.println(mostExpensive);

        String leastExpensive = products.stream().min(Comparator.comparing(Product::getPrice)).map(product -> product.name).orElse(null);
        System.out.println(leastExpensive);


    }
}
