package com.prasanna.cabbooking.Model;

import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

public class Trip {
    Rider rider;
    Cab cab;
    Location startLocation;
    Location endLocation;
    @Setter
    TripStatus tripStatus;
    @Setter
    Double fare;

    public Trip(@NonNull Rider rider, @NonNull Cab cab, @NonNull Location startLocation,
                @NonNull Location endLocation) {
        this.rider = rider;
        this.cab = cab;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.tripStatus = TripStatus.IN_PROGRESS;
    }

    public void endTrip() {
        this.tripStatus = TripStatus.FINISHED;
    }

    @Override
    public String toString(){
        return tripStatus.toString();
    }
}
