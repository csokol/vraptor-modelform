package br.com.caelum.vraptor.modelform.mappers;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.lang.reflect.Field;

public class TypeMappers {
	@Inject
	private Instance<TypeMapper> mappers;

	public String typeOf(Field field) {
		for (TypeMapper mapper : mappers) {
			if (mapper.handle(field)) {
				return mapper.typeOf(field);
			}
		}
		throw new IllegalArgumentException("Impossible to map " + field + ". Please create a TypeMapper to implement a custom mapping");
	}
}
