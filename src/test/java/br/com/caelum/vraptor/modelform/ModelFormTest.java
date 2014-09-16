package br.com.caelum.vraptor.modelform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModelFormTest {

	@Test
	public void shouldRenderStringTemplateBasedInput() throws NoSuchFieldException, SecurityException{
		ModelForm<SomeModel> form = new ModelForm<SomeModel>(SomeModel.class, null);
		String expectedInput = "<input type=\"text\" name=\"someModel.name\" id=\"someModel.name\"/>";
		ModelField nameModelField = new ModelField(SomeModel.class.getDeclaredField("name"), null);
		assertEquals(expectedInput, form.inputFor(nameModelField));
	}
	
	@Test
	public void shouldRenderCalendarTemplateBasedInput() throws NoSuchFieldException, SecurityException{
		ModelForm<SomeModel> form = new ModelForm<SomeModel>(SomeModel.class, null);
		String expectedInput = "<input type=\"date\" name=\"someModel.date\" id=\"someModel.date\"/>";
		ModelField nameModelField = new ModelField(SomeModel.class.getDeclaredField("date"), null);
		assertEquals(expectedInput, form.inputFor(nameModelField));
	}
}
