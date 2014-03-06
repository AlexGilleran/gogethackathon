package com.alexgilleran.goget.dao.csv;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.alexgilleran.goget.dao.GoGetDao;
import com.alexgilleran.goget.model.Booking;
import com.alexgilleran.goget.model.Car;
import com.alexgilleran.goget.model.CarModel;
import com.alexgilleran.goget.model.Pod;
import com.alexgilleran.goget.mybatis.GoGetMapper;

/**
 * Reads everything out of CSVs, puts it in the database.
 * 
 * Originally this was an implementation of {@link GoGetDao}, which explains
 * some of the weirdness of its implementation.
 * 
 */
@Component
public class GoGetCSVReader {
    private final Reader<Pod> podReader = new Reader<Pod>(Pod.class);
    private final Reader<Car> carReader = new Reader<Car>(Car.class);
    private final Reader<CarModel> carModelReader = new Reader<CarModel>(CarModel.class);
    private final Reader<Booking> bookingReader = new Reader<Booking>(Booking.class);

    @Autowired
    private GoGetMapper gogetMapper;

    @PostConstruct
    void init() throws IOException {
	if (gogetMapper.getPodCount() == 0) {
	    gogetMapper.clearBookings();
	    gogetMapper.clearVehicles();
	    gogetMapper.clearCarModels();
	    gogetMapper.clearPods();

	    List<Pod> pods = podReader.readList(new ClassPathResource("GoGet pods.csv").getInputStream());
	    for (Pod pod : pods) {
		gogetMapper.insertPod(pod);
	    }

	    List<CarModel> carModels = carModelReader.readList(new ClassPathResource("Car Model IDs.csv")
		    .getInputStream());
	    for (CarModel carModel : carModels) {
		gogetMapper.insertCarModel(carModel);
	    }

	    List<Car> cars = carReader.readList(new ClassPathResource("Vehicles.csv").getInputStream());
	    for (Car car : cars) {
		gogetMapper.insertCar(car);
	    }

	    List<Booking> bookings = bookingReader.readList(new ClassPathResource("Bookings v2.csv").getInputStream());
	    for (Booking booking : bookings) {
		gogetMapper.insertBooking(booking);
	    }
	}
    }
}
