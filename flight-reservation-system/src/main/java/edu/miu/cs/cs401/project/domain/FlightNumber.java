package edu.miu.cs.cs401.project.domain;

import java.util.Date;
import java.util.UUID;

public class FlightNumber {
    private final UUID uuid;
    private final int number;
    private int capacity;
    private final Airline airlineOwn;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Date departureTime;
    private Date arrivalTime;
    public FlightNumber(int number, int capacity, Airline airlineOwn, Airport departureAirport,
                  Airport arrivalAirport, Date departureTime, Date arrivalTime) {
        super();
        this.uuid = UUID.randomUUID();
        this.number = number;
        this.capacity = capacity;
        this.airlineOwn = airlineOwn;

        this.departureAirport = departureAirport;
        departureAirport.addDepartureFlight(this);
        this.arrivalAirport = arrivalAirport;
        arrivalAirport.addArrivalFlights(this);

        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    public UUID getUuid() {
        return uuid;
    }
    public int getNumber() {
        return number;
    }
    public int getCapacity() {
        return capacity;
    }
    public Airline getAirlineOwn() {
        return airlineOwn;
    }
    public Airport getDepartureAirport() {
        return departureAirport;
    }
    public Airport getArrivalAirport() {
        return arrivalAirport;
    }
    public Date getDepartureTime() {
        return departureTime;
    }
    public Date getArrivalTime() {
        return arrivalTime;
    }
    @Override
    public String toString() {
        return "FlightNumber [number=" + number + ", airlineOwn=" + airlineOwn.getCode() + ", departureAirport=" + departureAirport.getCode()
                + ", arrivalAirport=" + arrivalAirport.getCode() + ", departureTime=" + departureTime + ", arrivalTime="
                + arrivalTime + "]";
    }

}