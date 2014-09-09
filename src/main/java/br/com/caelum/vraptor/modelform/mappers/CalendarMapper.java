package br.com.caelum.vraptor.modelform.mappers;

import java.lang.reflect.Field;
import java.util.Calendar;

public class CalendarMapper implements TypeMapper {
	@Override
	public boolean handle(Field field) {
		return field.getType().equals(Calendar.class);
	}

	@Override
	public String typeOf(Field field) {
		return "date";
	}
}
