package com.oop.lab2.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    private EmployeeService service;
    @Test
    void checkUpdate() {
        Employee newVersion = new Employee(2L, "A", "A position");
        Employee oldVersion = new Employee(2L, "B", "B position");
        service.updateEmployeeProps(oldVersion, newVersion);
        assertEquals(oldVersion, newVersion);
    }

}