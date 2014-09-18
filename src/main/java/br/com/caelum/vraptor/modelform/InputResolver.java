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
		InputStream htmlStream = InputResolver.class
				.getResourceAsStream("/templates/" + javaType + ".html");
		String htmlField = null;
		try (Scanner scanner = new Scanner(htmlStream)) {
			htmlField = scanner.useDelimiter("\\Z").next();
		}
		return htmlField;
	}
}
