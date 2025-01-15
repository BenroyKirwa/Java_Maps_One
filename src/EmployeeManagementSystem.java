import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeManagementSystem {

    Map<String, List<Employee>> departments;

    public EmployeeManagementSystem() {
        this.departments = new HashMap<>();
    }

    public void addEmployee(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter the Employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the Employee number: ");
        String employeeNumber = scanner.nextLine();

        System.out.print("Enter the Department: ");
        String department = scanner.nextLine();


        // Create new Employee object
        Employee newEmployee = new Employee(id, employeeNumber, name, department );

        // Check if department exists, if not create a new list
        if (!departments.containsKey(department)) {
            departments.put(department, new ArrayList<>());
        }

        // Add employee to the department's list
        departments.get(department).add(newEmployee);

        System.out.println("Employee added successfully!");
    }

    public void getEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("The menu for searching for an employees!");
        System.out.println("------------------------------");
        System.out.println("0. Search by Employee ID");
        System.out.println("1. Search by Employee name");
        System.out.println("2. Search by employee number");
        System.out.println("3. Exit");

        System.out.print("What do you want to search by? ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 0:
                System.out.print("Enter Employee ID: ");
                int searchId = scanner.nextInt();
                findEmployeeById(searchId);
                break;

            case 1:
                System.out.print("Enter Employee Name: ");
                String searchName = scanner.nextLine();
                findEmployeeByName(searchName);
                break;

            case 2:
                System.out.print("Enter Employee Number: ");
                String searchNumber = scanner.nextLine();
                findEmployeeByNumber(searchNumber);
                break;

            case 3:
                System.out.println("Exiting search...");
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }

    // Helper methods for searching
    private void findEmployeeById(int id) {
        for (Map.Entry<String, List<Employee>> department : departments.entrySet()) {
            for (Employee emp : department.getValue()) {
                if (emp.getEmployeeId() == id) {
                    System.out.println("Employee found in department: " + department.getKey());
                    System.out.println(emp);
                    return;
                }
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    private void findEmployeeByName(String name) {
        boolean found = false;
        for (Map.Entry<String, List<Employee>> department : departments.entrySet()) {
            for (Employee emp : department.getValue()) {
                if (emp.getName().equalsIgnoreCase(name)) {
                    System.out.println("Employee found in department: " + department.getKey());
                    System.out.println(emp);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Employee with name " + name + " not found.");
        }
    }

    private void findEmployeeByNumber(String number) {
        for (Map.Entry<String, List<Employee>> department : departments.entrySet()) {
            for (Employee emp : department.getValue()) {
                if (emp.getEmployeeNumber().equals(number)) {
                    System.out.println("Employee found in department: " + department.getKey());
                    System.out.println(emp);
                    return;
                }
            }
        }
        System.out.println("Employee with number " + number + " not found.");
    }
    public void updateEmployee() {
        Scanner scanner = new Scanner(System.in);

        // First find the employee to update
        System.out.println("Enter the ID of the employee you want to update: ");
        int searchId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Find the employee and their department
        Employee employeeToUpdate = null;
        String currentDepartment = null;

        // Search for the employee
        for (Map.Entry<String, List<Employee>> entry : departments.entrySet()) {
            for (Employee emp : entry.getValue()) {
                if (emp.getEmployeeId() == searchId) {
                    employeeToUpdate = emp;
                    currentDepartment = entry.getKey();
                    break;
                }
            }
            if (employeeToUpdate != null) break;
        }

        if (employeeToUpdate == null) {
            System.out.println("Employee not found!");
            return;
        }

        // Show current details
        System.out.println("Current employee details:");
        System.out.println(employeeToUpdate);

        // Get updated details
        System.out.print("Enter updated Employee ID (current: " + employeeToUpdate.getEmployeeId() + "): ");
        int newId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter updated Employee name (current: " + employeeToUpdate.getName() + "): ");
        String newName = scanner.nextLine();

        System.out.print("Enter updated Employee number (current: " + employeeToUpdate.getEmployeeNumber() + "): ");
        String newNumber = scanner.nextLine();

        System.out.print("Enter updated Department (current: " + currentDepartment + "): ");
        String newDepartment = scanner.nextLine();

        // Create updated employee
        Employee updatedEmployee = new Employee(newId, newNumber, newName, newDepartment);

        // Handle department change
        if (!newDepartment.equals(currentDepartment)) {
            // Remove from old department
            departments.get(currentDepartment).remove(employeeToUpdate);

            // Add to new department
            if (!departments.containsKey(newDepartment)) {
                departments.put(newDepartment, new ArrayList<>());
            }
            departments.get(newDepartment).add(updatedEmployee);

            // Remove old department if empty
            if (departments.get(currentDepartment).isEmpty()) {
                departments.remove(currentDepartment);
            }
        } else {
            // Update in same department
            int index = departments.get(currentDepartment).indexOf(employeeToUpdate);
            departments.get(currentDepartment).set(index, updatedEmployee);
        }

        System.out.println("Employee updated successfully!");
    }
    public void displayEmployeesByDepartment(){

        if (departments.isEmpty()) {
            System.out.println("No departments or employees to display.");
            return;
        }

        System.out.println("\nEmployees by Department:");
        System.out.println("------------------------");

        departments.forEach((department, employees) -> {
            System.out.println("\nDepartment: " + department);
            System.out.println("Number of Employees: " + employees.size());
            System.out.println("------------------------");

            if (employees.isEmpty()) {
                System.out.println("No employees in this department.");
            } else {
                employees.forEach(System.out::println);
            }
        });

    }
    public void displayEmployeesSorted() {
        Scanner scanner = new Scanner(System.in);

        // Display sorting options
        System.out.println("\nSort employees by:");
        System.out.println("1. Employee ID");
        System.out.println("2. Employee Name");
        System.out.println("3. Employee Number");
        System.out.println("4. Department");

        System.out.print("Enter your choice (1-4): ");
        int sortChoice = scanner.nextInt();

        System.out.println("\nSort order:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.print("Enter your choice (1-2): ");
        int orderChoice = scanner.nextInt();

        // Get all employees into a single list
        List<Employee> allEmployees = departments.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // Create comparator based on choice
        Comparator<Employee> comparator = switch (sortChoice) {
            case 1 -> Comparator.comparing(Employee::getEmployeeId);
            case 2 -> Comparator.comparing(Employee::getName);
            case 3 -> Comparator.comparing(Employee::getEmployeeNumber);
            case 4 -> Comparator.comparing(Employee::getDepartment);
            default -> {
                System.out.println("Invalid choice! Sorting by ID by default.");
                yield Comparator.comparing(Employee::getEmployeeId);
            }
        };

        // Apply descending order if chosen
        if (orderChoice == 2) {
            comparator = comparator.reversed();
        }

        // Sort and display
        System.out.println("\nSorted Employees:");
        System.out.println("----------------");

        allEmployees.stream()
                .sorted(comparator)
                .forEach(emp -> System.out.println(emp));
    }
    public void displayEmployeesFiltered() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nFilter employees by:");
        System.out.println("1. Employee ID (greater than)");
        System.out.println("2. Employee Name (contains)");
        System.out.println("3. Employee Number (starts with)");
        System.out.println("4. Department (exact match)");

        System.out.print("Enter your choice (1-4): ");
        int filterChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Get all employees into a single list
        List<Employee> allEmployees = departments.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        switch (filterChoice) {
            case 1:
                System.out.print("Enter minimum Employee ID: ");
                int minId = scanner.nextInt();

                allEmployees.stream()
                        .filter(emp -> emp.getEmployeeId() > minId)
                        .forEach(System.out::println);
                break;

            case 2:
                System.out.print("Enter name to search: ");
                String nameSearch = scanner.nextLine();

                allEmployees.stream()
                        .filter(emp -> emp.getName().toLowerCase().contains(nameSearch.toLowerCase()))
                        .forEach(System.out::println);
                break;

            case 3:
                System.out.print("Enter employee number prefix: ");
                String numberPrefix = scanner.nextLine();

                allEmployees.stream()
                        .filter(emp -> emp.getEmployeeNumber().startsWith(numberPrefix))
                        .forEach(System.out::println);
                break;

            case 4:
                System.out.print("Enter department name: ");
                String deptName = scanner.nextLine();

                allEmployees.stream()
                        .filter(emp -> emp.getDepartment().equalsIgnoreCase(deptName))
                        .forEach(System.out::println);
                break;

            default:
                System.out.println("Invalid choice!");
        }
        scanner.close();
    }
    public void employeesCount(){
        if (departments.isEmpty()) {
            System.out.println("There are no departments available.");
        }
        else {
            // Display the total number of employees in each department.
            departments.forEach((department, employees) -> {
                System.out.println("\nDepartment: " + department);
                System.out.println("Number of Employees: " + employees.size());
            });
            // Display the overall total number of employees.
            int totalEmployees = departments.values().stream().mapToInt(List::size).sum();
            System.out.println("The total number of employees is : " + totalEmployees);

        }
    }

}
