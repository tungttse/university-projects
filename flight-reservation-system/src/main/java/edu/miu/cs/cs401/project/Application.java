package edu.miu.cs.cs401.project;

import edu.miu.cs.cs401.project.domain.*;
import edu.miu.cs.cs401.project.helpers.StorageHandler;
import edu.miu.cs.cs401.project.service.ReservationSystemFacade;
import edu.miu.cs.cs401.project.service.ReservationSystemFacadeImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Application {

	public static void passengerFlow() throws ParseException {
		Scanner scanner = new Scanner(System.in);
		Passenger passenger;

		// and create the object for reservation facade implementation
		ReservationSystemFacade facade = new ReservationSystemFacadeImpl();

		// Check if Passenger new?
//		if(facade.findPassengersByName(fname, lname) != null){
//			System.out.println("Welcome back " + fname + " " + lname +" to Airline Reservation System");
//			passenger = StorageHandler.passengers.get(0);
//			newPassenger = false;
//		} else {
//			System.out.println("Welcome " + fname + " " + lname +" to Airline Reservation System");
//			System.out.println("We need more information for register new passenger");
//			System.out.println("Email: ");
//			String email = scanner.next();
//			System.out.println("Date of birth (DD/MM/YYYY): ");
//			Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
//			passenger = new Passenger(fname, lname, dob, email, StorageHandler.getRandomAddress());
//			System.out.println("Your register is Done. ");
//			newPassenger = true;
//		}

		passenger = StorageHandler.passengers.get(0);

		// List out use case for user

		while (true) {
			System.out.println("========Welcome Passenger "+ passenger.getFirstName() +  " " + passenger.getLastName() +"=========");
			System.out.println();


			System.out.println("This is some action you can do: ");
			System.out.println(
					"1. List all available Airports" +
							"\n2. Search Airlines by Airport CODE" +
							"\n3. Search Airport by CITY NAME" +
							"\n4. Search flight by Departure and Arrival" +
							"\n5. My Reservation" +
							"\n6. Create Reservation" +
							"\n7. Quit");
			System.out.println("Please select one of these action: ");
			String action = scanner.next();
			if (action.equals("7")) {
				break;
			}


			PassengerAction(action, passenger, false, facade);
		}
	}

	public static void PassengerAction(String actionCase, Passenger p, boolean newPassenger, ReservationSystemFacade facade){
		Scanner scanner = new Scanner(System.in);
		String departure;
		String arrival;
		List<Flight> flightsFromTo;
		List<Airport> airports;
		switch (actionCase) {
			case "1": // View all airport
				// here is the list of them
				airports = facade.findAllAirports();
				System.out.println("Here is the list of all airports:");
				for (Airport airport : airports) {
					System.out.println("=================");
					System.out.println("Name: " + airport.getName() +
							"\nCode: " + airport.getCode() +
							"\nAddress: " + airport.getAddress().getStreet() +
							", " + airport.getAddress().getCity() +
							", " + airport.getAddress().getState() +
							", " + airport.getAddress().getZip());
				}
				System.out.println();
				break;

			case "2": // Search Airport by CODE
				// here is the list of all airlines
				System.out.println("Here is the list of all airports:");
				airports = facade.findAllAirports();
				for (Airport airport : airports) {
					System.out.println("=================");
					System.out.println("Name: " + airport.getName() +
							"\nCode: " + airport.getCode() +
							"\nAddress: " + airport.getAddress().getStreet() +
							", " + airport.getAddress().getCity() +
							", " + airport.getAddress().getState() +
							", " + airport.getAddress().getZip());
				}
				System.out.println("Please select Airport Code to get Airlines list: ");
				// print out airlines in this airport
				List<Airline> airlinesByAirportCode = facade.findAirlinesByAirportCode(scanner.nextLine());

				if (airlinesByAirportCode.size() == 0) {
					System.out.println("OOP, No Airlines");
					break;
				}

				System.out.println("We have Airlines list below: ");
				for (Airline airline : airlinesByAirportCode) {
					System.out.println("=================");
					System.out.println("Name: " + airline.getName() +
							"\nCode: " + airline.getCode() +
							"\nHistory: " + airline.getHistory());
				}
				System.out.println();
				break;

			case "3": // Search Airport by CITY NAME
				// here is the list of all airports in given city
				System.out.print("Please enter the city: ");
				List<Airport> airportsInCity = facade.findAirportsByCity(scanner.nextLine());
				if (airportsInCity.size() == 0)
					System.out.println("No airports in that city :(");
				for (Airport airport : airportsInCity) {
					System.out.println("=================");
					System.out.println("Name: " + airport.getName() +
							"\nCode: " + airport.getCode() +
							"\nAddress: " + airport.getAddress().getStreet() +
							", " + airport.getAddress().getCity() +
							", " + airport.getAddress().getState() +
							", " + airport.getAddress().getZip());
				}
				System.out.println();
				break;

			case "4": // Search fly by CITY name
				System.out.print("Please enter name of departure airport: ");
				departure = scanner.nextLine();
				System.out.print("Please enter name of arrival airport: ");
				arrival = scanner.nextLine();

				flightsFromTo = facade.findFlightsFromTo(departure, arrival);
				if (flightsFromTo.size() == 0)
					System.out.println("No flight for this route :(");
				for (Flight flight : flightsFromTo) {
					System.out.println("=================");
					System.out.println("Flight name: " + flight.getFlightNumber() +
							"Date: " + flight.getFlightDate() +
							"Passenger: " + flight.getPassengers() +
							"Drive by: " + flight.getPilots());
				}
				System.out.println();
				break;

			case "5":
				List<Reservation> myReservations = p.getReservations();
				if (myReservations.size() == 0) {
					System.out.println("No any reservation, please create a new one ");
					break;
				}

				System.out.println("These are your reservation: ");
				for (Reservation reservation : myReservations){
					if (reservation.getReservationCode() == ""
							|| reservation.getReservationCode() == null) {
						continue;
					}
					System.out.println("=================");
					System.out.println("Reservation Code: " + reservation.getReservationCode() +
							"Status: " + reservation.getStatus() +
							"Number of ticket: " + reservation.getTickets().size());
					List<Ticket> ticketsList = reservation.getTickets();
					for (Ticket ticket : ticketsList){
						System.out.println("=================");
						System.out.println("\nTicket number: " + ticket.getNumber() +
								"\nDate: " + ticket.getFlight().getFlightDate() +
								"\nDeparture: " + ticket.getFlight().getFlightNumber().getDepartureAirport().getName() +
								" " + ticket.getFlight().getFlightNumber().getDepartureTime().toString() +
								"\nArrival: " + ticket.getFlight().getFlightNumber().getArrivalAirport().getName() +
								" " + ticket.getFlight().getFlightNumber().getArrivalTime().toString());
					}
				}
				break;

			case "6":
//				System.out.print("Please enter name of departure airport: ");
//				departure = scanner.nextLine();
//				System.out.print("Please enter name of arrival airport: ");
//				arrival = scanner.nextLine();
//
//				flightsFromTo = facade.findFlightsFromTo(departure, arrival);
//				if (flightsFromTo.size() == 0) {
//					System.out.println("No flight for this route :(");
//					break;
//				}
//
//				for (Flight flight : flightsFromTo) {
//					System.out.println("=================");
//					System.out.println("Flight name: " + flight.getFlightNumber() +
//							"Date: " + flight.getFlightDate() +
//							"Passenger: " + flight.getPassengers() +
//							"Drive by: " + flight.getPilots());
//				}

				List<Flight> flights = StorageHandler.generateListFlightInstance(2);
				Reservation newReservation = facade.createReservation(p, flights);
				System.out.println("=================");
				System.out.println("Reservation Code: " + newReservation.getReservationCode() +
						" Status: " + newReservation.getStatus() +
						" Number of ticket: " + newReservation.getTickets().size());
				System.out.println("Your reservation is added");
				System.out.println("Do you want to confirm and purchase it? (Y/N)");
				String confirmed = scanner.nextLine();

				if(confirmed.equals("Y".toLowerCase())){
					newReservation.confirm();
					System.out.println("You confirmed and purchased successful the reservation " + newReservation.getReservationCode());
				} else {
					newReservation.cancel();
					System.out.println("OOp, you cancelled the reservation " + newReservation.getReservationCode());
				}

			default:
				break;
		}
		
		
	}
		
	public static void agentFlow(String uuid) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		boolean isAgent = false;
		Agent agent = null;
		// and create the object for reservation facade implementation
		ReservationSystemFacade facade = new ReservationSystemFacadeImpl();

		// Check if Agent new?
		if(facade.findAgentByUuid(uuid) != null){
			System.out.println("Welcome back to Airline Reservation System, Agent " + uuid + " !!!");
			agent = facade.findAgentByUuid(uuid);
			isAgent = true;
		} else {
			System.out.println("Cannot find this UUID in system please contact your admin !!");
		}

		// List out use case for user
		if(isAgent) {
			while (true) {
				System.out.println("This is some action you can do: ");
				System.out.println(
						"1. List all available Airports" +
								"\n2. Search Airlines by Airport CODE" +
								"\n3. Search Airport by CITY NAME" +
								"\n4. Search flight by Departure and Arrival" +
								"\n5. My Reservation for passenger" +
								"\n6. Create Reservation" +
								"\n7. Quit");
				System.out.println("Please select one of these action: ");

				String action = scanner.next();
				if(action.equals("7")) {
					break;
				}
				agentAction(action, agent, facade);
			}
		}
	}

	public static void agentAction(String actionCase, Agent a, ReservationSystemFacade facade) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		String departure;
		String arrival;
		List<Flight> flightsFromTo;
		List<Airport> airports;

		switch (actionCase) {
			case "1": // View all airport
				// here is the list of them
				airports = facade.findAllAirports();
				System.out.println("Here is the list of all airports:");
				
				for (Airport airport : airports) {
					System.out.println("=================");
					System.out.println("Name: " + airport.getName() +
							"\nCode: " + airport.getCode() +
							"\nAddress: " + airport.getAddress().getStreet() +
							", " + airport.getAddress().getCity() +
							", " + airport.getAddress().getState() +
							", " + airport.getAddress().getZip());
				}
				System.out.println();
				
				break;

			case "2": // Search Airport by CODE
				// here is the list of all airlines
				System.out.println("Here is the list of all airports:");
				airports = facade.findAllAirports();
				for (Airport airport : airports) {
					System.out.println("=================");
					System.out.println("Name: " + airport.getName() +
							"\nCode: " + airport.getCode() +
							"\nAddress: " + airport.getAddress().getStreet() +
							", " + airport.getAddress().getCity() +
							", " + airport.getAddress().getState() +
							", " + airport.getAddress().getZip());
				}
				System.out.println("Please select Airport Code to get Airlines list: ");
				// print out airlines in this airport
				String inputCode = scanner.nextLine();
				List<Airline> airlinesByAirportCode = facade.findAirlinesByAirportCode(inputCode);

				if (airlinesByAirportCode.size() == 0) {
					System.out.println("OOP, No Airlines");
					break;
				}
				System.out.println("We have Airlines list below: ");
				for (Airline airline : airlinesByAirportCode) {
					System.out.println("=================");
					System.out.println("Name: " + airline.getName() +
							"\nCode: " + airline.getCode() +
							"\nHistory: " + airline.getHistory());
				}
				System.out.println();
				break;

			case "3": // Search Airport by CITY NAME
				// here is the list of all airports in given city
				System.out.print("Please enter the city: ");
				List<Airport> airportsInCity = facade.findAirportsByCity(scanner.nextLine());
				if (airportsInCity.size() == 0)
					System.out.println("No airports in that city :(");
				for (Airport airport : airportsInCity) {
					System.out.println("=================");
					System.out.println("Name: " + airport.getName() +
							"\nCode: " + airport.getCode() +
							"\nAddress: " + airport.getAddress().getStreet() +
							", " + airport.getAddress().getCity() +
							", " + airport.getAddress().getState() +
							", " + airport.getAddress().getZip());
				}
				System.out.println();
				break;

			case "4": // Search fly by CITY name
				System.out.print("Please enter name of departure airport: ");
				departure = scanner.nextLine();
				System.out.print("Please enter name of arrival airport: ");
				arrival = scanner.nextLine();

				flightsFromTo = facade.findFlightsFromTo(departure, arrival);
				if (flightsFromTo.size() == 0)
					System.out.println("No flight for this route :(");
				for (Flight flight : flightsFromTo) {
					System.out.println("=================");
					System.out.println("Flight name: " + flight.getFlightNumber() +
							"Date: " + flight.getFlightDate() +
							"Passenger: " + flight.getPassengers() +
							"Drive by: " + flight.getPilots());
				}
				System.out.println();
				break;

			case "5":
				System.out.println("These are your reservation: ");
				List<Passenger> myPassenger = a.getPassengers();
				for (Passenger passenger : myPassenger) {
					System.out.println("=================");
					System.out.println("Passenger Name: " + passenger.getFirstName() + " " + passenger.getLastName());
					List<Reservation> pReservation = passenger.getReservations();
					for (Reservation reservation : pReservation) {
						if (reservation.getReservationCode() == ""
								|| reservation.getReservationCode() == null) {
							continue;
						}

						if (reservation.getAgentId().toString().equals(a.getUuid().toString())) {
							System.out.println("=================");
							System.out.println("Agent Book Reservation Code: " + reservation.getReservationCode() +
									"Status: " + reservation.getStatus() +
									"Number of ticket: " + reservation.getTickets().size());
							List<Ticket> ticketsList = reservation.getTickets();
							for (Ticket ticket : ticketsList){
								System.out.println("=================");
								System.out.println("\nTicket number: " + ticket.getNumber() +
										"\nDate: " + ticket.getFlight().getFlightDate() +
										"\nDeparture: " + ticket.getFlight().getFlightNumber().getDepartureAirport().getName() +
										" " + ticket.getFlight().getFlightNumber().getDepartureTime().toString() +
										"\nArrival: " + ticket.getFlight().getFlightNumber().getArrivalAirport().getName() +
										" " + ticket.getFlight().getFlightNumber().getArrivalTime().toString());
							}
						}
					}
				}
				break;

			case "6":
//				Passenger passenger = null;
//				System.out.println("Who you gonna make reservation for:");
//				System.out.println("First Name");
//				String fname = scanner.nextLine();
//				System.out.println("Last Name");
//				String lname = scanner.nextLine();

//				if(facade.findPassengersByName(a.getPassengers(), fname, lname) != null){
//					passenger = facade.findPassengersByName(a.getPassengers(), fname, lname);
//				} else {
//					System.out.println("This passenger is not in your list!!");
//					System.out.println("We need more information for register new passenger");
//					System.out.println("Email: ");
//					String email = scanner.next();
//					System.out.println("Date of birth (DD/MM/YYYY): ");
//					Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
//					passenger = new Passenger(fname, lname, dob, email, StorageHandler.getRandomAddress());
//					System.out.println("Your register is Done. ");
//				}

				Passenger passenger = StorageHandler.passengers.get(1);

//				System.out.print("Please enter name of departure airport: ");
//				departure = scanner.nextLine();
//				System.out.print("Please enter name of arrival airport: ");
//				arrival = scanner.nextLine();
//
//				flightsFromTo = facade.findFlightsFromTo(departure, arrival);
//				if (flightsFromTo.size() == 0) {
//					System.out.println("No flight for this route :(");
//					break;
//				}
//
//				for (Flight flight : flightsFromTo) {
//					System.out.println("=================");
//					System.out.println("Flight name: " + flight.getFlightNumber() +
//							"Date: " + flight.getFlightDate() +
//							"Passenger: " + flight.getPassengers() +
//							"Drive by: " + flight.getPilots());
//				}

				List<Flight> flights = StorageHandler.generateListFlightInstance(2);
				Reservation newReservation = facade.createReservation(a, passenger, flights);

				//TODO
				System.out.println("=================");
				System.out.println("Agent Book Reservation Code: " + newReservation.getReservationCode() +
						" Status: " + newReservation.getStatus() +
						" Number of ticket: " + newReservation.getTickets().size());
				System.out.println("Your reservation is added");
				System.out.println("Do you want to confirm and purchase it? (Y/N)");
				String confirmed = scanner.nextLine();
				if(confirmed.equals("Y".toLowerCase())){
					newReservation.confirm();
					System.out.println("You confirmed and purchased successful the reservation " + newReservation.getReservationCode());
				} else {
					newReservation.cancel();
					System.out.println("OOp, you cancelled the reservation " + newReservation.getReservationCode());
				}
				break;

			default:
				break;
		}
		
		
	}


	public static void main(String[] args) throws ParseException {
		StorageHandler.initializeData();
		String fname, lname;

		//Test Agent flow data
		Agent agent = new Agent();
		ReservationSystemFacade facade = new ReservationSystemFacadeImpl();
		Passenger passenger = StorageHandler.getRandomPassenger(5);
		passenger.addReservation(facade.createReservation(agent, passenger, StorageHandler.generateListFlightInstance(5)));
		
		StorageHandler.addAgent(agent);
		agent.addPassenger(passenger);
		System.out.println("Use this Agent UUID: " + agent.getUuid().toString());

		boolean isRunApp = true;
		while (isRunApp) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Who are you???");
			System.out.println("1. Admin\n2. Passenger\n3. Agent\n4. Quit");
			String role = scanner.nextLine();

			switch (role) {
				case "1":
					//TODO admin workflow
					break;
				case "2":
//					System.out.println("Can i know your name ?");
//					System.out.println("First Name: ");
//					fname = scanner.nextLine();
//					System.out.println("Last Name: ");
//					lname = scanner.nextLine();
					passengerFlow();
					break;
				case "3":
					//TODO Agent workflow
					System.out.println("Please provide your first 5 digit of UUID");
					String uuid = scanner.nextLine();
					agentFlow(uuid);
					break;
				case "4":
					isRunApp = false;
					System.out.println("Bye Bye");
					break;
			}
		}

		System.exit(0);
	}
}
