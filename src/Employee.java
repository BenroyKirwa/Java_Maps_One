import java.util.Objects;

public class Employee {
    private int employeeId;
    private String employeeNumber, name, department;

    public Employee(){}
    public Employee(int employeeId, String employeeNumber, String name, String department){
        this.employeeId = employeeId;
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.department = department;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    // Overriding toString() method for proper object printing
    @Override
    public String toString(){
        return "Employee{id=" + employeeId + ", name='" + name + "', employeeNumber='" +
                employeeNumber + "', department=" + department;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && Objects.equals(employeeNumber, employee.employeeNumber) && Objects.equals(name, employee.name) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeNumber, name, department);
    }
}