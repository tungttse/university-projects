package edu.miu.cs.cs401.project;

import edu.miu.cs.cs401.project.domain.Passenger;
import edu.miu.cs.cs401.project.helpers.StorageHandler;
import edu.miu.cs.cs401.project.service.AdminFacadeImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

import edu.miu.cs.cs401.project.domain.*;
import edu.miu.cs.cs401.project.helpers.StringHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.miu.cs.cs401.project.helpers.StorageHandler;
import edu.miu.cs.cs401.project.service.AdminFacadeImpl;
import edu.miu.cs.cs401.project.service.ReservationSystemFacade;
import edu.miu.cs.cs401.project.service.ReservationSystemFacadeImpl;
import edu.miu.cs.cs401.project.constants.ReservationStatus;
import java.time.LocalDate;
import java.util.UUID;

class AdminFacadeImplTest {
	AdminFacadeImpl adminFacade = new AdminFacadeImpl(); 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreatePassenger() throws ParseException {
		Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1989");
		adminFacade.createPassenger("firstName", "lastName", birthday, "abc@gmail.com", StorageHandler.getRandomAddress());

		assertEquals(StorageHandler.passengers.size(), 1);
		assertNotNull(StorageHandler.passengers.get(0));
	}

	@Test
	public void testUpdatePassengerFirstName() throws ParseException {
		Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1989");
		Passenger p = new Passenger("firstName", "lastName", birthday, "abc@gmail.com", StorageHandler.getRandomAddress());
		adminFacade.updatePassengerFirstName(p, "firstname_update");
		assertEquals(p.getFirstName(), "firstname_update");
	}

	@Test
	public void testUpdatePassengerLastName() throws ParseException {
		Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1989");
		Passenger p = new Passenger("firstName", "lastName", birthday, "abc@gmail.com", StorageHandler.getRandomAddress());
		adminFacade.updatePassengerLastName(p, "lastName_update");
		assertEquals(p.getLastName(), "lastName_update");
	}

	@Test
	public void testUpdatePassengerEmail() throws ParseException {
		Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1989");
		Passenger p = new Passenger("firstName", "lastName", birthday, "abc@gmail.com", StorageHandler.getRandomAddress());
		adminFacade.updatePassengerEmail(p, "abc_updated@gmail.com");
		assertEquals(p.getEmail(), "abc_updated@gmail.com");
	}

	@Test
	public void testDeletePassenger() throws ParseException {
		Date birthday= new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1989");
		adminFacade.createPassenger("firstName", "lastName", birthday, "abc@gmail.com", StorageHandler.getRandomAddress());
		adminFacade.deletePassenger(StorageHandler.passengers.get(0));
		assertEquals(StorageHandler.passengers.size(), 0);
	}

	@Test
	public void testDeleteAgent() {
		Agent agent = new Agent();
		StorageHandler.agents.add(agent);
		int numberOfAgents = StorageHandler.agents.size();

		adminFacade.deleteAgent(agent);
		assertTrue(StorageHandler.agents.size() == numberOfAgents - 1);
	}

	@Test
	public void testReadAirport() {
		adminFacade.readAirport(StorageHandler.getRandomAirport());
	}

	@Test
	public void testUpdateAirportName() {
		StorageHandler.emptyAirports();
		StorageHandler.createRandomAirports(5);
		
		Airport airport = StorageHandler.airports.get(0);
		String name = "NewAirportName";
		String oldName = airport.getName();

		adminFacade.updateAirportName(airport, name);
		assertEquals(airport.getName(), name);

		adminFacade.updateAirportName(airport, oldName);
		assertEquals(StorageHandler.airports.get(0).getName(), oldName);
	}

	@Test
	public void testUpdateAirportCode() {
		StorageHandler.emptyAirports();
		StorageHandler.createRandomAirports(5);
		
		Airport airport = StorageHandler.airports.get(0);
		String code = "NewAirportCode";
		String oldCode = airport.getCode();

		adminFacade.updateAirportCode(airport, code);
		assertEquals(airport.getCode(), code);

		adminFacade.updateAirportCode(airport, oldCode);
		assertEquals(StorageHandler.airports.get(0).getCode(), oldCode);
	}

	@Test
	public void testUpdateAirportAddress() {
		Airport airport = StorageHandler.airports.get(0);
		Address oldAddress = airport.getAddress();
		Address newAddress = new Address("newStreet","newCity","newState", 12345);

		adminFacade.updateAirportAddress(airport, newAddress);
		assertEquals(StorageHandler.airports.get(0).getAddress().getZip(), newAddress.getZip());

		adminFacade.updateAirportAddress(airport, oldAddress);
		assertEquals(StorageHandler.airports.get(0).getAddress().getZip(), oldAddress.getZip());
	}

	@Test
	public void testDeleteAirport() {
		Airport airport = StorageHandler.airports.get(0);
		String uuid = airport.getUuid().toString();

		adminFacade.deleteAirport(airport);
		assertNotEquals(StorageHandler.airports.get(0).getUuid().toString(), uuid);
	}

	@Test
	public void testReadAirline() {
		Airline airline = new Airline("code", "newName", "history");
		adminFacade.readAirline(airline);
	}

	@Test
	public void testUpdateAirlineName() {
		Airline airline = new Airline("code", "newName", "history");
		adminFacade.updateAirlineName(airline, "otherName");
		assertEquals(airline.getName(), "otherName");
	}

	@Test
	public void testUpdateAirlineCode() {
		Airline airline = new Airline("code", "newName", "history");
		adminFacade.updateAirlineCode(airline, "otherCode");
		assertEquals(airline.getCode(), "otherCode");
	}

	@Test
	public void testUpdateAirlinegetHistory() {
		Airline airline = new Airline("code", "newName", "history");
		adminFacade.updateAirlinegetHistory(airline, "otherHistory");
		assertEquals(airline.getHistory(), "otherHistory");
	}

	@Test
	public void testDeleteAirline() {
		Airline airline = new Airline("newCode", "newName", "-");
		UUID uuid = airline.getUuid();

		adminFacade.createAirline(airline);

		int lastIndex = StorageHandler.airlines.size() - 1;
		assertEquals(StorageHandler.airlines.get(lastIndex).getUuid().toString(), uuid.toString());
	}
}
