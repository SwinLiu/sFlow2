package com.lyplay.sflow.data.enums;

public enum EmployeeStatus {
	
	ACTIVE(1), INACTIVE(0);
	
	private int value;

	private EmployeeStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
