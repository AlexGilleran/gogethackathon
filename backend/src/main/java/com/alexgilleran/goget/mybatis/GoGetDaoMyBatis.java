package com.alexgilleran.goget.mybatis;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexgilleran.goget.dao.GoGetDao;
import com.alexgilleran.goget.model.Booking;
import com.alexgilleran.goget.model.Car;
import com.alexgilleran.goget.model.CarModel;
import com.alexgilleran.goget.model.Pod;

/**
 * This is really dumb - basically this originally read from CSV files and
 * stored everything in memory, then I decided I wanted to play around with
 * MyBatis so I added a data layer.
 * 
 * However I don't have time / cbf to actually use the advantages of the
 * database to do stuff like sorting, so I'm going to leave that as it is and
 * just retrieve everything out of the database and store it in memory.
 * 
 * Bad, but hey. Hackfest.
 */
@Component
public class GoGetDaoMyBatis implements GoGetDao {
    @Autowired
    private GoGetMapper mapper;

    private List<Pod> pods;

    @PostConstruct
    public void init() {
	this.pods = mapper.getPods();

	for (Pod pod : pods) {
	    populatePod(pod);
	}
    }

    @Override
    public List<Pod> getPods() {
	return pods;
    }

    @Override
    public Pod getPod(long id) {
	Pod pod = mapper.getPod(id);
	if (pod != null) {
	    populatePod(pod);
	}
	return pod;
    }

    private void populatePod(Pod pod) {
	List<Car> cars = mapper.getVehiclesForPod(pod.getPodId());
	for (Car car : cars) {
	    populateCar(car);
	    pod.addCar(car);
	}
    }

    private void populateCar(Car car) {
	for (Booking booking : mapper.getBookingsForVehicle(car.getCarId())) {
	    car.addBooking(booking);
	}
	CarModel model = mapper.getCarModel(car.getModelId());
	car.setCarModel(model);
    }
}
