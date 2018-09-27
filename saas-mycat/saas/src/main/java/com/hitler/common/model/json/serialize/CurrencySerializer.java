package com.hitler.common.model.json.serialize;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CurrencySerializer extends JsonSerializer<Double> {

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
	
	@Override
	public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeString(DECIMAL_FORMAT.format(value));
	}

}
