package edu.miu.cs.cs401.project.service;

import java.util.List;
import java.util.UUID;

import edu.miu.cs.cs401.project.domain.Agent;
import edu.miu.cs.cs401.project.domain.Airline;
import edu.miu.cs.cs401.project.domain.Airport;
import edu.miu.cs.cs401.project.domain.Flight;
import edu.miu.cs.cs401.project.domain.Passenger;
import edu.miu.cs.cs401.project.domain.Reservation;

public interface ReservationSystemFacade {
	
	List<Airport> findAllAirports();
	
	Airport findAirportByAirportCode(String airportCode);
	
	List<Airport> findAirportsByCity(String city); // Airport(s) of a city, e.g. Chicago has two major airports
	
	List<Airline> findAirlinesByAirportCode(String airportCode);
	
	List<Flight> findFlightsFromTo(String departure, String arrival);
	
	List<Reservation> findReservationsByPassengerId(UUID passengerId);
	
	List<Passenger> findPassengersByAgentCode(String agentCode);

	Passenger findPassengersByName(String firstName, String lastName);

	Passenger findPassengersByName(List<Passenger> passenger, String firstName, String lastName);

	Reservation createReservation(Passenger passenger, List<Flight> flights); // Passenger reserves
	
	Reservation createReservation(Agent agent, Passenger passenger, List<Flight> flights); // Agent reserves
	
	void confirmReservation(String reservationCode);
	
	void cancelReservation(String reservationCode);

	public Agent findAgentByUuid(String agentId);

}
