package com.oop.lab2.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }


    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        this.employeeService.addEmployee(employee);
    }

    @DeleteMapping(path = "{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping
    public void updateEmployee(@RequestBody Employee employee){
        this.employeeService.updateEmployee(employee);
    }
}
