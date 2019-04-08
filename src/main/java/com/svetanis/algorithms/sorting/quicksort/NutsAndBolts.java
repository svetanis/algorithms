package com.svetanis.algorithms.sorting.quicksort;

import static com.svetanis.java.base.utils.Print.print;
import static com.svetanis.java.base.utils.Random.randomIndex;
import static com.svetanis.java.base.utils.Swap.swap;
import static org.apache.commons.lang3.ArrayUtils.toObject;

public final class NutsAndBolts {

  public static void match(char[] nuts, char[] bolts) {
    // Time Complexity: O(n log n)
    
    match(nuts, bolts, 0, nuts.length - 1);
  }

  public static void match(char[] nuts, char[] bolts, int left, int right) {
    if (left < right) {
      int index = pivot(left, right);
      int pivot = partition(nuts, left, right, bolts[index]);
      // now using the partition of nuts choose that for bolts
      partition(bolts, left, right, nuts[pivot]);
      match(nuts, bolts, left, pivot - 1);
      match(nuts, bolts, pivot + 1, right);
    }
  }

  private static int pivot(int left, int right) {
    return randomIndex(left, right);
  }

  private static int partition(char[] a, int left, int right, char pivot) {
    int i = left;
    for (int j = left; j < right; ++j) {
      if (a[j] < pivot) {
        swap(a, i, j);
        i++;
      } else if (a[j] == pivot) {
        swap(a, j, right);
        j--;
      }
    }
    swap(a, i, right);
    // return the partition index of an array
    // based on the pivot element of other array
    return i;
  }

  public static void main(String[] args) {
    char[] nuts = { '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')' };
    char[] bolts = { '$', '%', '*', '(', ')', '&', '^', '~', '!', '@', '#' };
    match(nuts, bolts);
    print(toObject(nuts));
    print(toObject(bolts));
  }
}