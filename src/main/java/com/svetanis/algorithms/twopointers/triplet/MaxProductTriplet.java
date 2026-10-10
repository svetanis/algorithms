package com.svetanis.algorithms.twopointers.triplet;

// 628. Maximum Product of Three Numbers
//
// Input: an array of integers.
// Return: the largest product of three numbers at three different positions; -1 when
// there are fewer than three numbers.
//
// The one idea: the best three are either the three LARGEST numbers, or the two SMALLEST
// with the largest -- two negatives make a positive. No other choice can win, so one pass
// keeping the top three and the bottom two is enough, with no sort.
//
// Siblings:
//   twopointers.triplet.MaxProductTripletSubmit -- the same pass with >= / <= updates, plus a
//                                                 sorting version
//   twopointers.quadruple.MaxProductQuadruple   -- four numbers: three candidates, not two
//
// Time: O(n) -- one pass.
// Space: O(1).

public class MaxProductTriplet {

  public static int maxProduct(int[] a) {
    int n = a.length;
    if (n < 3) {
      return -1;                                  // RETURN -1: no three numbers to multiply
    }

    int firstMax = Integer.MIN_VALUE;             // the three largest, largest first
    int secondMax = Integer.MIN_VALUE;
    int thirdMax = Integer.MIN_VALUE;

    int firstMin = Integer.MAX_VALUE;             // the two smallest, smallest first
    int secondMin = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      if (a[i] > firstMax) {                      // RECORD a new largest: the others shift down
        thirdMax = secondMax;
        secondMax = firstMax;
        firstMax = a[i];
      } else if (a[i] > secondMax) {
        thirdMax = secondMax;
        secondMax = a[i];
      } else if (a[i] > thirdMax) {
        thirdMax = a[i];
      }

      if (a[i] < firstMin) {                      // RECORD a new smallest: the other shifts up
        secondMin = firstMin;
        firstMin = a[i];
      } else if (a[i] < secondMin) {
        secondMin = a[i];
      }
    }

    int max1 = firstMax * secondMax * thirdMax;   // COMPARE the three largest
    int max2 = firstMin * secondMin * firstMax;   // with the two smallest and the largest
    return Math.max(max1, max2);
  }

  public static void main(String[] args) {
    int[] a1 = { 10, 3, 5, 6, 20 };
    System.out.println(maxProduct(a1)); // 1200

    int[] a2 = { -10, -3, -5, -6, -20 };
    System.out.println(maxProduct(a2)); // -90

    int[] a3 = { 1, -4, 3, -6, 7, 0 };
    System.out.println(maxProduct(a3)); // 168

    int[] a4 = { 1, 10, -5, -1, -100 };
    System.out.println(maxProduct(a4)); // 5000

    int[] a5 = { 1, 2, 3 };
    System.out.println(maxProduct(a5)); // 6

    int[] a6 = { -1, -2, -3 };
    System.out.println(maxProduct(a6)); // -6
  }
}