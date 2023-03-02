package com.prasanna.cabbooking.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
public class Cab {
    String id;
    Driver driver;
    @Setter
    Trip currentTrip;
    @Setter
    Location currentLocation;
    @Setter
    boolean availability;

    public Cab(String id, Driver driver) {
        this.id = id;
        this.driver = driver;
        this.availability = true;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "id='" + id + '\'' +
                ", driver=" + driver +
                ", currentTrip=" + currentTrip +
                ", currentLocation=" + currentLocation +
                ", availability=" + availability +
                '}';
    }
}
