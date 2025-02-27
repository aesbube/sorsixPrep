import java.util.*;
import java.util.stream.Collectors;

public class Employee {
    private String empName;
    private String position;
    private int salary;

    public Employee(String empName, String position, int salary) {
        this.empName = empName;
        this.position = position;
        this.salary = salary;
    }

    public String getEmpName() {
        return empName;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empName='" + empName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static List<Employee> randomEmployees() {
        List<String> positions = List.of("Manager", "HR", "Developer", "Designer");
        Random random = new Random();
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String name = "Employee " + (i + 1);
            String position = positions.get(random.nextInt(positions.size()));
            int salary = random.nextInt(1000, 5000);
            employees.add(new Employee(name, position, salary));
        }
        return employees;
    }

    public static void main(String[] args) {
        List<Employee> employees = Employee.randomEmployees();

        List<String> employeesNames = employees.stream().map(Employee::getEmpName).toList();
        System.out.println(employeesNames);

        Map<String, Integer> salariesPerDepartment = employees.stream().collect(Collectors.groupingBy(
                Employee::getPosition,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
                        employee -> employee.map(Employee::getSalary).orElse(0)
                )
        ));

        System.out.println(salariesPerDepartment);

        int salaryTotal = employees.stream().mapToInt(Employee::getSalary).sum();
        System.out.println(salaryTotal);

        List<String> departments = employees.stream().collect(Collectors.groupingBy(
                Employee::getPosition,
                Collectors.summingInt(Employee::getSalary)
        )).entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).toList();

        System.out.println(departments);
    }
}
