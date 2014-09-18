package br.com.caelum.vraptor.modelform;

import java.lang.reflect.Field;
import java.util.Collection;

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
		String html = inputResolver.resolveInputFor(modelField.getJavaType());
		return html.replaceAll("#name",
				getModelName() + "." + modelField.getName());
	}

	public String getModelName() {
		return StringUtils.decapitalize(this.modelType.getSimpleName());
	}

}
