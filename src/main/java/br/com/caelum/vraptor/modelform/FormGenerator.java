package br.com.caelum.vraptor.modelform;

import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.util.StringUtils;

public class FormGenerator {

	private Class<?> modelType;
	private InputResolver inputResolver;

	public FormGenerator(Class<?> modelType,InputResolver inputResolver) {
		super();
		this.modelType = modelType;
		this.inputResolver = inputResolver;
	}
	
	public String inputFor(ModelField modelField) {
		Map<String, Object> params = new HashMap<>();
		String modelName = StringUtils.decapitalize(modelType.getSimpleName());
		params.put("name", modelName + "." + modelField.getName());
		params.put("label", StringUtils.capitalize(modelField.getName()));
		String html = inputResolver.resolveInputFor(modelField.getJavaType(),
				params);
		return html;
	}

}
