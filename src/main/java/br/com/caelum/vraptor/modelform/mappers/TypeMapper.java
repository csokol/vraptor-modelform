package br.com.caelum.vraptor.modelform.mappers;

import java.lang.reflect.Field;

public interface TypeMapper {
	boolean handle(Field field);

	String typeOf(Field field);
}
