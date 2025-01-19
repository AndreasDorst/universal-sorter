package com.universalsorter.service;

import com.universalsorter.model.IntegerComparable;
import com.universalsorter.service.SortedArraySearch;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SortedArraySearchTest {
    /*
	public static void main(String [] args) {
    	sortTest();
    }
    */
    
    @Test
    void sortTest() {
    	Integer [] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
    	IntegerComparable [] comparables = new IntegerComparable [sortedArray.length];
    	IntegerComparable target;
    	int index;
    	String response;
    	for (int i = 0; i < sortedArray.length; i++) {
    		comparables[i] = new IntegerComparable(sortedArray[i]);
    	}
    	
    	target = new IntegerComparable(5);
    	index = SortedArraySearch.getElementIndex(comparables, target);
    	response = SortedArraySearch.getElement(comparables, target);
    	assertEquals(2, index);
    	assertEquals("Данный элемент находится в массиве по индексу: 2", response);
    	
    	target = new IntegerComparable(2);
    	index = SortedArraySearch.getElementIndex(comparables, target);
    	response = SortedArraySearch.getElement(comparables, target);
    	assertEquals(-1, index);
    	assertEquals("Данный элемент в массиве отсутствует.", response);
    	
    	Exception catched = null;
    	try {
        	target = new IntegerComparable(null);
        	index = SortedArraySearch.getElementIndex(comparables, target);
    	} catch (Exception e) {
    		catched = e;
    	}
    	assertNotNull(catched);
		assertEquals("Nulls are not allowed for search of integers", catched.getMessage());
    	
    }
}
