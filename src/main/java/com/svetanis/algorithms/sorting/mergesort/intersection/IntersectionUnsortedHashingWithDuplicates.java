package com.svetanis.algorithms.sorting.mergesort.intersection;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Intersection of two string arrays, repeats counted (no LeetCode number; LeetCode 350,
// Intersection of Two Arrays II, is the same rule for integers)
//
// Input: two string arrays a1 and a2, unsorted, repeats allowed. Returns the strings that are in
// both, each as many times as the array with fewer copies of it holds; the strings come in the
// order of their first appearance in a1. For example ["dog", "dog"] and
// ["dog", "dog", "dog", "dog", "dog"] give ["dog", "dog"].
//
// The one idea: count how many times each string appears in each array (a map from string to
// count). A string in both maps is in the answer min(count in a1, count in a2) times.
//
// Time: O(n + m) -- each array is counted once; the answer is built from the first map's keys.
// Space: O(n + m) -- the two maps.
//
// Siblings: IntersectionTwoArraysII (350) -- the same rule on integers, three ways.

public final class IntersectionUnsortedHashingWithDuplicates {

  public static List<String> intersection(String[] a1, String[] a2) {
    List<String> list = new ArrayList<>();
    Map<String, Integer> map1 = freqMap(a1); // string -> copies in a1
    Map<String, Integer> map2 = freqMap(a2); // string -> copies in a2
    for (String str : map1.keySet()) {
      if (map2.get(str) != null) { // in both arrays
        int freq = min(map1.get(str), map2.get(str)); // as many copies as the poorer array
        for (int k = 0; k < freq; k++) { // TAKE freq copies
          list.add(str);
        }
      }
    }
    return list;
  }

  // string -> how many times it appears; the keys come out in first-appearance order
  private static Map<String, Integer> freqMap(String[] a) {
    Map<String, Integer> map = new LinkedHashMap<>();
    for (String str : a) {
      map.put(str, map.getOrDefault(str, 0) + 1);
    }
    return map;
  }

  public static void main(String[] args) {
    String[] a1 = { "dog", "dog" };
    String[] a2 = { "dog", "dog", "dog", "dog", "dog" };
    System.out.println(intersection(a1, a2)); // [dog, dog]

    String[] a3 = { "cat", "dog", "dog", "emu" };
    String[] a4 = { "dog", "emu", "emu", "cat", "dog" };
    System.out.println(intersection(a3, a4)); // [cat, dog, dog, emu]
  }
}