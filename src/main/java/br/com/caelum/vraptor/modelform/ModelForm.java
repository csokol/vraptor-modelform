package br.com.caelum.vraptor.modelform;

import br.com.caelum.vraptor.modelform.mappers.TypeMappers;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import javax.enterprise.inject.Vetoed;
import java.lang.reflect.Field;
import java.util.Collection;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

@SuppressWarnings("ALL")
@Vetoed
public class ModelForm<T> {

	private final Class<T> modelType;
	private final TypeMappers typeMappers;

	public ModelForm(Class<T> modelType, TypeMappers typeMappers) {
		this.modelType = modelType;
		this.typeMappers = typeMappers;
	}

	public Collection<ModelField> getFields() {
		MirrorList<Field> fields = new Mirror().on(modelType).reflectAll().fields();
		return Collections2.transform(fields, fieldToModelField());
	}

	private Function<Field, ModelField> fieldToModelField() {
		return new Function<Field, ModelField>() {
			@Override
			public ModelField apply(Field input) {
				return new ModelField(input, typeMappers);
			}
		};
	}

	public String getModelName() {
		String simpleName = this.modelType.getSimpleName();
		Character firstLetter = simpleName.charAt(0);
		return toLowerCase(firstLetter) + simpleName.substring(1);
	}
}
