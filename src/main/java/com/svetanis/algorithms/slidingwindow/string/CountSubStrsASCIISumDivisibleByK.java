package com.svetanis.algorithms.slidingwindow.string;

// Count Substrings of Length K Whose Character Codes Sum to a Multiple of K
// (no LeetCode number; source unknown)
//
// Input: a string and a number k, with 1 <= k <= the string's length.
// Return: how many substrings of exactly k characters have a sum of character
// codes (ASCII values) that divides evenly by k.
// Outside that range the method throws: k = 0 divides by zero, and a k longer than
// the string reads past its end while building the first window. No rule for
// those cases is known, so they are left as they are.
//
// The one idea: every window has the same length k, so moving it one step to the
// right changes exactly two characters -- one comes in on the right, one goes out
// on the left. Keep the sum and adjust it by those two instead of adding up k
// characters again.
//
// Siblings: none -- no other file in the repo solves this problem.
//   CountSubStrsContainingSameChar and MaxNumOfVowels1456 use the same
//   fixed-length window with a different test.
//
// Time: O(n) -- the first window costs k steps, and every later window two.
// Space: O(1) -- one running sum.

public final class CountSubStrsASCIISumDivisibleByK {

  public static int count(String str, int k) {
    int sum = 0;
    int count = 0;

    for (int i = 0; i < k; i++) {
      sum += str.charAt(i); // BUILD: the first window, characters 0..k-1
    }

    if (sum % k == 0) {
      count++; // RECORD: the first window
    }

    for (int i = k; i < str.length(); i++) {
      sum -= str.charAt(i - k); // OUT: the character k places back leaves
      sum += str.charAt(i); // IN: character i enters

      if (sum % k == 0) {
        count++; // RECORD: the window ending at i
      }
    }
    return count;
  }

  public static void main(String[] args) {
    String s1 = "bcgabc";
    System.out.println(count(s1, 3));

    String s2 = "adkf";
    System.out.println(count(s2, 3));
  }
}
