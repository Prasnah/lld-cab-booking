package com.prasanna.cabbooking.repository;

import com.prasanna.cabbooking.Exception.CabAlreadyExistsException;
import com.prasanna.cabbooking.Exception.CabNotFoundException;
import com.prasanna.cabbooking.Model.Cab;
import com.prasanna.cabbooking.Model.Location;
import com.prasanna.cabbooking.Model.TripStatus;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CabsManager {

    Map<String, Cab> cabRepo = new HashMap<>();

    public void registerCab(Cab cab) {
        if (cabRepo.containsKey(cab.getId())) {
            throw new CabAlreadyExistsException();
        } else {
            cabRepo.put(cab.getId(), cab);
        }
    }

    public Cab getCab(String riderId) {
        if (!cabRepo.containsKey(riderId)) {
            throw new CabNotFoundException();
        }
        return cabRepo.get(riderId);
    }

    public List<Cab> getAllCabs() {
        if (cabRepo.size() == 0) {
            throw new CabNotFoundException();
        }
        return new ArrayList<>(cabRepo.values());
    }

    public Cab updateCabLocation(String cabId, Location location) {

        if (cabRepo.containsKey(cabId)) {
            Cab cab = cabRepo.get(cabId);
            cab.setCurrentLocation(location);
            return cab;
        } else {
            // exception
        }
        return null;
    }

    public Cab updateCabAvailability(String cabId, boolean availability) {

        if (cabRepo.containsKey(cabId)) {
            Cab cab = cabRepo.get(cabId);
            cab.setAvailability(availability);
            return cab;
        } else {
            throw new CabNotFoundException();
        }
    }

}
