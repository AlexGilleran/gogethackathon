package com.alexgilleran.goget.dao.csv;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CSVField {
    public String value();

    public String dateFormat() default "";
}
