import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Transaction {
    private String namePerson;
    private double amount;
    private String nameOfTransaction;

    public Transaction(String namePerson, double amount, String nameOfTransaction) {
        this.namePerson = namePerson;
        this.amount = amount;
        this.nameOfTransaction = nameOfTransaction;
    }

    @Override
    public String toString() {
        return namePerson + " " + amount + " " + nameOfTransaction;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public double getAmount() {
        return amount;
    }

    public String getNameOfTransaction() {
        return nameOfTransaction;
    }

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("Alice", 100.0, "Electronics"),
                new Transaction("Bob", 200.0, "Clothing"),
                new Transaction("Alice", 150.0, "Groceries"),
                new Transaction("Charlie", 250.0, "Electronics"),
                new Transaction("Bob", 300.0, "Electronics")
        );

        Map<String, List<String>> categories = transactions.stream().collect(Collectors.groupingBy(
                Transaction::getNameOfTransaction,
                Collectors.mapping(Transaction::toString, Collectors.toList())
        ));

        System.out.println(categories);

        Map<String, Double> amountPerPerson = transactions.stream().collect(Collectors.groupingBy(
                Transaction::getNamePerson,
                Collectors.summingDouble(Transaction::getAmount)
        ));

        System.out.println(amountPerPerson);

        String category = transactions.stream().collect(Collectors.groupingBy(
                Transaction::getNameOfTransaction,
                Collectors.summingDouble(Transaction::getAmount)
        )).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("");

        System.out.println(category);

        String personMostAmount = transactions.stream().collect(Collectors.groupingBy(
                Transaction::getNamePerson,
                Collectors.summingDouble(Transaction::getAmount)
        )).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("");

        System.out.println(personMostAmount);

        String concatenated = transactions.stream().map(Transaction::getNameOfTransaction).collect(Collectors.joining());
        System.out.println(concatenated);

        double avgTransactionAmount = transactions.stream().collect(Collectors.averagingDouble(Transaction::getAmount));
        System.out.println(avgTransactionAmount);

        String maxTransaction = transactions.stream().max(Comparator.comparing(Transaction::getAmount)).map(Transaction::toString).orElse(null);
        System.out.println(maxTransaction);


    }
}
