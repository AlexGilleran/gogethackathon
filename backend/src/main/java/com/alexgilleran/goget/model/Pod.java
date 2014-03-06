package com.alexgilleran.goget.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.alexgilleran.goget.dao.csv.CSVField;
import com.alexgilleran.goget.dao.csv.Id;

public class Pod {
    @Id
    @CSVField("Pod_ID")
    private int podId;
    @CSVField("area_name")
    private String areaName;
    @CSVField("city")
    private String city;
    @CSVField("state")
    private String state;
    @CSVField("pod_type")
    private PodType podType;
    @CSVField("area_latitude")
    private String areaLatitude;
    @CSVField("area_longitude")
    private String areaLongitude;
    @CSVField("region_id")
    private String regionId;
    @CSVField("local_government_zone")
    private String localGovernmentZone;
    @CSVField("postcode")
    private String postCode;
    @CSVField("timezone_id")
    private int timezone_id;
    @CSVField("walkscore")
    private int walkscore;
    @CSVField("number_of_bays")
    private int numberOfBays;

    private List<Car> cars = new ArrayList<Car>();

    public int getPodId() {
	return podId;
    }

    public String getAreaName() {
	return areaName;
    }

    public String getCity() {
	return city;
    }

    public String getState() {
	return state;
    }

    public PodType getPodType() {
	return podType;
    }

    public String getAreaLatitude() {
	return areaLatitude;
    }

    public String getAreaLongitude() {
	return areaLongitude;
    }

    public String getRegionId() {
	return regionId;
    }

    public String getLocalGovernmentZone() {
	return localGovernmentZone;
    }

    public String getPostCode() {
	return postCode;
    }

    public int getTimezone_id() {
	return timezone_id;
    }

    public int getWalkscore() {
	return walkscore;
    }

    public int getNumberOfBays() {
	return numberOfBays;
    }

    public float getTotalCarbonDioxide(float maxTripLength) {
	float co2 = 0;

	for (Car car : cars) {
	    co2 += car.getTotalCarbonDioxide(maxTripLength);
	}

	return co2;
    }

    public float getTotalCityFuel(float maxTripLength) {
	float cityFuel = 0;

	for (Car car : cars) {
	    cityFuel += car.getTotalCityFuel(maxTripLength);
	}

	return cityFuel;
    }

    public float getSaveableKms(float maxTripLength) {
	float kms = 0;

	for (Car car : cars) {
	    kms += car.getTotalKmsLogged(maxTripLength);
	}

	return kms;
    }

    public int getSaveableTripCount(float maxTripLength) {
	int trips = 0;

	for (Car car : cars) {
	    trips += car.getTotalTripCount(maxTripLength);
	}

	return trips;
    }

    public int getTotalTripCount() {
	int trips = 0;

	for (Car car : cars) {
	    trips += car.getTotalTripCount();
	}

	return trips;
    }

    public float getSaveableTripFraction(float maxTripLength) {
	int totalTripCount = getTotalTripCount();
	if (totalTripCount > 0) {
	    return (float) getSaveableTripCount(maxTripLength) / (float) getTotalTripCount();
	} else {
	    return 0;
	}
    }

    public List<Car> getCars() {
	return cars;
    }

    public void addCar(Car car) {
	cars.add(car);
    }

    private static abstract class SavableKmsComparator implements Comparator<Pod> {
	protected float maxTrip;

	public SavableKmsComparator(float maxTrip) {
	    this.maxTrip = maxTrip;
	}
    }

    public static class CO2SavedComparator extends SavableKmsComparator {
	public CO2SavedComparator(float maxTrip) {
	    super(maxTrip);
	}

	@Override
	public int compare(Pod o1, Pod o2) {
	    return Float.compare(o2.getTotalCarbonDioxide(maxTrip), o1.getTotalCarbonDioxide(maxTrip));
	}
    }

    public static class FuelSavedComparator extends SavableKmsComparator {
	public FuelSavedComparator(float maxTrip) {
	    super(maxTrip);
	}

	@Override
	public int compare(Pod o1, Pod o2) {
	    return Float.compare(o2.getTotalCityFuel(maxTrip), o1.getTotalCityFuel(maxTrip));
	}
    }

    public static class PercentageComparator extends SavableKmsComparator {
	public PercentageComparator(float maxTrip) {
	    super(maxTrip);
	}

	@Override
	public int compare(Pod o1, Pod o2) {
	    int comparison = Float.compare(o2.getSaveableTripFraction(maxTrip), o1.getSaveableTripFraction(maxTrip));
	    if (comparison != 0) {
		return comparison;
	    } else {
		return Float.compare(o2.getSaveableKms(maxTrip), o1.getSaveableKms(maxTrip));
	    }
	}
    }

    public enum PodType {
	DEDICATED, FLOATING
    }
}
