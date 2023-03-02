package com.prasanna.cabbooking.strategy;

import com.prasanna.cabbooking.Model.Cab;
import com.prasanna.cabbooking.Model.Rider;

import java.util.List;

public class NaturalOrderCabMatching implements CabMatchingStrategy {
    @Override
    public Cab matchCab(Rider rider, List<Cab> cabs) {
        return cabs.size() > 0 ? cabs.get(0) : null;
    }
}
