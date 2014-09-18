package br.com.caelum.vraptor.modelform;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.com.caelum.vraptor.freemarker.FreemarkerConfiguration;
import freemarker.template.Configuration;
import static org.junit.Assert.assertEquals;

public class InputResolverTest {

	private Configuration configuration = new FreemarkerConfiguration().getConfiguration();
	private InputResolver inputResolver = new InputResolver(SomeModel.class,configuration);
	private Map<String,Object> params = new HashMap<String, Object>();

	@Test
	public void shouldRenderStringInput() throws NoSuchFieldException, SecurityException{
		String expectedInput = "<input type=\"text\" name=\"name\" id=\"name\"/>";
		ModelField nameModelField = new ModelField(SomeModel.class.getDeclaredField("name"));
		params.put("name", "name");
		assertEquals(expectedInput, inputResolver.resolveInputFor(nameModelField.getJavaType(),params));
	}
	
	@Test
	public void shouldRenderGlobalInput() throws NoSuchFieldException, SecurityException{
		String expectedInput = "<input type=\"date\" name=\"name\" id=\"name\"/>";
		ModelField nameModelField = new ModelField(OtherModel.class.getDeclaredField("date"));
		InputResolver inputResolver = new InputResolver(OtherModel.class,configuration);
		params.put("name", "name");
		assertEquals(expectedInput, inputResolver.resolveInputFor(nameModelField.getJavaType(),params));
	}
	
	@Test
	public void shouldRenderModelSpecificInput() throws NoSuchFieldException, SecurityException{
		String expectedInput = "<input type=\"date\" name=\"date\" id=\"date\" class=\"specific\"/>";
		ModelField dateModelField = new ModelField(SomeModel.class.getDeclaredField("date"));
		params.put("name", "date");
		assertEquals(expectedInput, inputResolver.resolveInputFor(dateModelField.getJavaType(),params));
	}
}
