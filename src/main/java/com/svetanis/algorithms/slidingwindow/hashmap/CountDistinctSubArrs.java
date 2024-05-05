package com.svetanis.algorithms.slidingwindow.hashmap;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Map;

// given array of n elements
// count total number of subarrays with total distinct elements 
// same as that of total distinct elements of original array
public final class CountDistinctSubArrs {

  public static int count(List<Integer> list) {
    // Time complexity: O(n)
    int n = list.size();
    int len = 0;
    int right = 0;
    int count = 0;
    // number of distinct elements
    int k = newHashSet(list).size();
    Map<Integer, Integer> map = newHashMap();
    for (int left = 0; left < n; left++) {
      while (right < n && len < k) {
    	int freq = map.getOrDefault(list.get(right), 0);
    	map.put(list.get(right), freq + 1);
        if (map.get(list.get(right)) == 1) {
          len++;
        }
        right++;
      }

      if (len == k) {
        count += (n - right + 1);
      }
      
      int freq = map.get(list.get(left));
      map.put(list.get(left), freq - 1);

      if (map.get(list.get(left)) == 0) {
        len--;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    List<Integer> a = asList(2, 1, 3, 2, 3);
    System.out.println(count(a));

    List<Integer> a1 = asList(2, 4, 5, 2, 1);
    System.out.println(count(a1));

    List<Integer> a2 = asList(2, 4, 4, 2, 4);
    System.out.println(count(a2));
  }
}
