package Demo;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Employee {
    private final String name;
    private final String department;
    private final int salary;

    public Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getSalary() { return salary; }
}

public class EmployeePipeline {

    public static List<String> process(List<Employee> employees,
                                       Predicate<Employee> filter,
                                       Comparator<Employee> order,
                                       Function<Employee, String> mapper) {
        return employees.stream()
                .filter(filter)
                .sorted(order)
                .map(mapper)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Amit", "IT", 60000),
            new Employee("Riya", "IT", 75000),
            new Employee("Karan", "HR", 50000),
            new Employee("Zara", "Finance", 80000),
            new Employee("Neha", "IT", 65000),
            new Employee("Ravi", "HR", 55000)
        );

        Predicate<Employee> isIT = e -> e.getDepartment().equals("IT");
        Predicate<Employee> highPaid = e -> e.getSalary() >= 65000;
        Comparator<Employee> bySalaryDesc = Comparator.comparingInt(Employee::getSalary).reversed();
        Comparator<Employee> byName = Comparator.comparing(Employee::getName);

        System.out.println("IT by salary desc: " + String.join(", ", 
            process(employees, isIT, bySalaryDesc, Employee::getName)));

        System.out.println("IT and high-paid: " + String.join(", ", 
            process(employees, isIT.and(highPaid), bySalaryDesc, Employee::getName)));

        System.out.println("Not IT (by name): " + String.join(", ", 
            process(employees, isIT.negate(), byName, Employee::getName)));

        System.out.println("All names: " + String.join(", ", 
            employees.stream().map(Employee::getName).collect(Collectors.toList())));
    }
}