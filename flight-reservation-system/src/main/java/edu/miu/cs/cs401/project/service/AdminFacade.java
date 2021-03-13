package edu.miu.cs.cs401.project.service;

import edu.miu.cs.cs401.project.domain.*;

import java.util.Date;

public interface AdminFacade {

    void createPassenger(String firstName, String lastName,
                                        Date birthDate, String email,
                                        Address residenceAddress);

    void readPassenger(Passenger passenger);
    void updatePassengerFirstName(Passenger passenger, String firstName);
    void updatePassengerLastName(Passenger passenger, String lastName);
    void updatePassengerEmail(Passenger passenger, String email);
    void deletePassenger(Passenger passenger);

    void createAgent(Agent agent);
    void readAgent(Agent agent);
    void deleteAgent(Agent agent);

    void createAirport(Airport airport);
    void readAirport(Airport airport);
    void updateAirportName(Airport airport, String name);
    void updateAirportCode(Airport airport, String code);
    void updateAirportAddress(Airport airport, Address address);
    void deleteAirport(Airport airport);

    void createAirline(Airline airline);
    void readAirline(Airline airline);
    void updateAirlineName(Airline airline, String name);
    void updateAirlineCode(Airline airline, String code);
    void updateAirlinegetHistory(Airline airline, String history);
    void deleteAirline(Airline airline);
}
