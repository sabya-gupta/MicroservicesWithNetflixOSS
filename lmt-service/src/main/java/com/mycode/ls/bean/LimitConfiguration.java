package com.mycode.ls.bean;

public class LimitConfiguration {

	int min;
	int max;
	public LimitConfiguration(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}
	public LimitConfiguration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	
}
