package edu.miu.cs.cs401.project.domain;

import edu.miu.cs.cs401.project.constants.ReservationStatus;
import edu.miu.cs.cs401.project.helpers.StringHelper;

import java.util.*;

public class Reservation {
    private final UUID uuid = UUID.randomUUID();
    private final List<Ticket> tickets = new ArrayList<>();;
    private UUID AgentId = null;

    public ReservationStatus getStatus() {
        return status;
    }

    private ReservationStatus status = ReservationStatus.NONE;
    private String reservationCode;

    /*
     *
     * passenger can make a reservation
     * so we need more constructor without Agent
     *
     */
    public Reservation() {
        this.reservationCode = StringHelper.getRandomAlphaString(6);
    }

    public Reservation(UUID agentId) {
        AgentId = agentId;
        this.reservationCode = StringHelper.getRandomAlphaString(6);
    }

    public Reservation(List<Ticket> tickets) {
        this.tickets.addAll(tickets);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getReservationCode() {
        return this.reservationCode;
    }

    public UUID getAgentId() {
        return AgentId;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public void cancel(){
        this.status = ReservationStatus.CANCEL;
        tickets.clear();
    }

    public void confirm(){
        this.status = ReservationStatus.CONFIRM_PURCHASE;
    }
}
