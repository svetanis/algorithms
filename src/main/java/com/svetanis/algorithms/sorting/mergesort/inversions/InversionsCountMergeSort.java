package com.svetanis.algorithms.sorting.mergesort.inversions;

public final class InversionsCountMergeSort {

  public static int count(int[] a, int left, int right) {
    // Time Complexity: O(n log n)
    
    int count = 0;
    if (right > left) {
      int mid = left + (right - left) / 2;

      // inversion count will be sum of inversions in left-part,
      // right-part and number of inversions in merging
      count = count(a, left, mid);
      count += count(a, mid + 1, right);

      // merge the two sorted halves
      count += merge(a, left, mid + 1, right);
    }
    return count;
  }

  private static int merge(int[] a, int left, int mid, int right) {
    int[] temp = new int[mid + right];
    int count = 0;
    int i = left; // i is index for left subarray
    int j = mid;  // j is index for right subarray
    int k = left; // k is index for merged array

    while (i <= mid - 1 && j <= right) {
      if (a[i] <= a[j]) {
        temp[k++] = a[i++];
      } else {
        temp[k++] = a[j++];
        // count inversions
        count = count + (mid - i);
      }
    }

    // Copy any remaining entries
    // in the left and right subarrays.
    while (i <= mid - 1) {
      temp[k++] = a[i++];
    }
    while (j <= right) {
      temp[k++] = a[j++];
    }
    // Copy from temp back to the data array.
    for (i = left; i <= right; i++) {
      a[i] = temp[i];
    }

    return count;
  }

  public static void main(String[] args) {

    int[] a = { 2, 4, 1, 3, 5 };
    System.out.println(count(a, 0, a.length - 1));
  }
}

