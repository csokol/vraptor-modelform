package br.com.caelum.vraptor.modelform;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ModelFormProducer {

	@Produces
	public <T> ModelForm<T> build(InjectionPoint injectionPoint) {
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		Class<T> modelType = (Class<T>) type.getActualTypeArguments()[0];
		return new ModelForm<>(modelType);
	}
}
