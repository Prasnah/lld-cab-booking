package com.prasanna.cabbooking.Controllers;

import com.prasanna.cabbooking.Model.Location;
import com.prasanna.cabbooking.Model.Rider;
import com.prasanna.cabbooking.Model.Trip;
import com.prasanna.cabbooking.repository.RiderManger;
import com.prasanna.cabbooking.repository.TripManager;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/rider/")
public class RiderController {

    RiderManger riderManger;

    TripManager tripManager;

    public RiderController(RiderManger riderManger, TripManager tripManager) {
        this.riderManger = riderManger;
        this.tripManager = tripManager;
    }

    @PostMapping("register")
    public ResponseEntity registerRider(@RequestBody Rider rider) {
        riderManger.registerRider(rider);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("fetch")
    public ResponseEntity<Rider> fetchRider(@PathVariable String riderId) {

        return new ResponseEntity<>(riderManger.getRider(riderId), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Trip> createTrip(@NonNull String riderId, @NonNull Location startLocation, @NonNull Location endLocation) {
        return new ResponseEntity<>(tripManager.createTrip(riderManger.getRider(riderId), startLocation, endLocation).join(), HttpStatus.CREATED);
    }
}
