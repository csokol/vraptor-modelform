package br.com.caelum.vraptor.modelform.mappers;

import java.lang.reflect.Field;

public class StringMapper implements TypeMapper {
	@Override
	public boolean handle(Field field) {
		return field.getType().equals(String.class);
	}

	@Override
	public String typeOf(Field field) {
		return "text";
	}
}
