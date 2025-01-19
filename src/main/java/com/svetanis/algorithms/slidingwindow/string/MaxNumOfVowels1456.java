package com.svetanis.algorithms.slidingwindow.string;

// 1456. Maximum Number of Vowels in a SubStr of Given Length

public final class MaxNumOfVowels1456 {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int sw(String s, int k) {
    int n = s.length();
    int count = init(s, k);
    int max = count;
    for (int right = k; right < n; right++) {
      if (isVowel(s.charAt(right))) {
        count++;
      }
      if (isVowel(s.charAt(right - k))) {
        count--;
      }
      max = Math.max(max, count);

    }
    return max;
  }

  private static boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
  }

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