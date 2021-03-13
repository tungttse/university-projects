package edu.miu.cs.cs401.project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.miu.cs.cs401.project.domain.Agent;
import edu.miu.cs.cs401.project.domain.Airline;
import edu.miu.cs.cs401.project.domain.Airport;
import edu.miu.cs.cs401.project.domain.Flight;
import edu.miu.cs.cs401.project.domain.FlightNumber;
import edu.miu.cs.cs401.project.domain.Passenger;
import edu.miu.cs.cs401.project.domain.Reservation;
import edu.miu.cs.cs401.project.helpers.StorageHandler;
import edu.miu.cs.cs401.project.service.ReservationSystemFacade;
import edu.miu.cs.cs401.project.service.ReservationSystemFacadeImpl;
import edu.miu.cs.cs401.project.constants.ReservationStatus;

class ReservationSystemFacadeImplTest {

	ReservationSystemFacade facade = new ReservationSystemFacadeImpl(); 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void findAllAirportsTest() {
		StorageHandler.emptyAirports();
		List<Airport> airports = facade.findAllAirports();
		assertNotNull(airports);
		assertTrue(airports.isEmpty());
		
		StorageHandler.createRandomAirports(10);
		airports = facade.findAllAirports();
		assertNotNull(airports);
		assertTrue(airports.size() == 10);
	}
	
	@Test
	void findAirportByAirportCodeTest() {
		StorageHandler.emptyAirports();
		StorageHandler.createRandomAirports(10);
		
		Airport airport = facade.findAirportByAirportCode(StorageHandler.airports.get(0).getCode());
		assertNotNull(airport);
	}
	
	@Test
	void findAirportsByCityTest() {
		StorageHandler.emptyAirports();
		StorageHandler.createRandomAirports(10);
		
		List<Airport> airports = facade.findAirportsByCity(
				StorageHandler.airports
					.get(0)
					.getAddress()
					.getCity()
			);
		
		assertNotNull(airports);
		assertFalse(airports.isEmpty());
	}
	
	@Test
	void findAirlinesByAirportCodeTest() {
		StorageHandler.emptyAirports();
		StorageHandler.createRandomAirports(15);
		
		int i = 0;
		while (i++ < 20) {
			FlightNumber flightNumber = new FlightNumber(
					i,
					350,
					StorageHandler.getRandomAirline(),
					StorageHandler.airports.get(i % StorageHandler.airports.size()) ,
					StorageHandler.airports.get((i + 1) % StorageHandler.airports.size()),
					new Date(),
					new Date()
			);
			StorageHandler.flightNumbers.add(flightNumber);
		}

		List<Airline> airlines = facade.findAirlinesByAirportCode(StorageHandler.airports.get(0).getCode());
		assertNotNull(airlines);
		assertFalse(airlines.isEmpty());

	}

	@Test
	void findFlightsFromToTest() {
		StorageHandler.emptyFlights();
		StorageHandler.emptyAirports();
		StorageHandler.createRandomFlightNumber(10);

		final int SIZE = 5;

		Airport departureAirport = new Airport("214", "Departure Airport", StorageHandler.getRandomAddress());
		Airport arrivalAirport = new Airport("215", "Arrival Airport", StorageHandler.getRandomAddress());
		StorageHandler.createRandomFlights(SIZE, departureAirport, arrivalAirport);


		List<Flight> flights = facade.findFlightsFromTo(departureAirport.getName(), arrivalAirport.getName());
		assertNotNull(flights);
		assertTrue(flights.size() == SIZE);
	}

	// For agent reservation
	@Test
	public void testCreateReservation() {
		Agent agent = new Agent();
		agent.addPassenger(StorageHandler.getRandomPassenger(1));
		agent.addPassenger(StorageHandler.getRandomPassenger(2));
		List<Flight> flights = StorageHandler.generateListFlightInstance(5);
		Passenger passenger = StorageHandler.getRandomPassenger(2);

		Reservation re = facade.createReservation(agent, passenger, flights);
		assertNotNull(re);
		assertEquals(re.getAgentId(), agent.getUuid());
		assertEquals(StorageHandler.getReservationByCode(re.getReservationCode()), re );
		assertEquals(passenger.getReservations().get(0), re );
		assertEquals(re.getTickets().size(), 5 );
	}

	// For passenger reservation
	@Test
	public void testTestCreateReservation() {
		List<Flight> flights = StorageHandler.generateListFlightInstance(5);
		Passenger passenger = StorageHandler.getRandomPassenger(2);

		Reservation re = facade.createReservation(passenger, flights);
		assertNotNull(re);
		assertEquals(StorageHandler.getReservationByCode(re.getReservationCode()), re );
		assertEquals(passenger.getReservations().get(0), re );
		assertEquals(re.getTickets().size(), 5 );
	}

	@Test
	public void testConfirmReservation() {
		// generate a reservation
		Agent agent = new Agent();
		agent.addPassenger(StorageHandler.getRandomPassenger(1));
		agent.addPassenger(StorageHandler.getRandomPassenger(2));
		List<Flight> flights = StorageHandler.generateListFlightInstance(5);
		Passenger passenger = StorageHandler.getRandomPassenger(2);
		Reservation re = facade.createReservation(agent, passenger, flights);
		re.confirm();

		assertNotNull(re);
		assertEquals(re.getStatus(), ReservationStatus.CONFIRM_PURCHASE);
	}

	@Test
	public void testCancelReservation() {
		// generate a reservation
		Agent agent = new Agent();
		agent.addPassenger(StorageHandler.getRandomPassenger(1));
		agent.addPassenger(StorageHandler.getRandomPassenger(2));
		List<Flight> flights = StorageHandler.generateListFlightInstance(5);
		Passenger passenger = StorageHandler.getRandomPassenger(2);
		Reservation re = facade.createReservation(agent, passenger, flights);
		re.cancel();

		assertNotNull(re);
		assertEquals(re.getStatus(), ReservationStatus.CANCEL);
	}

	@Test
	public void findPassengersByName() {
		StorageHandler.getListPassenger(5);
		StorageHandler.passengers.add(
					new Passenger(
							"fname1",
							"lname1",
							new Date(),
							"fl@miu.edu",
							StorageHandler.getRandomAddress()
					)
			);
		Passenger p = facade.findPassengersByName("fname1","lname1");
		assertNotNull(p);
		assertEquals(p.getFirstName(), "fname1");
		assertEquals(p.getLastName(), "lname1");
	}

}
