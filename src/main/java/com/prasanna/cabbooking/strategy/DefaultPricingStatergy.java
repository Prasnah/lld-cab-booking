package com.prasanna.cabbooking.strategy;

import com.prasanna.cabbooking.Model.Location;

public class DefaultPricingStatergy implements PriceStratergy {
    private static final Double PRICE_PER_KM = 10.0;

    @Override
    public Double getPrice(Location fromLocation, Location toLocation) {
        return fromLocation.distanceFrom(toLocation) * PRICE_PER_KM;
    }
}
