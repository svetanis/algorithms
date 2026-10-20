package com.svetanis.algorithms.slidingwindow.hashmap;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// Subarrays holding every distinct value of the array
//
// Input: a list of n integers.
// Return: how many contiguous subarrays contain every different value that appears in
// the whole list -- their number of different values equals the list's. 0 for an
// empty list.
//
// The one idea: if the subarray from left to some end already holds all k different
// values, so does every longer subarray from left -- adding values never takes one
// away. So for each start left, only the first end that works matters. right is one
// past the window, so the window is left .. right - 1, and len is how many different
// values it holds. right moves forward until len reaches k; then every end from
// right - 1 to n - 1 works, n - right + 1 of them. When left moves on, the value at
// left leaves and the window can only lose values, so the first good end for the next
// start is at or after the current one -- right never moves back.
//
// Time: O(n) -- left and right each move forward at most n times in total, so the
// inner while runs at most n times over the whole run. This needs list.get to be O(1),
// as on an ArrayList or Arrays.asList; on a LinkedList each get is O(n).
// Space: O(k) -- the set and the map hold one entry per different value.
//
// Siblings: CountSubArraysKDistinct is 992, exactly k different values for a k given
// as input, counted as (at most k) - (at most k - 1). CountSubArrAtLeastOneDuplicate
// also counts subarrays by their values, as all subarrays minus those with no repeat.

public final class CountDistinctSubArrs {

  public static int count(List<Integer> list) {
    int n = list.size();
    int len = 0;
    int right = 0;
    int count = 0;
    int k = new HashSet<>(list).size(); // TARGET: different values in the whole list
    Map<Integer, Integer> map = new HashMap<>();
    for (int left = 0; left < n; left++) {
      while (right < n && len < k) { // GROW until the window holds all k values
        int freq = map.getOrDefault(list.get(right), 0);
        map.put(list.get(right), freq + 1); // ADD
        if (map.get(list.get(right)) == 1) { // NEW: a value not in the window before
          len++;
        }
        right++;
      }

      if (len == k) { // COUNT: ends right - 1 .. n - 1 from this left
        count += (n - right + 1);
      }

      int freq = map.get(list.get(left));
      map.put(list.get(left), freq - 1); // OUT: the value at left leaves

      if (map.get(list.get(left)) == 0) { // LOST: its last copy left the window
        len--;
      }
    }
    return count; // RETURN
  }

  public static void main(String[] args) {
    List<Integer> a = asList(2, 1, 3, 2, 3);
    System.out.println(count(a)); // expected 5

    List<Integer> a1 = asList(2, 4, 5, 2, 1);
    System.out.println(count(a1)); // expected 2

    List<Integer> a2 = asList(2, 4, 4, 2, 4);
    System.out.println(count(a2)); // expected 9

    List<Integer> a3 = asList();
    System.out.println(count(a3)); // expected 0
  }
}
