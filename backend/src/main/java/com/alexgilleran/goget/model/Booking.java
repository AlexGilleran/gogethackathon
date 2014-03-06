package com.alexgilleran.goget.model;

import org.joda.time.DateTime;

import com.alexgilleran.goget.dao.csv.CSVField;
import com.alexgilleran.goget.dao.csv.Id;

public class Booking {
    // start,end_,Booking_ID,start_time,end_time,Car_ID,Pod_ID,booking_method,kms_logged,booking_timestamp,city,state,postcode,DriverID_anon,AccountID_anon
    @CSVField(value = "start", dateFormat = "d/MM/yyyy H:mm")
    private DateTime start;
    @CSVField(value = "end_", dateFormat = "d/MM/yyyy H:mm")
    private DateTime end;
    @CSVField("Booking_ID")
    @Id
    private int bookingId;
    @CSVField("Car_ID")
    private int carId;
    @CSVField("Pod_ID")
    private int podId;
    @CSVField("booking_method")
    private String bookingMethod;
    @CSVField("kms_logged")
    private float kmsLogged;
    @CSVField(value = "booking_timestamp", dateFormat = "d/MM/yyyy H:mm")
    private DateTime bookingTimestamp;
    @CSVField("city")
    private String city;
    @CSVField("state")
    private String state;
    @CSVField("postcode")
    private String postcode;
    @CSVField("DriverID_anon")
    private int driverId;
    @CSVField("AccountID_anon")
    private int accountId;

    public DateTime getStart() {
	return start;
    }

    public DateTime getEnd() {
	return end;
    }

    public int getBookingId() {
	return bookingId;
    }

    public int getCarId() {
	return carId;
    }

    public int getPodId() {
	return podId;
    }

    public float getKmsLogged() {
	return kmsLogged;
    }

    public DateTime getBookingTimestamp() {
	return bookingTimestamp;
    }
}
