package com.prasanna.cabbooking;

import com.prasanna.cabbooking.Controllers.CabController;
import com.prasanna.cabbooking.Controllers.RiderController;
import com.prasanna.cabbooking.Controllers.TripController;
import com.prasanna.cabbooking.Exception.CabNotAvailableException;
import com.prasanna.cabbooking.Exception.TripNotFoundException;
import com.prasanna.cabbooking.Model.Cab;
import com.prasanna.cabbooking.Model.Driver;
import com.prasanna.cabbooking.Model.Location;
import com.prasanna.cabbooking.Model.Rider;
import com.prasanna.cabbooking.repository.CabsManager;
import com.prasanna.cabbooking.repository.RiderManger;
import com.prasanna.cabbooking.repository.TripManager;
import com.prasanna.cabbooking.strategy.CabMatchingStrategy;
import com.prasanna.cabbooking.strategy.DefaultPricingStatergy;
import com.prasanna.cabbooking.strategy.NaturalOrderCabMatching;
import com.prasanna.cabbooking.strategy.PriceStratergy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LldCabbookingApplication.class)
class LldCabbookingApplicationTests {
    CabController cabController;
    RiderController riderController;
    TripController tripController;

    @BeforeEach
    void contextLoad() {
        CabsManager cabsManager = new CabsManager();
        RiderManger riderManger = new RiderManger();

        CabMatchingStrategy cabMatchingStrategy = new NaturalOrderCabMatching();
        PriceStratergy priceStratergy = new DefaultPricingStatergy();

        TripManager tripManager = new TripManager(cabsManager, riderManger, cabMatchingStrategy, priceStratergy);
        cabController = new CabController(cabsManager, tripManager);
        riderController = new RiderController(riderManger, tripManager);
        tripController = new TripController(tripManager, riderManger);
    }

    @Test
    void bookCabAndRide() {
        Cab cab1 = new Cab("1", new Driver("1", "d1"));
        Cab cab2 = new Cab("2", new Driver("2", "d2"));
        Cab cab3 = new Cab("3", new Driver("3", "d3"));
        Cab cab4 = new Cab("4", new Driver("4", "d4"));
        cabController.registerCab(cab1);
        cabController.registerCab(cab2);
        cabController.registerCab(cab3);
        cabController.registerCab(cab4);

        Assertions.assertEquals("1", cabController.fetchCabs("1").getBody().getId());

        Rider rider1 = new Rider("r1", "rider1");
        Rider rider2 = new Rider("r2", "rider2");
        Rider rider3 = new Rider("r3", "rider3");
        Rider rider4 = new Rider("r4", "rider4");
        riderController.registerRider(rider1);
        riderController.registerRider(rider2);
        riderController.registerRider(rider3);
        riderController.registerRider(rider4);

        Assertions.assertEquals("r4", riderController.fetchRider("r4").getBody().getRiderId());

        cabController.updateCabLocation("1", new Location(0.0, 2.0));
        cabController.updateCabLocation("2", new Location(10.0, 22.0));
        cabController.updateCabLocation("3", new Location(4.4, 11.0));
        cabController.updateCabLocation("4", new Location(5.0, 2.0));

        cabController.updateCabAvailability("4", false);
        riderController.createTrip("r3", new Location(2.5, 3.5), new Location(10.3, 22.2));
        riderController.createTrip("r4", new Location(1.0, 10.5), new Location(101.3, 22.2));

        System.out.println("## Current trips for rider r3, r4");
        System.out.println(tripController.fetchTripHistory("r3").getBody());
        System.out.println(tripController.fetchTripHistory("r4").getBody());

        System.out.println("## Current trips for rider r3, r4");
        System.out.println(tripController.fetchTripHistory("r3").getBody());
        System.out.println(tripController.fetchTripHistory("r4").getBody());

        cabController.updateCabLocation("1", new Location(50.0, 50.0));
      //  Assertions.assertThrows(TripNotFoundException.class, () -> cabController.endTrip("1"));
        cabController.endTrip("1");
        Assertions.assertThrows(CabNotAvailableException.class, () -> riderController.createTrip("r1", new Location(5.0, 180.0), new Location(0.0, 0.0)));
    }


}
