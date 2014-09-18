package br.com.caelum.vraptor.modelform;

import java.io.InputStream;
import java.util.Scanner;

import javax.enterprise.inject.Vetoed;

import br.com.caelum.vraptor.util.StringUtils;

/**
 * 
 * @author Alberto Souza
 * 
 */

@Vetoed
public class InputResolver {

	private final Class<?> modelType;

	public InputResolver(Class<?> modelType) {
		super();
		this.modelType = modelType;
	}

	public String resolveInputFor(Class<?> fieldType) {
		String javaType = StringUtils.decapitalize(fieldType.getSimpleName());
		InputStream htmlStream = stream(javaType);
		String htmlField = null;
		try (Scanner scanner = new Scanner(htmlStream)) {
			htmlField = scanner.useDelimiter("\\Z").next();
		}
		return htmlField;
	}

	private InputStream stream(String javaType) {
		InputStream htmlStream = InputResolver.class
				.getResourceAsStream("/templates/form/"
						+ StringUtils.decapitalize(modelType.getSimpleName())
						+ "/" + javaType + ".html");
		if (htmlStream == null) {
			htmlStream = InputResolver.class
					.getResourceAsStream("/templates/form/" + javaType
							+ ".html");
		}
		return htmlStream;
	}
}
