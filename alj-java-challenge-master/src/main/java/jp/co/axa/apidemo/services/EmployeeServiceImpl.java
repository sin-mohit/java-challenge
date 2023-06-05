package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Employee service class to impletement interface
 */
@CacheConfig(cacheNames={"employee"})  
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Retrieve all employees
     * @return List<Employee>
     */
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    /**
     * Get employee by employeeId
     * @return List<Employee>
     */
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        if (optEmp.isPresent()) {
            return optEmp.get();
        }
        else {
            throw new EmployeeNotFoundException(employeeId);
        }
        
    }

    /**
     * Save employee detail
     */
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    /**
     * Delete employee detail
     */
    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Update employee detail
     */
    public void updateEmployee(Long employeeId, Employee employee) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        
        if (optEmp.isPresent()) {
            Employee currentEmpDetail = optEmp.get();
            currentEmpDetail.setName(employee.getName());
            currentEmpDetail.setDepartment(employee.getDepartment());
            currentEmpDetail.setSalary(employee.getSalary());
            employeeRepository.save(currentEmpDetail);
        } else {
            throw new EmployeeNotFoundException(employeeId);
        }
        
    }
}