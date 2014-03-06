package com.alexgilleran.goget.model;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public interface Fuel {
    DateTime getDateTime();

    int getLitres();

    BigDecimal getAmountSpent();
}
