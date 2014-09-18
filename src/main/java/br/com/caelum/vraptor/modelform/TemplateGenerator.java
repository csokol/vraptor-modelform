package br.com.caelum.vraptor.modelform;

import java.io.StringWriter;
import java.util.Map;

import javax.inject.Inject;

import br.com.caelum.vraptor.freemarker.FreemarkerConfiguration;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class TemplateGenerator {

	private Configuration configuration;

	@Inject
	public TemplateGenerator(FreemarkerConfiguration configuration) {
		super();
		this.configuration = configuration.getConfiguration();
	}

	public String generate(Map<String, Object> params, String path) {
		try {
			StringWriter output = new StringWriter();
			Template template = configuration.getTemplate(path);

			template.process(params, output);
			output.flush();
			return output.getBuffer().toString();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}
