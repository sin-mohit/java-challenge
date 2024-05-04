package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * Employee service interface
 */
public interface EmployeeService {

    /**
     * Retrieve all employees
     * @return List<Employee>
     */
    @Cacheable("myCache")
    public List<Employee> getEmployees();

    /**
     * Get employee by employeeId
     * @return List<Employee>
     */
    @Cacheable("myCache")
    public Employee getEmployee(Long employeeId);

    /**
     * Save employee detail
     */
    @CachePut("myCache")
    public void saveEmployee(Employee employee);

    /**
     * Delete employee detail
     */
    @CacheEvict("myCache")
    public void deleteEmployee(Long employeeId);

    /**
     * Update employee detail
     */
    public void updateEmployee(Long employeeId, Employee employee);
}