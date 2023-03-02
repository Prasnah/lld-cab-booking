package com.prasanna.cabbooking.Model;


import lombok.AllArgsConstructor;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@AllArgsConstructor
public class Location {
    Double startPoint;
    Double endPoint;

    public double distanceFrom(Location location2) {
        Double distance = sqrt(pow(this.startPoint - location2.startPoint, 2) + pow(this.endPoint - location2.endPoint, 2));
        System.out.println(distance);
        return distance;
    }
}
