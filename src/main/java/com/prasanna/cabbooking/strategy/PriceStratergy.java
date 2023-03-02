package com.prasanna.cabbooking.strategy;

import com.prasanna.cabbooking.Model.Location;

public interface PriceStratergy {
    public Double getPrice(Location fromLocation, Location toLocation);
}
