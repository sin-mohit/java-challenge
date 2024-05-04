package jp.co.axa.apidemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * Application service test class
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

    @Autowired
    private EmployeeService employeeService;
    
    @MockBean
    private EmployeeRepository employeeRepository;
    
    private List<Employee> mockEmployees;
    
    @Before
    public void setUp() {
        // Mock data setup
        mockEmployees = new ArrayList<Employee>();
        mockEmployees.add(new Employee(Long.valueOf(1), "Mohit", 30000, "IT"));
        mockEmployees.add(new Employee(Long.valueOf(2), "Rohit", 32000, "HR"));
        
        when(employeeRepository.findAll()).thenReturn(mockEmployees);
        when(employeeRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(mockEmployees.get(0)));
        when(employeeRepository.findById(Long.valueOf(3))).thenReturn(Optional.empty());
    }

    /**
     * Test for get employee by id
     */
    @Test
    public void testGetEmployeeById() {        
        // When
        Employee retrievedEmp = employeeService.getEmployee(Long.valueOf(1));

        // Then 
        assertNotNull(retrievedEmp);
        assertEquals(Long.valueOf(1), retrievedEmp.getId());
        assertEquals("Mohit", retrievedEmp.getName());
        assertEquals(Integer.valueOf(30000), retrievedEmp.getSalary());
        assertEquals("IT", retrievedEmp.getDepartment());
    }
    
    /**
     * Test for get all employee
     */
    @Test
    public void testGetEmployees() {
        // When
        List<Employee> retrievedAllEmp = employeeService.getEmployees();

        // Then 
        assertEquals(2, retrievedAllEmp.size());
        assertEquals("Mohit", retrievedAllEmp.get(0).getName());
        assertEquals("Rohit", retrievedAllEmp.get(1).getName());
    }
    
    /**
     * Test when employee not found
     */
    @Test(expected = EmployeeNotFoundException.class)
    public void testGetEmployee_NotFound() throws EmployeeNotFoundException {
        // When
        employeeService.getEmployee(Long.valueOf(3));

        // Then expect EmployeeNotFoundException to be thrown
    }
    
    /**
     * Test for update employee
     */
    @Test
    public void testUpdateEmployee() {
        // Given
        Employee employeeToUpdate = new Employee(Long.valueOf(1), "Mohit", 40000, "Infra");
        
        // When
        employeeService.updateEmployee(employeeToUpdate.getId(), employeeToUpdate);

        // Then 
        assertEquals("Mohit", mockEmployees.get(0).getName());
        assertEquals(Integer.valueOf(40000), mockEmployees.get(0).getSalary());
        assertEquals("Infra", mockEmployees.get(0).getDepartment());
    }
    
    /**
     * Test for save employee
     */
    @Test
    public void testSaveEmployee() {
        // Given
        Employee newEmployee = new Employee(Long.valueOf(3), "Pallav", 50000, "Infra");
        
        // When
        employeeService.saveEmployee(newEmployee);

        // Then
        verify(employeeRepository, times(1)).save(newEmployee);      
    }
    
    /**
     * Test delete employee by id
     */
    @Test
    public void testDeleteById() {        
        // When
        employeeService.deleteEmployee(Long.valueOf(2));

        // Then
        verify(employeeRepository, times(1)).deleteById(Long.valueOf(2));
    }
}
