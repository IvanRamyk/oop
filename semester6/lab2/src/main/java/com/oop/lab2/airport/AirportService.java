package com.oop.lab2.airport;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    private final AirportRepository studentRepository;

    public AirportService(AirportRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Airport> getAirports() {
        return studentRepository.findAll();
    }
}
