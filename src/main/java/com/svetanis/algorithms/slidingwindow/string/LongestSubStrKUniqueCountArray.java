package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

import com.google.common.base.Optional;

public final class LongestSubStrKUniqueCountArray {

  private static final int MAX = 256;

  public static Optional<String> kUnique(String str, int k) {
    // Time complexity: O(n)

    int n = str.length();
    if (countUnique(str) < k) {
      return absent();
    }

    int left = 0; // current start
    int right = 0; // current end
    int start = 0; // max window start
    int end = 0; // max window end
    int max = 0; // max window size

    int[] count = new int[MAX];
    char[] chars = str.toCharArray();
    count[chars[0]]++;

    for (int i = 1; i < n; i++) {
      count[chars[i]]++;
      right++;

      while (!isValid(count, k)) {
        count[chars[left]]--;
        left++;
      }

      if (right - left + 1 > max) {
        max = right - left + 1;
        start = left;
        end = right;
      }
    }
    return of(str.substring(start, end + 1));
  }

  private static boolean isValid(int[] a, int k) {
    int count = 0;
    for (int i = 0; i < MAX; i++) {
      if (a[i] > 0) {
        count++;
      }
    }
    return k >= count;
  }

  private static int countUnique(String str) {
    int k = 0;
    int[] count = new int[MAX];
    // count num of unique chars
    for (char c : str.toCharArray()) {
      if (count[c] == 0) {
        k++;
      }
      count[c]++;
    }
    return k;
  }

  public static void main(String[] args) {
    String str1 = "aabbcc";
    System.out.println(kUnique(str1, 1));
    System.out.println(kUnique(str1, 2));
    System.out.println(kUnique(str1, 3));

    String str2 = "aaabbb";
    System.out.println(kUnique(str2, 3));

    String str3 = "aabacbebebe";
    System.out.println(kUnique(str3, 3));
  }
}
