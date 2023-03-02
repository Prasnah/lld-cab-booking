package com.prasanna.cabbooking.repository;

import com.prasanna.cabbooking.Exception.CabNotAvailableException;
import com.prasanna.cabbooking.Exception.TripNotFoundException;
import com.prasanna.cabbooking.Model.*;
import com.prasanna.cabbooking.strategy.CabMatchingStrategy;
import com.prasanna.cabbooking.strategy.PriceStratergy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Repository
public class TripManager {
    Map<String, List<Trip>> tripRepo = new HashMap<>();
    private CabsManager cabsManager;
    private RiderManger riderManger;

    private static final Double MAX_DISTANCE_TO_PICKUP = 10.0;
    private CabMatchingStrategy cabMatchingStrategy;
    private PriceStratergy priceStratergy;

    public TripManager(CabsManager cabsManager, RiderManger riderManger, CabMatchingStrategy cabMatchingStrategy, PriceStratergy priceStratergy) {
        this.cabsManager = cabsManager;
        this.riderManger = riderManger;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.priceStratergy = priceStratergy;
    }

    @Async
    public CompletableFuture<Trip> createTrip(Rider rider, Location startLocation, Location endLocation) {

        List<Cab> availableCabs = cabsManager.getAllCabs()
                .stream()
                .filter(Cab::isAvailability)
                .filter(cab -> cab.getCurrentTrip() == null)
                .filter(cab -> startLocation.distanceFrom(cab.getCurrentLocation()) <= MAX_DISTANCE_TO_PICKUP)
                .toList();
        Cab selectedCab = cabMatchingStrategy.matchCab(rider, availableCabs);
        if (selectedCab == null) {
            throw new CabNotAvailableException();
        }
        Double price = priceStratergy.getPrice(startLocation, endLocation);
        Trip newTrip = new Trip(rider, selectedCab, startLocation, endLocation);
        newTrip.setFare(price);
        newTrip.setTripStatus(TripStatus.IN_PROGRESS);
        if (tripRepo.containsKey(rider.getRiderId())) {
            tripRepo.get(rider.getRiderId()).add(newTrip);
        } else {
            tripRepo.put(rider.getRiderId(), List.of(newTrip));
        }
        selectedCab.setCurrentTrip(newTrip);
        return CompletableFuture.completedFuture(newTrip);

    }

    public void endTrip(Cab cab) {
        if (cab.getCurrentTrip() == null) {
            throw new TripNotFoundException();
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }

    public List<Trip> fetchHistory(String riderId) {
        return tripRepo.get(riderId);
    }
}
