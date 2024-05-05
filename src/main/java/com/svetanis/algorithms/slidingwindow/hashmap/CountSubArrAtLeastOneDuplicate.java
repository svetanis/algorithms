package com.svetanis.algorithms.slidingwindow.hashmap;

import static com.google.common.collect.Maps.newHashMap;
import java.util.Map;

// given array of n elements,
// count subarrays with at least one duplicate

public final class CountSubArrAtLeastOneDuplicate {

  private static int count(int[] a) {
    int n = a.length;
    // total number of subarrays
    int total = n * (n + 1) / 2;
    // number of subarrays with all unique elements
    int unique = countUnique(a);
    // number of subarrays with at least one duplicate
    return total - unique;
  }

  
  private static int countUnique(int[] a) {
    // Time complexity: O(n)
    int n = a.length;
    int count = 0;
    int left = -1;
    Map<Integer, Integer> map = newHashMap();

    for (int i = 0; i < n; i++) {
   	  int freq = map.getOrDefault(a[i], 0);
      map.put(a[i], freq + 1);

      if (map.get(a[i]) >= 2) {
        left++;
        while (a[left] != a[i]) {
          map.put(a[left], map.get(a[left]) - 1);
          left++;
        }
        map.put(a[left], map.get(a[left]) - 1);
      }
      count += i - left;
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3 };
    System.out.println(count(a));

    int[] a1 = { 4, 3, 4, 3 };
    System.out.println(count(a1));

  }
}
