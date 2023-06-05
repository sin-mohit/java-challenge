package jp.co.axa.apidemo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * Application service test class
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Get employee by id test
     */
    @Test
    public void testFindById() {
       Employee employee = getEmployee();         
       employeeService.saveEmployee(employee);
       Employee result = employeeService.getEmployee(employee.getId());
       assertEquals(employee.getId(), result.getId());        
    }
    
    /**
     * Get all employee test
     */
    @Test
    public void testFindAll() {
       Employee employee = getEmployee();
       employeeService.saveEmployee(employee);
       List<Employee> result = new ArrayList<>();
       employeeService.retrieveEmployees().forEach(e -> result.add(e));
       assertEquals(result.size(), 1);        
    }
    
    /**
     * Save employee test
     */
    @Test
    public void testSave() {
       Employee employee = getEmployee();
       employeeService.saveEmployee(employee);
       Employee found = employeeService.getEmployee(employee.getId());
       assertEquals(employee.getId(), found.getId());         
    }
    
    /**
     * Delete employee by id test
     */
    @Test
    public void testDeleteById() {
       Employee employee = getEmployee();
       employeeService.saveEmployee(employee);
       employeeService.deleteEmployee(employee.getId());
       List<Employee> result = new ArrayList<>();
       employeeService.retrieveEmployees().forEach(e -> result.add(e));
       assertEquals(result.size(), 0);
    }
    
    /**
     * Set employee detail for test
     */
    private Employee getEmployee() {
       Employee employee = new Employee();
       employee.setId(Long.valueOf(1));
       employee.setName("Mohit");
       employee.setDepartment("IT");;
       employee.setSalary(30000);
       return employee;
    }

}
