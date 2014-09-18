package br.com.caelum.vraptor.modelform;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Vetoed;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;
import br.com.caelum.vraptor.util.StringUtils;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

@Vetoed
public class ModelForm<T> {

	private final Class<T> modelType;
	private final InputResolver inputResolver;
	private Map<String, Object> params = new HashMap<>();

	public ModelForm(Class<T> modelType,InputResolver inputResolver) {
		this.modelType = modelType;
		this.inputResolver = inputResolver;
	}

	public Collection<ModelField> getFields() {
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

	public String inputFor(ModelField modelField) {
		params.put("name", getModelName() + "." + modelField.getName());
		String html = inputResolver.resolveInputFor(modelField.getJavaType(),params);
		return html;
	}

	public String getModelName() {
		return StringUtils.decapitalize(this.modelType.getSimpleName());
	}

}
