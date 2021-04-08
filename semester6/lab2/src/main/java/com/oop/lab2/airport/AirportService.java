package com.oop.lab2.airport;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    public List<Airport> getAirports() {
        return List.of(
                new Airport(1, "Boryspil", "Kyiv", "Ukraine")
        );
    }
}
