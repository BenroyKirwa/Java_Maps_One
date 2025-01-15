# Java Maps One
## Create an Employee class with the following fields and include appropriate constructors, getters, setters: 
```
Int employeeId, String employeeNumber, String name, String department
```
## Create a Map, Map<String, List<Employee>>, where: 
```
- The key is the department name (String). 
- The value is a List<Employee> represen ng all employees in that department.
``` 
### The following are a list of func onali es to achieve: 
1. Add New Employee
```
Add a new Employee to the appropriate department
If the department does not exist, create a new entry in the Map
```
2. Get Employee
``` 
Search for an employee by id, number or name across all departments.
```
3. Update Employee Details
``` 
Update the details of a particular employee. The department can also be updated.
```
4. Delete Employee
``` 
Remove an employee by a certain property. 
If the department becomes empty after removal, delete the department from the map.
``` 
5.
```
Display Employees in all departments, group by department
``` 
6. Display Employees Ordered By a certain property either Ascending or Descending  
7. Display Employees Filtered By a certain property. Use streams and filters to achieve the filtering, and not looping through the list. 
8. Count Employees 
```
Display the total number of employees in each department 
Display the overall total number of employees
```
