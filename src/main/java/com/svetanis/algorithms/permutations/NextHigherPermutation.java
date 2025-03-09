package com.svetanis.algorithms.permutations;

import static com.svetanis.algorithms.string.reverse.ReverseStringSwap.reverse;
import static com.svetanis.java.base.utils.Swap.swap;
import static java.lang.String.valueOf;

// Let us consider the string “ABCDEF”. 
// Let previously printed permutation be “DCFEBA”. 
// The next permutation in sorted order should be “DEABCF”. 

// The ‘first character’ will be ‘C’. 
// The ‘second character’ will be ‘E’. 
// After swapping these two, we get “DEFCBA”. 
// The final step is to sort the substring after 
// the character original index of ‘first character’. 
// Finally, we get “DEABCF”.

public final class NextHigherPermutation {

  public static String nextHigher(String str) {
    // Time complexity: O(n * n!)

    char[] chars = str.toCharArray();

    if (nextHigher(chars)) {
      return valueOf(chars);
    }
    return "this is the last one";
  }

  public static boolean nextHigher(char[] chars) {
    int n = chars.length;

    // 1. first char: rightmost char which
    // is smaller than its next char
    int first = smaller(chars);

    // last permutation
    if (first == -1) {
      return false;
    }

    // 2. find ceil of first char on the right of first char
    int second = ceiling(chars, chars[first], first + 1, n - 1);

    // 3. swap first and second characters
    swap(chars, first, second);

    // 4. sort the string on right of 'first char'
    reverse(chars, first + 1, n - 1);

    return true;
  }

  public static int smaller(char[] chars) {
    int n = chars.length;
    for (int i = n - 2; i >= 0; --i) {
      if (chars[i] < chars[i + 1]) {
        return i;
      }
    }
    return -1;
  }

  public static int ceiling(char[] chars, char first, int low, int high) {
    // initialize index of ceiling element
    int ceilIndex = low;

    // now iterate through rest of the elements and
    // find the smallest char greater than first
    for (int i = low + 1; i <= high; ++i) {
      if (chars[i] > first && chars[i] < chars[ceilIndex]) {
        ceilIndex = i;
      }
    }
    return ceilIndex;
  }

  public static void main(String[] args) {
    String str = "DCFEBA";
    System.out.println(nextHigher(str));
  }
}
