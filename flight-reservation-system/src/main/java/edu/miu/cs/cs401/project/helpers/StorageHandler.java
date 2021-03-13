package edu.miu.cs.cs401.project.helpers;

import edu.miu.cs.cs401.project.domain.*;
import edu.miu.cs.cs401.project.service.ReservationSystemFacade;
import edu.miu.cs.cs401.project.service.ReservationSystemFacadeImpl;

import java.security.SecureRandom;
import java.util.*;

public class StorageHandler {
    public static List<Airport> airports = new ArrayList<>();
    public static List<Airline> airlines = new ArrayList<>();
    public static List<Address> addresses = new ArrayList<>();
    public static List<FlightNumber> flightNumbers = new ArrayList<>();
    public static List<Flight> flights = new ArrayList<>();
    public static List<Agent> agents = new ArrayList<>();
    public static List<Passenger> passengers = new ArrayList<>();
    public static List<Reservation> reservations = new ArrayList<>();

    public static HashMap<String, Reservation> reservationsMap = new HashMap<>();

    public static HashMap<String, List<Ticket>> ticketsByReservationCode = new HashMap<>();

    public static List<Ticket> getTicketByReservationCode(String reservationCode) {
        return ticketsByReservationCode.get(reservationCode);
    }

    public static List<Ticket> setTickets(String reservationCode, List<Ticket> tickets) {
        return ticketsByReservationCode.put(reservationCode, tickets);
    }

    public static List<Ticket> removeTickets(String reservationCode) {
        return ticketsByReservationCode.remove(reservationCode);
    }

    // Reservations
    public static Reservation getReservationByCode(String reservationCode) {
        return reservationsMap.get(reservationCode);
    }

    public static List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        for (Map.Entry<String, Reservation> entry : reservationsMap.entrySet()) {
            reservations.add(entry.getValue());
        }
        return reservations;
    }

    public static void addReservation(Reservation reservation) {
        reservationsMap.put(reservation.getReservationCode(), reservation);
    }

    public static void updateReservation(Reservation reservation) {
        reservationsMap.put(reservation.getReservationCode(), reservation);
    }

    public static void removeReservation(String reservationCode) {
        reservationsMap.remove(reservationCode);
    }
    // End reservations

    public static Address getRandomAddress() {
        return new Address("Main road", StringHelper.getRandomCityName(), StringHelper.getRandomStateName(), (int) (Math.random() * 1000) + 3000);
    }

    public static void createRandomAddresses(int amount) {
        for (int i = 0; i < amount; i++) {
            addresses.add(getRandomAddress());
        }
    }

    public static int randomNumber() {
        return (int) (Math.random() * 1000);
    }

    public static String randomReservationCode() {
        int len = 6;
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static String randomTicketNumber() {
        int len = 20;
        String AB = "0123456789";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static List<Passenger> getListPassenger(int amount) {
        for (int i = 0; i < amount; i++) {
            passengers.add(getRandomPassenger(i));
        }
        return passengers;
    }

    public static Passenger getRandomPassenger(int unique) {
        return new Passenger(
                "fn" + unique,
                "ln" + unique,
                new Date(),
                "e"+unique +"@gmail.com",
                getRandomAddress()
        );
    }

    public static Agent getRandomAgent(int amountPassenger) {
        return new Agent(getListPassenger(amountPassenger));
    }

    public static void addAgent(Agent agent) {
        agents.add(agent);
    }

    public static void emptyAddresses() {
        addresses.removeAll(addresses);
    }

    public static Airport getRandomAirport() {
        final String airportCode = ((int) (Math.random() * 900) + 100) + "";
        return new Airport(airportCode, StringHelper.getRandomCityName() + " Airport", getRandomAddress());
    }

    public static Airline getRandomAirline() {
        return new Airline((int) (Math.random() * 900) + "", "Turkish Airlines", "Turkish Airlines is established in ...");
    }

    public static void createRandomAirports(int amount) {
        for (int i = 0; i < amount; i++) {
            airports.add(getRandomAirport());
        }
    }

    public static void emptyAirports() {
        airports.removeAll(airports);
    }

    public static void emptyFlights() {
        flights.removeAll(flights);
    }

    public static FlightNumber createRandomFlightNumber(int amount) {
        int number = (int) Math.random();
        int capacity = amount;
        Airline airlineOwn = new Airline("AIA", "American Airline", "history");
        Airport departureAirport = getRandomAirport();
        Airport arrivalAirport = getRandomAirport();
        Date departureTime = new Date();
        Date arrivalTime = new Date();

        FlightNumber fn = new FlightNumber(number, capacity, airlineOwn, departureAirport, arrivalAirport, departureTime, arrivalTime);
        flightNumbers.add(fn);

        return fn;
    }

    public static FlightNumber createFixedFlightNumber(int amount) {
        int number = (int) Math.random();
        int capacity = amount;
        Airline airlineOwn = new Airline("AIA", "American Airline", "history");

        final String airportCode = ((int) (Math.random() * 900) + 100) + "";

        Airport departureAirport = new Airport(airportCode, "Chicago Airport", getRandomAddress());
        Airport arrivalAirport = new Airport(airportCode, "New York City Airport", getRandomAddress());
        Date departureTime = new Date();
        Date arrivalTime = new Date();

        FlightNumber fn = new FlightNumber(number, capacity, airlineOwn, departureAirport, arrivalAirport, departureTime, arrivalTime);
        flightNumbers.add(fn);

        return fn;
    }




    public static void createRandomFlightNumbers(int amount, Airport departureAirport, Airport arrivalAirport) {
        for (int i = 0; i < amount; i++)
            flightNumbers.add(getRandomFlightNumber(departureAirport, arrivalAirport));
    }

    private static FlightNumber getRandomFlightNumber(Airport departureAirport, Airport arrivalAirport) {
        return new FlightNumber(
                (int) Math.random(),
                300,
                new Airline("AIA", "American Airline", "history"),
                departureAirport,
                arrivalAirport,
                new Date(),
                new Date()
        );
    }

    public static void createRandomFlights(int amount, Airport departureAirport, Airport arrivalAirport) {
        for (int i = 0; i < amount; i++)
            flights.add(getRandomFlight(departureAirport, arrivalAirport));
    }

    public static Flight getRandomFlight(Airport departureAirport, Airport arrivalAirport) {
        Date dateFlight = new Date();
        List<Passenger> passengers = getListPassenger(10);
        List<String> pilots = createRandomPilots(5);
        List<String> crews = createRandomCrew(5);

        return new Flight(
                getRandomFlightNumber(departureAirport, arrivalAirport),
                dateFlight,
                passengers,
                crews,
                pilots
        );
    }

    public static List<String> createRandomPilots(int amount) {
        List<String> pilots = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            pilots.add("pilot_" + i);
        }
        return pilots;
    }

    public static List<String> createRandomCrew(int amount) {
        List<String> crews = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            crews.add("crew_" + i);
        }
        return crews;
    }

    public static Flight createRandomFlightInstance() {
        int number = (int) Math.random();
        int capacity = 30;
        Airline airlineOwn = new Airline("AIA", "American Airline", "history");
        Airport departureAirport = getRandomAirport();
        Airport arrivalAirport = getRandomAirport();
        Date dateFlight = new Date();
        List<Passenger> passengers = getListPassenger(10);
        List<String> pilots = createRandomPilots(5);
        List<String> crews = createRandomCrew(5);

        return new Flight(
                createRandomFlightNumber(5),
                dateFlight,
                passengers,
                crews,
                pilots
        );
    }

    public static Flight createFixedFlightInstance() {
        int number = (int) Math.random();
        int capacity = 30;
        Airline airlineOwn = new Airline("AIA", "American Airline", "history");
        Airport departureAirport = getRandomAirport();
        Airport arrivalAirport = getRandomAirport();
        Date dateFlight = new Date();
        List<Passenger> passengers = getListPassenger(10);
        List<String> pilots = createRandomPilots(5);
        List<String> crews = createRandomCrew(5);

        return new Flight(
                createFixedFlightNumber(5),
                dateFlight,
                passengers,
                crews,
                pilots
        );
    }

    public static List<Flight> generateListFlightInstance(int amount) {
        List<Flight> flightInstances = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (i % 2 ==0) {
                flightInstances.add(createFixedFlightInstance());
            } else {
                flightInstances.add(createRandomFlightInstance());
            }
        }
        return flightInstances;
    }

    public static void initializeData() {
        //Test Agent flow data
        Agent agent = new Agent();
        ReservationSystemFacade facade = new ReservationSystemFacadeImpl();
        Passenger passenger = StorageHandler.getRandomPassenger(5);
        passenger.addReservation(facade.createReservation(agent, passenger, StorageHandler.generateListFlightInstance(10)));

        StorageHandler.addAgent(agent);
        agent.addPassenger(passenger);

        List<Passenger> passengers = getListPassenger(5);
        Agent a1 = getRandomAgent(0);
        a1.addPassenger(passengers.get(0));
        a1.addPassenger(passengers.get(1));
        a1.addPassenger(passengers.get(2));

        Agent a2 = getRandomAgent(0);
        a2.addPassenger(passengers.get(2));
        a2.addPassenger(passengers.get(3));
        a2.addPassenger(passengers.get(4));

        // create reservation
        List<Flight> flights = generateListFlightInstance(5);
        StorageHandler.flights.addAll(flights);

        Reservation reservation = new Reservation();
        for (Flight flight : flights) {
            Ticket reservationTicket =
                    new Ticket(StorageHandler.randomTicketNumber(),
                            reservation.getReservationCode(),
                            flight);
            reservation.addTicket(reservationTicket);
        }

        addReservation(reservation);
        setTickets(reservation.getReservationCode(), reservation.getTickets());

        StorageHandler.createRandomAirports(10);
        StorageHandler.getRandomAirline();

       // Add some
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
    }

    public static void printReservations() {
        for (Reservation r : reservations) {
            System.out.print("reservation: " + r.getAgentId());
        }
    }
}
