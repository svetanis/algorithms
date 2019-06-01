package com.svetanis.algorithms.sorting.mergesort.intersection;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.binary.frequency.FrequencyGivenElementCountOccurrence.count;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Maps.freqMap;
import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

public final class IntersectionKUnsortedListsWithDuplicates {

  public static ImmutableList<Integer> intersection(List<List<Integer>> lists) {
    // sort all the sets
    for (List<Integer> list : lists) {
      sort(list);
    }
    // find the smallest set
    int index = getSmallestListIndex(lists);
    // add all elements of smallest set to a map
    Map<Integer, Integer> map = freqMap(lists.get(index));
    return getIntersection(lists, map, index);
  }

  private static ImmutableList<Integer> getIntersection(List<List<Integer>> lists, Map<Integer, Integer> map,
      int index) {
    // iterate through the map elements to see
    // if they are present in remaining sets

    boolean found = false;
    List<Integer> list = newArrayList();
    for (int key : map.keySet()) {
      int value = map.get(key);
      found = true;

      // iterate through all sets
      for (int j = 0; j < lists.size(); ++j) {
        // if this set is not the smallest set,
        // then do binary search in it
        if (j != index) {
          // if the element is found in this set, find its frequency
          int freq = count(lists.get(j), key);
          if (freq != 0) {
            // update the min freq if needed
            if (freq < value) {
              map.put(key, freq);
            }
          } else {
            // element not found, no need to proceed
            found = false;
            break;
          }
        }
      }
      // if element was found in all sets,
      // then add it to result freq times
      if (found) {
        for (int k = 0; k < value; k++) {
          list.add(key);
        }
      }
    }
    return newList(list);
  }

  public static int getSmallestListIndex(List<List<Integer>> lists) {
    int index = 0;
    int min = lists.get(0).size();
    for (int i = 1; i < lists.size(); ++i) {
      // update minSize, if needed
      int size = lists.get(i).size();
      if (min > size) {
        min = size;
        index = i;
      }
    }
    return index;
  }

  public static void main(String[] args) {
    List<List<Integer>> lists = newArrayList();
    lists.add(asList(1, 2, 2, 3, 4));
    lists.add(asList(2, 2, 3, 5, 6));
    lists.add(asList(1, 3, 2, 2, 6));
    print(intersection(lists)); // 2 2 3
  }
}
