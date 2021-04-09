package com.oop.lab2.airport;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AirportService {
    private final AirportRepository repository;

    public AirportService(AirportRepository repository) {
        this.repository = repository;
    }

    public List<Airport> getAirports() {
        return repository.findAll();
    }

    public void addAirport(Airport airport) {
        repository.save(airport);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void updateAirport(Airport newVersion) {
        Airport oldVersion = repository.findById(newVersion.getId())
                .orElseThrow(() ->  new IllegalStateException("Student does not exist"));
        if (newVersion.getName() != null
                && !oldVersion.getName().equals(newVersion.getName())
                && newVersion.getName().length() > 0
        ) {
            oldVersion.setName(newVersion.getName());
        }

        if (newVersion.getCity() != null
                && !oldVersion.getCity().equals(newVersion.getCity())
                && newVersion.getCity().length() > 0
        ) {
            oldVersion.setCity(newVersion.getCity());
        }

        if (newVersion.getCountry() != null
                && !oldVersion.getCountry().equals(newVersion.getCountry())
                && newVersion.getCountry().length() > 0
        ) {
            oldVersion.setCountry(newVersion.getCountry());
        }

    }
}
