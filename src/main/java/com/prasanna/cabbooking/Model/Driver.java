package com.prasanna.cabbooking.Model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Driver {
    String driverId;
    String Name;

    @Override
    public String toString() {
        return "Driver{" +
                "driverId='" + driverId + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
