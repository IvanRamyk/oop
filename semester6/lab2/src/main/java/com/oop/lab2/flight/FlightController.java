package com.oop.lab2.flight;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getFlights() {
        System.out.println("Somebody to love!");
        return flightService.getFlights();
    }


    @PostMapping
    public void addFlight(@RequestBody FlightInfo flight) {
        this.flightService.addFlight(flight);
    }

    @DeleteMapping(path = "{flightId}")
    public void deleteFlight(@PathVariable("flightId") Long id) {
        flightService.deleteFlight(id);
    }

    @PutMapping
    public void updateFlight(@RequestBody FlightInfo flight){
        this.flightService.updateFlight(flight);
    }
}
