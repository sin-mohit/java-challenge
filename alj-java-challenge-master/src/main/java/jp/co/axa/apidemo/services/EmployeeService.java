package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

/**
 * Employee service interface
 */
public interface EmployeeService {

    /**
     * Retrieve all employees
     * @return List<Employee>
     */
    public List<Employee> retrieveEmployees();

    /**
     * Get employee by employeeId
     * @return List<Employee>
     */
    public Employee getEmployee(Long employeeId);

    /**
     * Save employee detail
     */
    public void saveEmployee(Employee employee);

    /**
     * Delete employee detail
     */
    public void deleteEmployee(Long employeeId);

    /**
     * Update employee detail
     */
    public void updateEmployee(Long employeeId, Employee employee);
}