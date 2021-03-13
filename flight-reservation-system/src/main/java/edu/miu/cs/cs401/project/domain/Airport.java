package edu.miu.cs.cs401.project.domain;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Airport {
    private final UUID uuid = UUID.randomUUID();
    private String code;
    private String name;
    private Address address;
    private final List<FlightNumber> departureFlights = new ArrayList<>();
    private final List<FlightNumber> arrivalFlights = new ArrayList<>();

    public Airport(String code, String name, Address address) {
        super();
        this.code = code;
        this.name = name;
        this.address = address;
    }
    public UUID getUuid() {
        return uuid;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public List<FlightNumber> getDepartureFlights() {
        return departureFlights;
    }
    public List<FlightNumber> getArrivalFlights () {
        return arrivalFlights;
    }
    public void addDepartureFlight(FlightNumber flightNumber) {
        departureFlights.add(flightNumber);
    }
    public void removeDepartureFlight(FlightNumber flightNumber) {
        departureFlights.remove(flightNumber);
    }
    public void addArrivalFlights(FlightNumber flightNumber) {
        arrivalFlights.add(flightNumber);
    }
    public void removeArrivalFlights(FlightNumber flightNumber) {
        arrivalFlights.remove(flightNumber);
    }
    @Override
    public String toString() {
        return "Airport [ " + uuid + ", " + code + ", " + name + ", address = " + address + " ]";
    }
}
