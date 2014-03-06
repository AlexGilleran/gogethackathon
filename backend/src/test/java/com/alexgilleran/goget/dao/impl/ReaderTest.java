package com.alexgilleran.goget.dao.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.alexgilleran.goget.dao.csv.Reader;
import com.alexgilleran.goget.model.Booking;
import com.alexgilleran.goget.model.Car;
import com.alexgilleran.goget.model.CarModel;
import com.alexgilleran.goget.model.CarModel.Transmission;
import com.alexgilleran.goget.model.Pod;
import com.alexgilleran.goget.model.TripLogEntry;

public class ReaderTest {
    @Test
    public void testMap() throws IOException {
	Reader<Pod> reader = new Reader<Pod>(Pod.class);

	Map<Integer, Pod> pods = reader.readMap(new ClassPathResource("GoGet pods.csv").getInputStream());
	// 157,Adelaide - 101 Sturt
	// Street,Adelaide,SA,Dedicated,-34.933098,138.596509999999,8,10/21/08
	// 0:00,Adelaide City Council,5000,1,88,2
	Pod firstPod = pods.get(157);
	assertEquals(157, firstPod.getPodId());
	assertEquals("Adelaide - 101 Sturt Street", firstPod.getAreaName());
	assertEquals(Pod.PodType.DEDICATED, firstPod.getPodType());
    }

    @Test
    public void testPod() throws IOException {
	Reader<Pod> reader = new Reader<Pod>(Pod.class);

	List<Pod> pods = reader.readList(new ClassPathResource("GoGet pods.csv").getInputStream());
	// 157,Adelaide - 101 Sturt
	// Street,Adelaide,SA,Dedicated,-34.933098,138.596509999999,8,10/21/08
	// 0:00,Adelaide City Council,5000,1,88,2
	Pod firstPod = pods.get(0);
	assertEquals(157, firstPod.getPodId());
	assertEquals("Adelaide - 101 Sturt Street", firstPod.getAreaName());
	assertEquals(Pod.PodType.DEDICATED, firstPod.getPodType());
    }

    @Test
    public void testCar() throws IOException {
	Reader<Car> reader = new Reader<Car>(Car.class);

	List<Car> entries = reader.readList(new ClassPathResource("Vehicles.csv").getInputStream());
	assertEquals(598, entries.size());

	Car firstEntry = entries.get(0);

	// Car_ID,Pod_ID,city,area_name,number_plate,room_name,unit_id,start_date,car_model_id
	// 102,690,Melbourne,Melbourne CBD - 45 Collins Street,ZNW319,Rory the
	// Corolla,1280,15/03/2013 0:00,70

	assertEquals(102, firstEntry.getCarId());
	assertEquals(690, firstEntry.getPodId());
	assertEquals("Melbourne", firstEntry.getCity());
	assertEquals("Melbourne CBD - 45 Collins Street", firstEntry.getAreaName());
	assertEquals("ZNW319", firstEntry.getNumberPlate());
	assertEquals("Rory the Corolla", firstEntry.getCarName());
	assertEquals(1280, firstEntry.getUnitId());
	assertEquals(70, firstEntry.getModelId());
    }

    @Test
    public void testTripLog() throws IOException {
	Reader<TripLogEntry> reader = new Reader<TripLogEntry>(TripLogEntry.class);

	List<TripLogEntry> entries = reader.readList(new ClassPathResource("Trip Logs.csv").getInputStream());
	assertEquals(128832, entries.size());

	TripLogEntry firstEntry = entries.get(0);
	// trip_log_id,unit_id,driverID_anon,start_time,stop_time,booking_id,distance
	// 142499619,937,1055499,1/10/2013 0:03,1/10/2013 0:12,1335826,3.2

	assertEquals(firstEntry.getTripLogId(), 142499619);
	assertEquals(firstEntry.getUnitId(), 937);
	assertEquals(firstEntry.getDriverId(), 1055499);
	assertEquals(firstEntry.getStartTime(), new DateTime(2013, 10, 1, 0, 3, DateTimeZone.UTC));
	assertEquals(firstEntry.getStopTime(), new DateTime(2013, 10, 1, 0, 12, DateTimeZone.UTC));
	assertEquals(firstEntry.getBookingId(), 1335826);
	assertEquals(firstEntry.getDistance(), 3.2f, 0);
    }

    @Test
    public void testCarModels() throws IOException {
	Reader<CarModel> reader = new Reader<CarModel>(CarModel.class);

	Map<Integer, CarModel> entries = reader.readMap(new ClassPathResource("Car Model IDs.csv").getInputStream());
	assertEquals(41, entries.size());

	CarModel firstEntry = entries.get(8);
	// Model
	// id,manufacturer,short_model_name,description,year,transmission,doors,number_in_fleet
	// 8,Toyota,Yaris Hatch YR,1.3L,2010,auto,5,15

	assertEquals(8, firstEntry.getModelId());
	assertEquals("Toyota", firstEntry.getManufacturer());
	assertEquals("Yaris Hatch YR", firstEntry.getShortModelName());
	assertEquals("1.3L", firstEntry.getDescription());
	assertEquals(2010, firstEntry.getYear());
	assertEquals(Transmission.AUTO, firstEntry.getTransmission());
	assertEquals(5, firstEntry.getDoors());
	assertEquals(15, firstEntry.getNumberInFleet());
	assertEquals(6.2f, firstEntry.getConsumptionCity(), 0);
	assertEquals(4.4f, firstEntry.getConsumptionHwy(), 0);
	assertEquals(5.1f, firstEntry.getConsumptionCombined(), 0);
	assertEquals(119f, firstEntry.getCarbonDioxide(), 0);
	assertEquals(0.24f, firstEntry.getCarbonMonoxide(), 0);
	assertEquals(0.07f, firstEntry.getHydroCarbons(), 0);
	assertEquals(0.028f, firstEntry.getNitrogenOxide(), 0);
    }

    @Test
    public void testBookings() throws IOException {
	Reader<Booking> reader = new Reader<Booking>(Booking.class);

	List<Booking> entries = reader.readList(new ClassPathResource("Bookings v2.csv").getInputStream());
	assertEquals(16751, entries.size());

	Booking firstEntry = entries.get(0);

	assertEquals(new DateTime(2013, 9, 26, 0, 0, DateTimeZone.UTC), firstEntry.getStart());
	assertEquals(new DateTime(2013, 10, 2, 0, 0, DateTimeZone.UTC), firstEntry.getEnd());
	assertEquals(1328894, firstEntry.getBookingId());
	assertEquals(1631, firstEntry.getCarId());
	assertEquals(580, firstEntry.getPodId());
	assertEquals(2102, firstEntry.getKmsLogged(), 0);
    }
}
