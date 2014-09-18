package br.com.caelum.vraptor.modelform;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

public class ModelFormProducer {

	@Produces
	public <T> ModelForm<T> build(InjectionPoint injectionPoint) {
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		Class<T> modelType = (Class<T>) type.getActualTypeArguments()[0];
		return new ModelForm<T>(modelType, new InputResolver(modelType));
	}
}
