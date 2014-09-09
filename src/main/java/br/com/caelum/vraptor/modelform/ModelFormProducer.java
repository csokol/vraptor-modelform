package br.com.caelum.vraptor.modelform;

import br.com.caelum.vraptor.modelform.mappers.TypeMappers;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;

public class ModelFormProducer {

	@Inject
	private TypeMappers mappers;

	@Produces
	public <T> ModelForm<T> build(InjectionPoint injectionPoint) {
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		Class<T> modelType = (Class<T>) type.getActualTypeArguments()[0];
		return new ModelForm<T>(modelType, mappers);
	}
}
