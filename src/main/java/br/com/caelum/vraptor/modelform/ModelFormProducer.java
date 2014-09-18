package br.com.caelum.vraptor.modelform;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import freemarker.template.Configuration;

public class ModelFormProducer {

	private Configuration configuration;

	@Inject
	public ModelFormProducer(Configuration configuration) {
		super();
		this.configuration = configuration;
	}

	@Produces
	public <T> ModelForm<T> build(InjectionPoint injectionPoint) {
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		Class<T> modelType = (Class<T>) type.getActualTypeArguments()[0];
		return new ModelForm<T>(modelType, new InputResolver(modelType,
				configuration));
	}
}
