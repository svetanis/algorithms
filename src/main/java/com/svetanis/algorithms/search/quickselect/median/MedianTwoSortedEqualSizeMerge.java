package com.svetanis.algorithms.search.quickselect.median;

// since there are 2n elements, 

// median will be average of elements at index
// n - 1 and n in the array obtained 
// after merging array1 and array2

public final class MedianTwoSortedEqualSizeMerge {

  public static int median(int[] a1, int[] a2) {
    // Time complexity: O(n)

    int n = a1.length;

    int i = 0; // current index of a1
    int j = 0; // current index of a2
    int prev = -1;
    int next = -1;

    for (int count = 0; count <= n; ++count) {
      // all elements of a1 are smaller
      // than first element of a2
      if (i == n) {
        prev = next;
        next = a2[0];
        break;
      }
      // all elements of a2 are smaller
      // than first element of a1
      if (j == n) {
        prev = next;
        next = a1[0];
        break;
      }

      if (a1[i] < a2[j]) {
        prev = next; // store the prev median
        next = a1[i];
        i++;
      } else {
        prev = next; // store the prev median
        next = a2[j];
        j++;
      }
    }
    return new Double((prev + next)/2).intValue();
  }

  public static void main(String[] args) {
    int a1[] = { 1, 12, 15, 26, 38 };
    int a2[] = { 2, 13, 17, 30, 45 };
    System.out.println(median(a1, a2));
  }
}
