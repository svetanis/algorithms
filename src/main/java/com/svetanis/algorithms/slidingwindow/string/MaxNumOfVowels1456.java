package com.svetanis.algorithms.slidingwindow.string;

// 1456. Maximum Number of Vowels in a SubStr of Given Length
//
// Input: a string of lowercase letters and a number k, with 1 <= k <= its length.
// Return: the most vowels (a, e, i, o, u) found in any substring of exactly k
// letters. A k longer than the string reads past its end and throws; uppercase
// vowels are not counted.
//
// The one idea: every window has the same length k, so moving it one step to the
// right changes exactly two letters -- one comes in on the right, one goes out on
// the left. Each letter is worth 1 if it is a vowel and 0 if not, so the window's
// count changes by at most one in each direction; no recount is needed.
//
// Siblings: none -- no other file in the repo solves 1456.
//   CountSubStrsASCIISumDivisibleByK uses the same fixed-length window over a sum.
//
// Time: O(n) -- the first window costs k steps, and every later window two.
// Space: O(1) -- one running count.

public final class MaxNumOfVowels1456 {

  public static int sw(String s, int k) {
    int n = s.length();
    int count = init(s, k); // BUILD: vowels in the first window
    int max = count;
    for (int right = k; right < n; right++) {
      if (isVowel(s.charAt(right))) {
        count++; // IN: letter right enters
      }
      if (isVowel(s.charAt(right - k))) {
        count--; // OUT: the letter k places back leaves
      }
      max = Math.max(max, count); // RECORD

    }
    return max;
  }

  private static boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
  }

  // the first window -- vowels in [0, k)
  private static int init(String s, int k) {
    int count = 0;
    for (int i = 0; i < k; i++) {
      if (isVowel(s.charAt(i))) {
        count++;
      }
    }
    return count;
  }

  public static void main(String args[]) {
    System.out.println(sw("abciiidef", 3)); // 3
    System.out.println(sw("aeiou", 2)); // 2
    System.out.println(sw("leetcode", 3)); // 2
  }
}