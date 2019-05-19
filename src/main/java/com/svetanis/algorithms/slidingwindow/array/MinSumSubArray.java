package com.svetanis.algorithms.slidingwindow.array;

public final class MinSumSubArray {

  public static int minSum(int[] a, int x) {
    // Time complexity: O(n)

    int n = a.length;
    int min = n + 1;
    int sum = 0;
    int left = 0;
    int right = 0;

    while (right < n) {
      while (sum <= x && right < n) {
        sum += a[right++];
      }

      // if currentSum becomes > x
      while (sum > x && left < n) {
        // update minLength if needed
        if (right - left < min) {
          min = right - left;
        }
        // remove starting elements
        sum -= a[left++];
      }
    }
    return min;
  }

  public static void main(String[] args) {
    int[] ar1 = { 1, 4, 45, 6, 0, 19 };
    System.out.println(minSum(ar1, 51));

    int[] ar2 = { 1, 10, 5, 2, 7 };
    System.out.println(minSum(ar2, 9));

    int[] ar3 = { 1, 11, 100, 1, 0, 200, 3, 2, 1, 250 };
    System.out.println(minSum(ar3, 280));
  }
}
