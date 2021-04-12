package com.oop.lab2.flight;

import static org.junit.jupiter.api.Assertions.*;

import com.oop.lab2.airport.Airport;
import com.oop.lab2.employee.Employee;
import com.oop.lab2.employee.EmployeeService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    private FlightService service;
    @Test
    void checkUpdate() {
        LocalDateTime now = LocalDateTime.now();
        Flight newVersion = new Flight(2L, new Airport(2L, "A", "A", "A"), new Airport(3L, "B", "B", "B"), now);
        Flight oldVersion = new Flight(2L, new Airport(2L, "C", "C", "C"), new Airport(3L, "B", "B", "B"), now);
        service.updateFlightProps(oldVersion, newVersion);
        assertEquals(oldVersion, newVersion);
    }

}