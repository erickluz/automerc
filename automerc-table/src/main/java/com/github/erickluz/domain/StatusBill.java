
package com.github.erickluz.domain;

public enum StatusBill {

	OPEN(0, "Open"),
	CLOSED(1, "Closed");

	public int code;
	public String description;

	StatusBill(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
