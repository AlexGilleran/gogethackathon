package com.alexgilleran.goget.dao.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

/**
 * s
 * 
 * @author Alex
 */
public class Reader<M> {
    private final Class<M> clazz;
    private final Map<Field, String> fieldMap = new HashMap<Field, String>();
    private Field idField;

    public Reader(Class<M> clazz) {
	this.clazz = clazz;

	for (Field field : clazz.getDeclaredFields()) {
	    CSVField csvField = field.getAnnotation(CSVField.class);
	    if (csvField != null) {
		fieldMap.put(field, csvField.value());
	    }
	    if (field.isAnnotationPresent(Id.class)) {
		idField = field;
	    }
	}
    }

    public List<M> readList(InputStream inputStream) throws IOException {
	List<M> objList = new ArrayList<M>();

	InputStreamReader fileReader = new InputStreamReader(inputStream);
	ICsvMapReader csvReader = new CsvMapReader(fileReader, CsvPreference.STANDARD_PREFERENCE);

	try {
	    final String[] header = csvReader.getHeader(true);

	    Map<String, String> csvMap;
	    while ((csvMap = csvReader.read(header)) != null) {
		M csvObj = clazz.newInstance();

		for (Entry<Field, String> entry : this.fieldMap.entrySet()) {
		    setValueOnObject(csvObj, csvMap.get(entry.getValue()), entry.getKey());
		}

		objList.add(csvObj);
	    }

	    return objList;
	} catch (InstantiationException | IllegalAccessException e) {
	    throw new RuntimeException(e);
	} finally {
	    csvReader.close();
	}
    }

    // TODO: Fix duplication, but y'know, can't have a hack contest without
    // hacks.
    public Map<Integer, M> readMap(InputStream inputStream) throws IOException {
	if (idField == null) {
	    throw new IllegalStateException("Tried to read map when no id specified");
	}

	Map<Integer, M> objList = new HashMap<Integer, M>();

	InputStreamReader fileReader = new InputStreamReader(inputStream);
	ICsvMapReader csvReader = new CsvMapReader(fileReader, CsvPreference.STANDARD_PREFERENCE);

	try {
	    final String[] header = csvReader.getHeader(true);

	    Map<String, String> csvMap;
	    while ((csvMap = csvReader.read(header)) != null) {
		M csvObj = clazz.newInstance();

		int idNumber = -1;
		for (Entry<Field, String> entry : this.fieldMap.entrySet()) {
		    String value = csvMap.get(entry.getValue());
		    setValueOnObject(csvObj, value, entry.getKey());

		    if (idField == entry.getKey()) {
			idNumber = Integer.parseInt(value);
		    }
		}

		if (idNumber == -1) {
		    throw new RuntimeException();
		}

		objList.put(idNumber, csvObj);
	    }

	    return objList;
	} catch (InstantiationException | IllegalAccessException e) {
	    throw new RuntimeException(e);
	} finally {
	    csvReader.close();
	}
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setValueOnObject(M csvObj, String valueAsString, Field fieldToSet) throws IllegalAccessException {
	boolean accessibility = fieldToSet.isAccessible();
	fieldToSet.setAccessible(true);

	try {
	    if (valueAsString == null) {
		return;
	    } else if (fieldToSet.getType().isAssignableFrom(int.class)) {
		fieldToSet.setInt(csvObj, Integer.parseInt(valueAsString));
	    } else if (fieldToSet.getType().isEnum()) {
		Class<? extends Enum> enumClass = (Class<? extends Enum<?>>) fieldToSet.getType();
		fieldToSet.set(csvObj, Enum.valueOf(enumClass, valueAsString.toUpperCase()));
	    } else if (fieldToSet.getType().isAssignableFrom(DateTime.class)) {
		String dateFormat = fieldToSet.getAnnotation(CSVField.class).dateFormat();
		DateTimeFormatter DateTimeFormatter = DateTimeFormat.forPattern(dateFormat).withZone(DateTimeZone.UTC);
		fieldToSet.set(csvObj, DateTimeFormatter.parseDateTime(valueAsString).toDateTime());
	    } else if (fieldToSet.getType().isAssignableFrom(float.class)) {
		fieldToSet.set(csvObj, Float.parseFloat(valueAsString));
	    } else if (fieldToSet.getType().isAssignableFrom(double.class)) {
		fieldToSet.set(csvObj, Double.parseDouble(valueAsString));
	    } else if (fieldToSet.getType().isAssignableFrom(long.class)) {
		fieldToSet.set(csvObj, Long.parseLong(valueAsString));
	    } else if (fieldToSet.getType().isAssignableFrom(BigDecimal.class)) {
		fieldToSet.set(csvObj, new BigDecimal(valueAsString));
	    } else {
		fieldToSet.set(csvObj, valueAsString);
	    }
	} catch (NumberFormatException e) {
	    throw new NullPointerException("Could not parse " + valueAsString + " as "
		    + fieldToSet.getType().toString());
	}
	fieldToSet.setAccessible(accessibility);
    }
}
