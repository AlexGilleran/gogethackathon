package com.alexgilleran.goget.rest.adapters;

import com.alexgilleran.goget.model.Pod;

public class PodWrapper {
    private final Pod pod;
    private final float maxTripLength;
    private final int placeInOrder;

    public PodWrapper(int placeInOrder, Pod pod, float maxTripLength) {
	this.placeInOrder = placeInOrder;
	this.pod = pod;
	this.maxTripLength = maxTripLength;
    }

    public int getId() {
	return pod.getPodId();
    }

    public int getPlaceInOrder() {
	return placeInOrder;
    }

    public int getTripCount() {
	return pod.getTotalTripCount();
    }

    public int getSaveableTripCount() {
	return pod.getSaveableTripCount(maxTripLength);
    }

    public float getSavableCo2() {
	return pod.getTotalCarbonDioxide(maxTripLength) / 1000;
    }

    public float getSavableCityFuel() {
	return pod.getTotalCityFuel(maxTripLength);
    }

    public float getSavableTripFraction() {
	return pod.getSaveableTripFraction(maxTripLength);
    }

    public String getName() {
	return pod.getAreaName();
    }

    public double getLatitude() {
	return Float.parseFloat(pod.getAreaLatitude());
    }

    public double getLongitude() {
	return Float.parseFloat(pod.getAreaLongitude());
    }
}
