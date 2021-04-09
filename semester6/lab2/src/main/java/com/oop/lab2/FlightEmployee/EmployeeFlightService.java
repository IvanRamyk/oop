package com.oop.lab2.FlightEmployee;


import com.oop.lab2.airport.Airport;
import com.oop.lab2.flight.Flight;
import com.oop.lab2.flight.FlightInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
public class EmployeeFlightService {
    private final EmployeeFlightRepository repository;

    public EmployeeFlightService(EmployeeFlightRepository repository) {
        this.repository = repository;
    }


    public void addFlightEmployee(EmployeeFlight employeeFlight) {
        repository.save(employeeFlight);
    }

    public void deleteFlight(EmployeeFlight employeeFlight) {
        repository.delete(employeeFlight);
    }


}