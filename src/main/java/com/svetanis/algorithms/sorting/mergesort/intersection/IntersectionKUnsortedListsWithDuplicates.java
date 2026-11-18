package com.svetanis.algorithms.sorting.mergesort.intersection;

import static com.svetanis.algorithms.search.binary.frequency.FrequencyGivenElementCountOccurrence.count;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Intersection of k lists, repeats counted (GeeksforGeeks; no LeetCode number)
//
// Input: k non-empty lists of integers, unsorted, repeats allowed. Returns the values that are in
// every list, each repeated as many times as it appears in the list that has the fewest copies
// of it, in increasing order. Every list is sorted in place as a side effect.
//
// The one idea: a value in the intersection is in the smallest list, so only that list's values
// need to be tried. Count each distinct value of the smallest list (its frequency map). For each
// one, ask every other list how many copies it holds, by binary search on the sorted list. If
// some list has none, the value is out; otherwise the answer holds as many copies as the
// smallest count seen, starting from the smallest list's own count.
//
// Time: O(S log S + d * S log S) -- S is the total size of all lists and d the number of distinct
//   values in the smallest list; count sorts a copy of its list on every call.
// Space: O(S) -- the copies made by count, and the frequency map.

public final class IntersectionKUnsortedListsWithDuplicates {

  public static List<Integer> intersection(List<List<Integer>> lists) {
    for (List<Integer> list : lists) { // SORT every list: the counting below binary-searches
      sort(list);
    }
    int index = getSmallestListIndex(lists); // the smallest list holds every candidate
    Map<Integer, Integer> map = freqMap(lists.get(index)); // value -> copies in the smallest list
    return getIntersection(lists, map, index);
  }

  private static List<Integer> getIntersection(List<List<Integer>> lists, Map<Integer, Integer> map,
      int index) {
    List<Integer> list = new ArrayList<>();
    for (int key : map.keySet()) {
      int value = map.get(key); // COPIES allowed so far: the smallest list's count, then the least seen
      boolean found = true;

      for (int j = 0; j < lists.size(); ++j) {
        if (j != index) { // the smallest list is already counted
          int freq = count(lists.get(j), key); // COUNT the copies in list j
          if (freq == 0) { // OUT: this list lacks the value
            found = false;
            break;
          }
          if (freq < value) { // the answer holds no more copies than the poorest list
            value = freq;
          }
        }
      }
      if (found) { // TAKE: the value is in every list, as many copies as the poorest one
        for (int k = 0; k < value; k++) {
          list.add(key);
        }
      }
    }
    return list;
  }

  // value -> how many times it appears; the keys come out in the list's order
  private static Map<Integer, Integer> freqMap(List<Integer> list) {
    Map<Integer, Integer> map = new LinkedHashMap<>();
    for (int number : list) {
      map.put(number, map.getOrDefault(number, 0) + 1);
    }
    return map;
  }

  public static int getSmallestListIndex(List<List<Integer>> lists) {
    int index = 0;
    int min = lists.get(0).size();
    for (int i = 1; i < lists.size(); ++i) {
      int size = lists.get(i).size();
      if (min > size) { // keep the first of equally small lists
        min = size;
        index = i;
      }
    }
    return index;
  }

  public static void main(String[] args) {
    List<List<Integer>> lists = new ArrayList<>();
    lists.add(asList(1, 2, 2, 3, 4));
    lists.add(asList(2, 2, 3, 5, 6));
    lists.add(asList(1, 3, 2, 2, 6));
    System.out.println(intersection(lists)); // [2, 2, 3]

    List<List<Integer>> lists2 = new ArrayList<>();
    lists2.add(asList(1, 2, 1, 3));
    lists2.add(asList(2, 2, 3));
    lists2.add(asList(1, 4, 1, 2));
    System.out.println(intersection(lists2)); // [2]
  }
}
