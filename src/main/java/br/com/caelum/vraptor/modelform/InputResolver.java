package br.com.caelum.vraptor.modelform;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.util.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author Alberto Souza
 * 
 */

@Vetoed
public class InputResolver {

	private final Class<?> modelType;
	private Configuration configuration;

	public InputResolver(Class<?> modelType, Configuration configuration) {
		super();
		this.modelType = modelType;
		this.configuration = configuration;
	}

	public String resolveInputFor(Class<?> fieldType, Map<String, Object> params) {
		String javaType = StringUtils.decapitalize(fieldType.getSimpleName());
		try {
			return getTemplate(javaType,params);
		} catch (IOException | TemplateException e) {
			throw new RuntimeException(e);
		}

	}

	private String getTemplate(String javaType, Map<String, Object> params) throws IOException,
			TemplateException {
		String modelName = StringUtils.decapitalize(modelType.getSimpleName());
		InputStream inputModelStream = InputResolver.class
				.getResourceAsStream("/templates/form/" + modelName + "/"
						+ javaType + ".ftl");

		String path = "form/";
		if (inputModelStream != null) {
			path = path.concat(modelName + "/");
		}

		StringWriter output = new StringWriter();
		Template template = configuration.getTemplate(path.concat(javaType
				+ ".ftl"));
		
		template.process(params, output);
		output.flush();

		return output.getBuffer().toString();
	}
}
