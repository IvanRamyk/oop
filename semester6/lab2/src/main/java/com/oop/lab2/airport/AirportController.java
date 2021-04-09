package com.oop.lab2.airport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAirports() {
        return airportService.getAirports();
    }


    @PostMapping
    public void addAirport(@RequestBody Airport airport) {
        this.airportService.addAirport(airport);
    }

    @DeleteMapping(path = "{airportId}")
    public void deleteAirport(@PathVariable("airportId") Long id) {
        airportService.deleteStudent(id);
    }

    @PutMapping
    public void updateAirport(@RequestBody Airport airport){
        this.airportService.updateEmployee(airport);
    }
}
