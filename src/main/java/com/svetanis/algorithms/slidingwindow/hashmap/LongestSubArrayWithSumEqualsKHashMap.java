package com.svetanis.algorithms.slidingwindow.hashmap;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import com.svetanis.java.base.Pair;


public final class LongestSubArrayWithSumEqualsKHashMap {

  public static Pair<Integer, Integer> maxSubArray(int[] a, int k) {
    // Time complexity: O(n)
    // Space complexity: O(n)

    int sum = 0;
    int max = 0;
    int left = -1;
    int right = -1;
    
    Map<Integer, Integer> map = newHashMap();
    map.put(0,  -1);
    
    for (int i = 0; i < a.length; i++) {
      sum += a[i];
      if (!map.containsKey(sum)) {
        map.put(sum, i);
      }
      
      int key = sum - k;
      if (map.containsKey(key)) {
        int index = map.get(key);
        if (max < i - index) {
          max = i - index;
          left = index + 1;
          right = i;
        }
      }
    }
    return Pair.build(left, right);
  }

  public static void main(String[] args) {
    int[] a = { 1, -1, 5, -2, 3};
    Pair<Integer, Integer> pair = maxSubArray(a, 3);
    System.out.println(pair);
  }
}