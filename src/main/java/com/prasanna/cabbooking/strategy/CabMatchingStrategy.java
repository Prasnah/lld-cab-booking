package com.prasanna.cabbooking.strategy;

import com.prasanna.cabbooking.Model.Cab;
import com.prasanna.cabbooking.Model.Rider;

import java.util.List;

public interface CabMatchingStrategy {
    public Cab matchCab(Rider rider, List<Cab> cabs);
}
