package edu.miu.cs.cs401.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.miu.cs.cs401.project.domain.*;
import edu.miu.cs.cs401.project.helpers.StorageHandler;

public class ReservationSystemFacadeImpl implements ReservationSystemFacade {

	@Override
	public List<Airport> findAllAirports() {
		return StorageHandler.airports;
	}

	@Override
	public Airport findAirportByAirportCode(String airportCode) {
		for (Airport airport: StorageHandler.airports)
			if (airport.getCode().equals(airportCode))
				return airport;
		return null;
	}

	@Override
	public List<Airport> findAirportsByCity(String city) {
		List<Airport> airports = new ArrayList<>();
		for (Airport airport: StorageHandler.airports)
			if (airport.getAddress().getCity().equals(city))
				airports.add(airport);
		return airports;
	}

	@Override
	public List<Airline> findAirlinesByAirportCode(String airportCode) {
		Airport departureAirport = findAirportByAirportCode(airportCode);

		if (departureAirport == null) return new ArrayList<>();

		// perform the query
		List<FlightNumber> depFlights = departureAirport.getDepartureFlights();
		List<Airline> airlines = new ArrayList<>();
		for (FlightNumber depFlight: depFlights) {
			Airline depFlightAirline = depFlight.getAirlineOwn();
			if (!airlines.contains(depFlightAirline)) {
				airlines.add(depFlightAirline);
			}

		}

		return airlines;
	}

	@Override
	public List<Flight> findFlightsFromTo(String departure, String arrival) {
		List<Flight> flightInstancesFound = new ArrayList<Flight>();

		for (Flight flight: StorageHandler.flights){
			// comparing dates, departure & arrival airport NAMES
			boolean case2 = flight.getFlightNumber().getDepartureAirport().getName().equals(departure);
			boolean case3 = flight.getFlightNumber().getArrivalAirport().getName().equals(arrival);

			if (case2 && case3){
				flightInstancesFound.add(flight);
			}
		}

		return flightInstancesFound;
	}

	@Override
	public List<Reservation> findReservationsByPassengerId(UUID passengerId) {
		for (Passenger passenger: StorageHandler.passengers)
			if (passenger.getUuid().equals(passengerId))
				return passenger.getReservations();

		return new ArrayList<>();
	}

	@Override
	public List<Passenger> findPassengersByAgentCode(String agentCode) {
		for (Agent a : StorageHandler.agents) {
			if (a.getUuid().equals(agentCode)) {
				return a.getPassengers();
			}
		}
		return null;
	}

	@Override
	public Passenger findPassengersByName(String firstName, String lastName) {
		for (Passenger p : StorageHandler.passengers) {
			if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Passenger findPassengersByName(List<Passenger> passengers, String firstName, String lastName) {
		for (Passenger p : passengers) {
			if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Reservation createReservation(Passenger passenger, List<Flight> flights) {
		Reservation reservation = new Reservation();
		for(Flight flight: flights){
			Ticket reservationTicket =
					new Ticket(StorageHandler.randomTicketNumber(),
							reservation.getReservationCode(),
							flight);
			reservation.addTicket(reservationTicket);
		}
		passenger.addReservation(reservation);

		StorageHandler.addReservation(reservation);
		StorageHandler.setTickets(reservation.getReservationCode(), reservation.getTickets());

		return reservation;
	}

	@Override
	public Reservation createReservation(Agent agent, Passenger passenger, List<Flight> flights) {
		Reservation reservation = new Reservation(agent.getUuid());
		for (Flight flight: flights) {
			Ticket t = new Ticket(
					StorageHandler.randomTicketNumber(),
					reservation.getReservationCode(),
					flight);
			reservation.addTicket(t);
		}
		passenger.addReservation(reservation);

		StorageHandler.addReservation(reservation);
		StorageHandler.setTickets(reservation.getReservationCode(), reservation.getTickets());

		return reservation;
	}

	@Override
	public void confirmReservation(String reservationCode) {
		Reservation reservation = StorageHandler.getReservationByCode(reservationCode);
		reservation.confirm();
		StorageHandler.updateReservation(reservation);
	}

	@Override
	public void cancelReservation(String reservationCode) {
		StorageHandler.removeTickets(reservationCode);
		StorageHandler.removeReservation(reservationCode);
	}

	@Override
	public Agent findAgentByUuid(String agentId) {
		if (agentId.length() != 5){
			return null;
		}

		for (Agent agent: StorageHandler.agents) {
			if (agent.getUuid().toString()
					.replace("-", "")
					.substring(0, 5)
					.toUpperCase()
					.equals(agentId.toUpperCase()))
				return agent;
		}
		return null;
	}
}
