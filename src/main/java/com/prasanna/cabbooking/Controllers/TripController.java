package com.prasanna.cabbooking.Controllers;

import com.prasanna.cabbooking.repository.RiderManger;
import com.prasanna.cabbooking.repository.TripManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/trip/")
public class TripController {

    TripManager tripManager;
    RiderManger riderManger;

    public TripController(TripManager tripManager, RiderManger ridermanager) {
        this.tripManager = tripManager;
        this.riderManger = ridermanager;
    }

    @GetMapping("fetch/history")
    public ResponseEntity fetchTripHistory(String riderId) {
        return new ResponseEntity<>(tripManager.fetchHistory(riderId), HttpStatus.OK);
    }

}
