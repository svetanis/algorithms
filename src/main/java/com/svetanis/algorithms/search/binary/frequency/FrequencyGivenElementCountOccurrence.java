package com.svetanis.algorithms.search.binary.frequency;

import static com.svetanis.algorithms.search.binary.frequency.FirstOccurrenceBinaryRecursive.firstOccurrence;
import static com.svetanis.algorithms.search.binary.frequency.LastOccurrenceBinaryRecursive.lastOccurrence;
import static com.svetanis.java.base.utils.Arrays.toArray;
import static org.apache.commons.lang3.ArrayUtils.toPrimitive;

import java.util.List;

public final class FrequencyGivenElementCountOccurrence {

  public static int count(List<Integer> list, int x) {
    return count(toPrimitive(toArray(list, Integer.class)), x);
  }

  public static int count(int[] a, int x) {
    // Time Complexity: O(log n)

    int first = firstOccurrence(a, x);
    int last = lastOccurrence(a, x);
    if (first == -1 && last == -1) {
      return 0;
    } else if (first == last) {
      return 1;
    } else {
      return last - first + 1;
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 3, 3, 5, 5, 5, 6, 7, 7, 9, 9 };
    System.out.println(count(a, 5));

    int[] a1 = { 2, 2, 3, 5, 6 };
    System.out.println(count(a1, 2));
  }
}