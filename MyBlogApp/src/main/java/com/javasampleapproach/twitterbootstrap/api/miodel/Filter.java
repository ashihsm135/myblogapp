package com.javasampleapproach.twitterbootstrap.api.miodel;

import java.util.List;

public class Filter {

	private String name;
	private List<Object> values;
	private boolean isRegex;
	private boolean isRange;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	public boolean isRegex() {
		return isRegex;
	}
	public void setRegex(boolean isRegex) {
		this.isRegex = isRegex;
	}
	public boolean isRange() {
		return isRange;
	}
	public void setRange(boolean isRange) {
		this.isRange = isRange;
	}
	
	
}