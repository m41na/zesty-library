package com.practicaldime.quest.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

public class SimpleDateScalar extends GraphQLScalarType {

	private static SimpleDateFormat mmddyyyy = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
	private static SimpleDateFormat yyyyddmm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public SimpleDateScalar() {
		super("DateTime", "A Simple DateTime Scalar", new Coercing<Date, String>() {
			@Override
			public String serialize(Object input) throws CoercingSerializeException {
				Date date;
				if (input instanceof Date) {
					date = (Date) input;
				} else {
					throw new CoercingSerializeException(
							"Expected something we can convert to 'java.util.Date' but was '" + input.getClass()
									+ "'.");
				}
				try {
					return mmddyyyy.format(date);
				} catch (Exception e) {
					try {
						return yyyyddmm.format(date);
					} catch (Exception ex) {
						throw new CoercingSerializeException(
								"Unable to turn TemporalAccessor into Date because of : '" + ex.getMessage() + "'.");
					}
				}
			}

			@Override
			public Date parseValue(Object input) throws CoercingParseValueException {
				Date date;
				if (input instanceof Date) {
					date = (Date) input;
				} else if (input instanceof String) {
					date = parseOffsetDateTime(input.toString(), CoercingParseValueException::new);
				} else {
					throw new CoercingParseValueException("Expected a 'String' but was '" + input.getClass() + "'.");
				}
				return date;
			}

			@Override
			public Date parseLiteral(Object input) throws CoercingParseLiteralException {
				if (!(input instanceof StringValue)) {
					throw new CoercingParseLiteralException(
							"Expected AST type 'StringValue' but was '" + input.getClass() + "'.");
				}
				return parseOffsetDateTime(((StringValue) input).getValue(), CoercingParseLiteralException::new);
			}

			private Date parseOffsetDateTime(String date, Function<String, RuntimeException> exceptionMaker) {
				try {
					return mmddyyyy.parse(date);
				} catch (ParseException e) {
					try {
						return yyyyddmm.parse(date);
					} catch (ParseException ex) {
						throw exceptionMaker
								.apply("Invalid RFC3339 value : '" + date + "'. because of : '" + ex.getMessage() + "'");
					}
				}
			}
		});
	}
}