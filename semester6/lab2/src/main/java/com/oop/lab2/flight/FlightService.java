package com.oop.lab2.flight;


import com.oop.lab2.airport.Airport;
import com.oop.lab2.airport.AirportRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository repository;
    private final AirportRepository airportRepository;

    public FlightService(FlightRepository repository, AirportRepository airportRepository) {
        this.repository = repository;
        this.airportRepository = airportRepository;
    }

    public List<Flight> getFlights() {
        return repository.findAll();
    }

    public void addFlight(FlightInfo flightInfo) {
        Airport from = airportRepository.findById(flightInfo.getFrom())
                .orElseThrow(() -> new IllegalStateException("Airport does not exist"));

        Airport to = airportRepository.findById(flightInfo.getTo())
                .orElseThrow(() -> new IllegalStateException("Airport does not exist"));
        Flight flight = new Flight(flightInfo.getId(), from, to, new Timestamp(flightInfo.getDatetime()).toLocalDateTime());
        System.out.println(from + " " + to);
        repository.save(flight);
    }

    public void deleteFlight(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void updateFlight(FlightInfo flightInfo) {
        Airport from = airportRepository.findById(flightInfo.getFrom())
                .orElseThrow(() -> new IllegalStateException("Airport does not exist"));

        Airport to = airportRepository.findById(flightInfo.getTo())
                .orElseThrow(() -> new IllegalStateException("Airport does not exist"));

        Flight oldVersion = repository.findById(flightInfo.getId()).orElseThrow(() -> new IllegalStateException("Flight does not exist"));

        Flight newVersion = new Flight(flightInfo.getId(), from, to, new Timestamp(flightInfo.getDatetime()).toLocalDateTime());

        if (newVersion.getFrom() != null
                && !oldVersion.getFrom().equals(newVersion.getFrom())
        ) {
            oldVersion.setFrom(newVersion.getFrom());
        }

        if (newVersion.getTo() != null
                && !oldVersion.getTo().equals(newVersion.getTo())
        ) {
            oldVersion.setTo(newVersion.getTo());
        }

        if (newVersion.getDeparture() != null
                && !oldVersion.getDeparture().equals(newVersion.getDeparture())
        ) {
            oldVersion.setDeparture(newVersion.getDeparture());
        }

    }
}