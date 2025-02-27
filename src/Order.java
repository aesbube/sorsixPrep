import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    int orderID;
    List<String> items;

    public Order(int orderID, List<String> items) {
        this.orderID = orderID;
        this.items = items;
    }

    public int getOrderID() {
        return orderID;
    }

    public List<String> getItems() {
        return items;
    }

    public int numItems() {
        return items.size();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", items=" + items +
                '}';
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order(1, List.of("Item A", "Item B")),
                new Order(2, List.of("Item C")),
                new Order(3, List.of("Item A", "Item C", "Item D"))
        );

        List<String> items = orders.stream().map(Order::getItems).flatMap(List::stream).toList();
        System.out.println(items);

        Map<String, Integer> itemsOrdered = orders.stream()
                .map(Order::getItems)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        item -> item,
                        Collectors.summingInt(item -> 1)
                ));

        System.out.println(itemsOrdered);

        String orderMostItems = orders.stream().max(Comparator.comparing(Order::numItems)).map(Order::toString).orElse(null);
        System.out.println(orderMostItems);


        List<String> words = List.of("apple", "banana", "car", "dog", "elephant", "fish");

        Map<Integer, List<String>> wordsMap = words.stream().collect(Collectors.groupingBy(
                String::length,
                Collectors.mapping(String::toString, Collectors.toList())
        ));

        System.out.println(wordsMap);

    }
}
