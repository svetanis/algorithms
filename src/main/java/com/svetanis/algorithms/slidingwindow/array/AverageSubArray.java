package com.svetanis.algorithms.slidingwindow.array;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import java.util.List;

import com.google.common.collect.ImmutableList;

// given an array, find the average of each 
// subarray of 'K' contiguous elements in it

public final class AverageSubArray {

  public static ImmutableList<Double> average(int[] a, int k) {
    // Time Complexity: O(n)
    int left = 0;
	double sum = 0.0;
    List<Double> list = newArrayList();
    for(int right = 0; right < a.length; right++) {
    	sum += a[right];
    	if(right >= k - 1) {
    		// calculate and store average
    		list.add(sum/k);
    		// subtract element going out
    		sum -= a[left];
    		// slide the window
    		left++;
    	}
    }
    return newList(list);
  }

  public static void main(String[] args) {
    int[] a = { 1, 3, 2, 6, -1, 4, 1, 8, 2 };
    System.out.println(average(a, 5));
  }
}
