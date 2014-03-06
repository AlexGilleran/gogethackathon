package com.alexgilleran.goget.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.joda.time.DateTime;

import com.alexgilleran.goget.dao.csv.CSVField;
import com.alexgilleran.goget.dao.csv.Id;

public class Car {
    @Id
    @CSVField("Car_ID")
    private int carId;
    @CSVField("Pod_ID")
    private int podId;
    @CSVField("city")
    private String city;
    @CSVField("area_name")
    private String areaName;
    @CSVField("number_plate")
    private String numberPlate;
    @CSVField("room_name")
    private String carName;
    @CSVField("unit_id")
    private int unitId;
    @CSVField(value = "start_DateTime", dateFormat = "d/MM/yy H:mm")
    private DateTime startDateTime;
    @CSVField("car_model_id")
    private int modelId;

    private Pod pod;
    private CarModel carModel;
    private List<Booking> bookings = new ArrayList<Booking>();

    public int getCarId() {
	return carId;
    }

    public int getPodId() {
	return podId;
    }

    public String getCity() {
	return city;
    }

    public String getAreaName() {
	return areaName;
    }

    public String getNumberPlate() {
	return numberPlate;
    }

    public String getCarName() {
	return carName;
    }

    public int getUnitId() {
	return unitId;
    }

    public DateTime getStartDateTime() {
	return startDateTime;
    }

    public Pod getPod() {
	return pod;
    }

    public void setPod(Pod pod) {
	this.pod = pod;
    }

    public int getModelId() {
	return modelId;
    }

    public CarModel getCarModel() {
	return carModel;
    }

    public void setCarModel(CarModel carModel) {
	this.carModel = carModel;
    }

    public void addBooking(Booking booking) {
	bookings.add(booking);
    }

    private List<Booking> getBookingsUnderKms(final float kms) {
	return ListUtils.select(bookings, new Predicate<Booking>() {
	    @Override
	    public boolean evaluate(Booking object) {
		return object.getKmsLogged() <= kms;
	    }
	});
    }

    public int getTotalTripCount() {
	return bookings.size();
    }

    public int getTotalTripCount(final float maxJourneyLength) {
	return getBookingsUnderKms(maxJourneyLength).size();
    }

    public float getTotalKmsLogged(final float maxJourneyLength) {
	float kmsLogged = 0;

	for (Booking booking : getBookingsUnderKms(maxJourneyLength)) {
	    kmsLogged += booking.getKmsLogged();
	}

	return kmsLogged;
    }

    public float getTotalCarbonDioxide(final float maxJourneyLength) {
	if (getCarModel() == null) {
	    return 0;
	}
	return getCarModel().getCarbonDioxide() * getTotalKmsLogged(maxJourneyLength);
    }

    public float getTotalCityFuel(final float maxJourneyLength) {
	if (getCarModel() == null) {
	    return 0;
	}
	return getCarModel().getConsumptionCity() * getTotalKmsLogged(maxJourneyLength) / 100;
    }
}
