package com.prasanna.cabbooking.repository;

import com.prasanna.cabbooking.Exception.RiderAlreadyExistsException;
import com.prasanna.cabbooking.Exception.RiderNotFoundException;
import com.prasanna.cabbooking.Model.Rider;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RiderManger {
    private Map<String, Rider> riderRepo = new HashMap<>();

    public void registerRider(Rider rider) {
        if (riderRepo.containsKey(rider.getRiderId())) {
            throw new RiderAlreadyExistsException();
        } else {
            riderRepo.put(rider.getRiderId(), rider);
        }
    }

    public Rider getRider(String riderId) {
        if (riderRepo.containsKey(riderId)) {
            return riderRepo.get(riderId);
        }
        throw new RiderNotFoundException();
    }

}
