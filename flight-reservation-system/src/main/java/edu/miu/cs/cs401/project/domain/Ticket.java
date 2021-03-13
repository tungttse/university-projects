package edu.miu.cs.cs401.project.domain;

import java.util.UUID;

public class Ticket {
    private final UUID uuid = UUID.randomUUID();
    private final String number;
    private final String reservationCode;
    private final Flight flight;

    public Ticket(String number, String reservationCode, Flight flight) {
        super();
        this.number = number;
        this.reservationCode = reservationCode;
        this.flight = flight;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getNumber() {
        return number;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public Flight getFlight() {
        return flight;
    }
}

