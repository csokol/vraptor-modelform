package br.com.caelum.vraptor.modelform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InputResolverTest {

	private InputResolver inputResolver = new InputResolver(SomeModel.class);;

	@Test
	public void shouldRenderStringTemplateBasedInput() throws NoSuchFieldException, SecurityException{
		String expectedInput = "<input type=\"text\" name=\"#name\" id=\"#name\"/>";
		ModelField nameModelField = new ModelField(SomeModel.class.getDeclaredField("name"));
		assertEquals(expectedInput, inputResolver.resolveInputFor(nameModelField.getJavaType()));
	}
	
	@Test
	public void shouldRenderCalendarTemplateBasedInput() throws NoSuchFieldException, SecurityException{
		String expectedInput = "<input type=\"date\" name=\"#name\" id=\"#name\"/>";
		ModelField dateModelField = new ModelField(SomeModel.class.getDeclaredField("date"));
		assertEquals(expectedInput, inputResolver.resolveInputFor(dateModelField.getJavaType()));
	}
}
