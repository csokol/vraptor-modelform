package br.com.caelum.vraptor.modelform;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;

import br.com.caelum.vraptor.freemarker.FreemarkerConfiguration;

public class InputResolverTest {

	private FreemarkerConfiguration configuration = new FreemarkerConfiguration();
	private InputResolver inputResolver = new InputResolver(SomeModel.class,
			new TemplateGenerator(configuration));
	private Map<String, Object> params = new HashMap<String, Object>();

	@Test
	public void shouldRenderStringInput() throws NoSuchFieldException,
			SecurityException {
		String expectedInput = "<input type=\"text\" name=\"name\" id=\"name\"/>";
		ModelField nameModelField = new ModelField(
				SomeModel.class.getDeclaredField("name"));
		params.put("name", "name");
		params.put("label", "LabelName");
		
		
		String generatedHtml = inputResolver.resolveInputFor(
				nameModelField.getJavaType(), params);
		
		assertTrue(generatedHtml.contains(expectedInput));
		assertTrue(generatedHtml.contains("LabelName"));
	}

	@Test
	public void shouldRenderGlobalInput() throws NoSuchFieldException,
			SecurityException {
		String expectedInput = "<input type=\"date\" name=\"date\" id=\"date\"/>";
		ModelField nameModelField = new ModelField(
				OtherModel.class.getDeclaredField("date"));
		InputResolver inputResolver = new InputResolver(OtherModel.class,
				new TemplateGenerator(configuration));
		
		params.put("name", "date");
		params.put("label", "LabelDate");
		
		String generatedHtml = inputResolver.resolveInputFor(
				nameModelField.getJavaType(), params);
		
		assertTrue(generatedHtml.contains(expectedInput));
		assertTrue(generatedHtml.contains("LabelDate"));
	}

	@Test
	public void shouldRenderModelSpecificInput() throws NoSuchFieldException,
			SecurityException {
		String expectedInput = "<input type=\"date\" name=\"date\" id=\"date\" class=\"specific\"/>";
		ModelField dateModelField = new ModelField(
				SomeModel.class.getDeclaredField("date"));
		params.put("name", "date");
		params.put("label", "Date");
		
		assertEquals(expectedInput, inputResolver.resolveInputFor(
				dateModelField.getJavaType(), params));
	}
}
