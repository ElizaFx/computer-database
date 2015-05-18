package com.excilys.formation.cdb.mapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DateMapper implements AttributeConverter<LocalDate, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDate localDate) {
		return localDate == null ? null : Timestamp.valueOf(LocalDateTime.of(
				localDate, LocalTime.of(0, 0)).format(
				DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
	}

	@Override
	public LocalDate convertToEntityAttribute(Timestamp timestamp) {
		return timestamp == null ? null : timestamp.toLocalDateTime()
				.toLocalDate();
	}

}
