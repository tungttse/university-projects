package edu.miu.cs.cs401.project.service;

import edu.miu.cs.cs401.project.domain.*;
import edu.miu.cs.cs401.project.helpers.StorageHandler;

import java.util.Date;

public class AdminFacadeImpl implements AdminFacade {

    // added by Bayartsogt
    @Override
    public void createPassenger(String firstName, String lastName,
                                Date birthDate, String email,
                                Address residenceAddress) {

        StorageHandler.passengers.add(new Passenger(firstName, lastName, birthDate, email, residenceAddress));
    }

    // Passenger CRUD
    @Override
    public void readPassenger(Passenger passenger) {
        System.out.println("=======================");
        System.out.println("FirstName: " + passenger.getFirstName());
        System.out.println("LastName: " + passenger.getLastName());
        System.out.println("BirthDate: " + passenger.getBirthDate());
        System.out.println("Email: " + passenger.getEmail());
        System.out.println("=======================");
    }

    @Override
    public void updatePassengerFirstName(Passenger passenger, String firstName) {
        passenger.setFirstName(firstName);
    }

    @Override
    public void updatePassengerLastName(Passenger passenger, String lastName) {
        passenger.setLastName(lastName);
    }

    @Override
    public void updatePassengerEmail(Passenger passenger, String email) {
        passenger.setEmail(email);
    }

    @Override
    public void deletePassenger(Passenger passenger) {
        StorageHandler.passengers.remove(passenger);
    }

    @Override
    public void createAgent(Agent agent) {
        StorageHandler.agents.add(agent);
    }


    // Agent CRUD
    @Override
    public void readAgent(Agent agent) {
        System.out.println("=======================");
//        System.out.println("FirstName: " + agent.getFirstName());
//        System.out.println("LastName: " + agent.getLastName());
//        System.out.println("BirthDate: " + agent.getBirthDate());
//        System.out.println("Email: " + agent.getEmail());
        System.out.println("=======================");
    }

    @Override
    public void deleteAgent(Agent agent) {
        StorageHandler.agents.remove(agent);
    }

    @Override
    public void createAirport(Airport airport) {
        StorageHandler.airports.add(airport);
    }

    @Override
    public void readAirport(Airport airport) {
        System.out.println(airport.toString());
    }

    // Airport CRUD
    @Override
    public void updateAirportName(Airport airport, String name) {
        airport.setName(name);
    }

    @Override
    public void updateAirportCode(Airport airport, String code) {
        airport.setCode(code);
    }

    @Override
    public void updateAirportAddress(Airport airport, Address address) {
        airport.setAddress(address);
    }

    @Override
    public void deleteAirport(Airport airport) {
        StorageHandler.airports.remove(airport);
    }

    // Airline CRUD
    @Override
    public void createAirline(Airline airline) {
        StorageHandler.airlines.add(airline);
    }

    @Override
    public void readAirline(Airline airline) {
        System.out.println("=== READ AIRPORT ===");
        System.out.println(airline.toString());
        System.out.println("====================");
    }

    @Override
    public void updateAirlineName(Airline airline, String name) {
        airline.setName(name);
    }

    @Override
    public void updateAirlineCode(Airline airline, String code) {
        airline.setCode(code);
    }

    @Override
    public void updateAirlinegetHistory(Airline airline, String history) {
        airline.setHistory(history);
    }

    @Override
    public void deleteAirline(Airline airline) {
        StorageHandler.airlines.remove(airline);
    }
}
