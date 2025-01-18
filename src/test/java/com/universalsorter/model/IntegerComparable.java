package com.universalsorter.model;

public class IntegerComparable implements Comparable {
	public Integer integer;
	public IntegerComparable (Integer i) {
		if (i==null) {
			throw new NullPointerException("Nulls are not allowed for search of integers");
		}
		this.integer = i;
	}

	@Override
	public int compareTo(Object o) {
		int result;
		
		if (!(o instanceof IntegerComparable)) {
			throw new ClassCastException("Object should be of IntegerComparable");
		}		
		
		if (this == null || o == null) {
			throw new NullPointerException();
		}
		
		IntegerComparable compared = (IntegerComparable) o;
		
		if (this.integer.equals(compared.integer)) {
			result = 0;
		} else if (this.integer > compared.integer) {
			result = 1;
		} else {
			result = -1;
		}
		
		return result;
	}
	
}