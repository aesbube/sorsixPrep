import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private int age;
    private String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Alice", 30, "New York"),
                new Person("Bob", 20, "Los Angeles"),
                new Person("Charlie", 25, "New York"),
                new Person("Dave", 35, "Chicago"),
                new Person("Eve", 40, "Los Angeles")
        );

        List<String> people2 = people.stream().filter(person -> person.age >= 30).map(person -> person.name).collect(Collectors.toList());
        List<String> cities = people.stream().map(person -> person.city).collect(Collectors.toSet()).stream().toList();
        Boolean isTherePerson = people.stream().anyMatch(person -> person.age >= 20 && person.age < 30);
        String newYork = people.stream().filter(person -> person.city.contains("New York")).map(Person::toString).findFirst().orElse(null);

        System.out.println(people2);
        System.out.println(cities);
        System.out.println(isTherePerson);
        System.out.println(newYork);

        Map<String, List<String>> peopleCity = people.stream().collect(Collectors.groupingBy(
                Person::getCity,
                Collectors.mapping(Person::getName, Collectors.toList())
        ));
        System.out.println(peopleCity);

        Map<String, Integer> numPplCity = people.stream().collect(Collectors.groupingBy(
                Person::getCity,
                Collectors.summingInt(person -> 1)
        ));
        System.out.println(numPplCity);

        Map<String, List<Person>> ageGroups = people.stream()
                .collect(Collectors.groupingBy(person -> {
                    int age = person.getAge();
                    if (age <= 20) {
                        return "0-20";
                    } else if (age <= 30) {
                        return "21-30";
                    } else if (age <= 40) {
                        return "31-40";
                    } else {
                        return "41+";
                    }
                }));
        System.out.println(ageGroups);

        Boolean allPplLegal = people.stream().allMatch(person -> person.getAge() >= 18);
        System.out.println(allPplLegal);

        Boolean miamiLives = people.stream().noneMatch(person -> person.getCity().contains("Miami"));
        System.out.println(miamiLives);

        String person30 = people.stream().filter(person -> person.getAge() >= 30 && person.getAge() < 40).findFirst().map(Person::getName).orElse(null);
        System.out.println(person30);

        String oldest = people.stream().max(Comparator.comparing(Person::getAge)).map(Person::getName).orElse(null);
        System.out.println(oldest);
    }
}