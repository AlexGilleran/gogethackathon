package com.alexgilleran.goget.model;

import org.joda.time.DateTime;

import com.alexgilleran.goget.dao.csv.CSVField;
import com.alexgilleran.goget.dao.csv.Id;

public class TripLogEntry {
    @CSVField("trip_log_id")
    @Id
    private int tripLogId;

    @CSVField("unit_id")
    private int unitId;

    @CSVField("driverID_anon")
    private int driverId;

    @CSVField(value = "start_time", dateFormat = "d/MM/yy H:mm")
    private DateTime startTime;

    @CSVField(value = "stop_time", dateFormat = "d/MM/yy H:mm")
    private DateTime stopTime;

    @CSVField("booking_id")
    private int bookingId;

    @CSVField("distance")
    private float distance;

    public int getTripLogId() {
	return tripLogId;
    }

    public int getUnitId() {
	return unitId;
    }

    public int getDriverId() {
	return driverId;
    }

    public DateTime getStartTime() {
	return startTime;
    }

    public DateTime getStopTime() {
	return stopTime;
    }

    public int getBookingId() {
	return bookingId;
    }

    public float getDistance() {
	return distance;
    }
}
