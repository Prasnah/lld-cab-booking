package com.prasanna.cabbooking.Controllers;

import com.prasanna.cabbooking.Model.Cab;
import com.prasanna.cabbooking.Model.Location;
import com.prasanna.cabbooking.Model.Rider;
import com.prasanna.cabbooking.repository.CabsManager;
import com.prasanna.cabbooking.repository.RiderManger;
import com.prasanna.cabbooking.repository.TripManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController("api/cab/")
public class CabController {


    CabsManager cabManger;
    TripManager tripManager;

    public CabController(CabsManager cabManger, TripManager tripManager) {
        this.cabManger = cabManger;
        this.tripManager = tripManager;
    }

    @PostMapping("register")
    public ResponseEntity registerCab(@RequestBody Cab cab) {
        cabManger.registerCab(cab);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("fetch")
    public ResponseEntity<Cab> fetchCabs(@PathVariable String cabId) {
        return new ResponseEntity<>(cabManger.getCab(cabId), HttpStatus.OK);
    }

    @PutMapping("update/location")
    public ResponseEntity<Cab> updateCabLocation(@PathVariable String cabId, @PathVariable Location location) {
        return new ResponseEntity<>(cabManger.updateCabLocation(cabId, location), HttpStatus.OK);
    }

    @PutMapping("update/availability")
    public ResponseEntity<Cab> updateCabAvailability(@PathVariable String cabId, @PathVariable boolean availability) {
        return new ResponseEntity<>(cabManger.updateCabAvailability(cabId, availability), HttpStatus.OK);
    }

    @PutMapping("end")
    public ResponseEntity endTrip(String cabId) {
        tripManager.endTrip(cabManger.getCab(cabId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
