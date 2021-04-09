package com.oop.lab2.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(@RequestParam(required = false, defaultValue = "-1") String flightId) {
        Long id = (long) Integer.parseInt(flightId);
        if (id == -1)
            return employeeService.getEmployees();
        return employeeService.getEmployeesByFlightId(id);
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
