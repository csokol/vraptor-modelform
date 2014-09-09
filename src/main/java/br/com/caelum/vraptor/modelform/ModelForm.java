package br.com.caelum.vraptor.modelform;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import javax.enterprise.inject.Vetoed;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

@SuppressWarnings("ALL")
@Vetoed
public class ModelForm<T> {

	private final Class<T> modelType;

	public ModelForm(Class<T> modelType) {
		this.modelType = modelType;
	}

	public Collection<String> getFields() {
		MirrorList<Field> fields = new Mirror().on(modelType).reflectAll().fields();
		return Collections2.transform(fields, new Function<Field, String>() {
			@Override
			public String apply(Field input) {
				return input.getName();
			}
		});
	}

	public String getModelName() {
		String simpleName = this.modelType.getSimpleName();
		Character firstLetter = simpleName.charAt(0);
		return toLowerCase(firstLetter) + simpleName.substring(1);
	}
}
