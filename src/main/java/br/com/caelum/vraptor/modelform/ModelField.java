package br.com.caelum.vraptor.modelform;

import java.lang.reflect.Field;

/**
 * Created by csokol on 9/9/14.
 */
public class ModelField {
	private final Field field;

	public ModelField(Field field) {
		this.field = field;
	}

	public String getName() {
		return field.getName();
	}

	public Class<?> getJavaType(){
		return field.getType();
	}
	
	
}
