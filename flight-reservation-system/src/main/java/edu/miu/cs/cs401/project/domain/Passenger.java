package edu.miu.cs.cs401.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Passenger {
    private final UUID uuid = UUID.randomUUID();
    private final List<Reservation> reservations = new ArrayList<>();
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private Address residenceAddress;


    public Passenger(String firstName, String lastName, Date birthDate, String email,
                     Address residenceAddress) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.residenceAddress = residenceAddress;
    }
    public UUID getUuid() {
        return uuid;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public String getEmail() {
        return email;
    }
    public Address getResidenceAddress() {
        return residenceAddress;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setResidenceAddress(Address residenceAddressvoid) {
        this.residenceAddress = residenceAddress;
    }

    // reservation part
    public List<Reservation> getReservations() {
        return reservations;
    }
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }
}
