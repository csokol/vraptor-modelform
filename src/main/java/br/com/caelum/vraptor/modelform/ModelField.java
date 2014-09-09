package br.com.caelum.vraptor.modelform;

import br.com.caelum.vraptor.modelform.mappers.TypeMappers;

import java.lang.reflect.Field;

/**
 * Created by csokol on 9/9/14.
 */
public class ModelField {
	private final Field field;
	private final TypeMappers typeMappers;

	public ModelField(Field field, TypeMappers typeMappers) {
		this.field = field;
		this.typeMappers = typeMappers;
	}

	public String getName() {
		return field.getName();
	}

	public String getType() {
		return typeMappers.typeOf(field);
	}
}
