package com.huaxia.rms.query.filter;

public class StringFilter extends BaseFilter {

	public StringFilter(String condition) {
		this.condition = condition;
	}

	public String getString() {
		return condition;
	}

	private final String condition;
}
