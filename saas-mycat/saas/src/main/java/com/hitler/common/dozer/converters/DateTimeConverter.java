package com.hitler.common.dozer.converters;

import java.util.Calendar;
import java.util.Date;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.joda.time.DateTime;

public class DateTimeConverter implements CustomConverter {

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof Date) {
			return new DateTime(sourceFieldValue);
		} else if (sourceFieldValue instanceof DateTime) {
			Calendar result;
			if (existingDestinationFieldValue == null) {
				result = Calendar.getInstance();
			} else {
				result = (Calendar) existingDestinationFieldValue;
			}
			result.setTimeInMillis(((DateTime) sourceFieldValue).getMillis());
			return result.getTime();
		}
		
		throw new MappingException("Misconfigured/unsupported mapping");
		
	}

}
