package edu.miu.cs.cs401.project.domain;

import java.util.Date;
import java.util.UUID;
import java.util.List;

public class Flight {
    private final UUID uuid = UUID.randomUUID();;
    private final FlightNumber flightNumber;
    private final List<Passenger> passengers;
    private final List<String> crews;
    private final List<String> pilots;
    private Date flightDate;

    public Flight(FlightNumber flight, Date flightDate, List<Passenger> passengers, List<String> crews, List<String> pilots) {
        this.flightNumber = flight;
        this.flightDate = flightDate;
        this.passengers = passengers;
        this.crews = crews;
        this.pilots = pilots;
    }
    public UUID getUuid() {
        return uuid;
    }
    public Date getFlightDate() {
        return flightDate;
    }
    public FlightNumber getFlightNumber() {
        return flightNumber;
    }
    public List<Passenger> getPassengers() {
        return passengers;
    }
    public List<String> getCrews() {
        return crews;
    }
    public List<String> getPilots() {
        return pilots;
    }
}