package br.com.caelum.vraptor.modelform;

import java.util.Calendar;

import javax.enterprise.inject.Vetoed;
import javax.validation.constraints.NotNull;

@Vetoed
public class SomeModel {

	private String name;
	private Calendar date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

}
