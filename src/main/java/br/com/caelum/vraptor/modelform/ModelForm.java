package br.com.caelum.vraptor.modelform;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

@Named("form")
@ApplicationScoped
public class ModelForm {

	private TemplateGenerator templateGenerator;

	public ModelForm() {
		// TODO Auto-generated constructor stub
	}

	@Inject
	public ModelForm(TemplateGenerator templateGenerator) {
		this.templateGenerator = templateGenerator;
	}

	public String buildFor(Object model) {
		Collection<ModelField> fields = getFields(model.getClass());
		Map<String, Object> params = new HashMap<>();
		FormGenerator formGenerator = new FormGenerator(model.getClass(),
				new InputResolver(model.getClass(), templateGenerator));
		params.put("modelFields", fields);
		params.put("formGenerator", formGenerator);

		return templateGenerator.generate(params, "/form/model_form.ftl");
	}

	private Collection<ModelField> getFields(Class<?> modelType) {
		MirrorList<Field> fields = new Mirror().on(modelType).reflectAll()
				.fields();
		return Collections2.transform(fields, fieldToModelField());
	}

	private Function<Field, ModelField> fieldToModelField() {
		return new Function<Field, ModelField>() {
			@Override
			public ModelField apply(Field input) {
				return new ModelField(input);
			}
		};
	}

}
