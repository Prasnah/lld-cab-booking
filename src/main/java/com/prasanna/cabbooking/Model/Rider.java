package com.prasanna.cabbooking.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class Rider {
    String riderId;
    String riderName;

    @Override
    public String toString() {
        return "Rider{" +
                "riderId='" + riderId + '\'' +
                ", riderName='" + riderName + '\'' +
                '}';
    }
}
