package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

/**
 * Employee Controller 
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieve all employees
     * @return List<Employee>
     */
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    /**
     * Retrieve employee by employeeId
     * @return Employee
     */
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) throws EmployeeNotFoundException {
        return employeeService.getEmployee(employeeId);
    }

    /**
     * Save employee
     */
    @PostMapping("/employees")
    public void saveEmployee(Employee employee){
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
    }

    /**
     * Delete employee
     */
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
    }

    /**
     * Update employee detail
     */
    @PutMapping(path = "/employees/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEmployee(@RequestBody @Valid Employee employee,
                               @PathVariable(name="employeeId")Long employeeId) {
        if(Objects.nonNull(employee) && Objects.nonNull(employeeId)){
            employeeService.updateEmployee(employeeId, employee);
        }

    }

}
