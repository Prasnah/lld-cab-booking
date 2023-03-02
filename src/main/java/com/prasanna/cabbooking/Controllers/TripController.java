package com.prasanna.cabbooking.Controllers;

import com.prasanna.cabbooking.Model.Location;
import com.prasanna.cabbooking.Model.Rider;
import com.prasanna.cabbooking.Model.Trip;
import com.prasanna.cabbooking.repository.RiderManger;
import com.prasanna.cabbooking.repository.TripManager;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/trip/")
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
