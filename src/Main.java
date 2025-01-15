public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem employees = new EmployeeManagementSystem();
        employees.addEmployee();
        employees.addEmployee();
        employees.addEmployee();
        employees.getEmployee();
        employees.updateEmployee();
        employees.displayEmployeesFiltered();
        employees.displayEmployeesSorted();
        employees.displayEmployeesByDepartment();
        employees.employeesCount();
    }

}
