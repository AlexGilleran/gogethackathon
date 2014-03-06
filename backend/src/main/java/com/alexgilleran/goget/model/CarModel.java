package com.alexgilleran.goget.model;

import com.alexgilleran.goget.dao.csv.CSVField;
import com.alexgilleran.goget.dao.csv.Id;

public class CarModel {
    @CSVField("Model id")
    @Id
    private int modelId;

    @CSVField("manufacturer")
    private String manufacturer;

    @CSVField("short_model_name")
    private String shortModelName;

    @CSVField("description")
    private String description;

    @CSVField("year")
    private int year;

    @CSVField("transmission")
    private Transmission transmission;

    @CSVField("doors")
    private int doors;

    @CSVField("number_in_fleet")
    private int numberInFleet;

    @CSVField("consumptionCity")
    private float consumptionCity;

    @CSVField("consumptionHwy")
    private float consumptionHwy;

    @CSVField("consumptionCombined")
    private float consumptionCombined;

    @CSVField("co2")
    private float carbonDioxide;

    @CSVField("co")
    private float carbonMonoxide;

    @CSVField("hc")
    private float hydroCarbons;

    @CSVField("nox")
    private float nitrogenOxide;

    public int getModelId() {
	return modelId;
    }

    public String getManufacturer() {
	return manufacturer;
    }

    public String getShortModelName() {
	return shortModelName;
    }

    public String getDescription() {
	return description;
    }

    public int getYear() {
	return year;
    }

    public Transmission getTransmission() {
	return transmission;
    }

    public int getNumberInFleet() {
	return numberInFleet;
    }

    public int getDoors() {
	return doors;
    }

    public float getConsumptionCity() {
	return consumptionCity;
    }

    public float getConsumptionHwy() {
	return consumptionHwy;
    }

    public float getConsumptionCombined() {
	return consumptionCombined;
    }

    public float getCarbonDioxide() {
	return carbonDioxide;
    }

    public float getCarbonMonoxide() {
	return carbonMonoxide;
    }

    public float getHydroCarbons() {
	return hydroCarbons;
    }

    public float getNitrogenOxide() {
	return nitrogenOxide;
    }

    public enum Transmission {
	AUTO, MANUAL;
    }
}
