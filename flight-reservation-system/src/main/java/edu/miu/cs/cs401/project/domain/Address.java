package edu.miu.cs.cs401.project.domain;

import java.util.UUID;

public class Address {
    private final UUID uuid = UUID.randomUUID();
    private String street;
    private String city;
    private String state;
    private int zip;
    public Address(String street, String city, String state, int zip) {
        super();
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public UUID getUuid() {
        return uuid;
    }
    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public int getZip() {
        return zip;
    }
    @Override
    public String toString() {
        return "Address{" +
                "uuid=" + uuid +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                '}';
    }
}

