package com.svetanis.algorithms.slidingwindow.string;

import static com.svetanis.algorithms.slidingwindow.string.LongestSubStrKUniqueHashMap.countUnique;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Optional;

// Longest Substring with Exactly K Unique Characters
// (no LeetCode number; source unknown -- LeetCode 340 is the "at most k" version)
//
// Input: a string of characters with codes below 256, and a number k >= 1.
// Return: the longest substring holding exactly k different characters, or an
// empty Optional when the whole string holds fewer than k different characters.
// When the longest length is 1 it returns a later single character than
// LongestSubStrKUniqueHashMap does ("ab", k = 1 gives "b" here, "a" there),
// because the first character's window is never measured on its own; both are
// correct answers.
// k = 0 is outside the problem: it returns the first character instead of an
// empty substring, and throws on an empty string. No rule for it is known, so it
// is left as it is.
//
// The one idea: once the whole string is known to hold at least k different
// characters, the longest window with AT MOST k has EXACTLY k. If it had fewer,
// the character just beside it could be added without passing k, giving a longer
// window -- and if it already covers the whole string, it holds them all. So the
// method checks the count once up front and then runs the plain at-most-k window.
//
// Siblings:
//   LongestSubStrKUniqueHashMap -- the same answer with a map; it tests exactly k
//     at every step instead of relying on the fact above.
//   LongestSubStrLenAtMostKUnique (LeetCode 340) -- at most k, returns the length;
//     its count-array method keeps the number of different characters in a
//     variable instead of re-counting all 256 buckets.
//
// Time: O(256 * n) -- each character enters once and leaves at most once, and
//   every isValid call scans all 256 buckets.
// Space: O(n) -- the string copied into a char array; the counts are a fixed 256.

public final class LongestSubStrKUniqueCountArray {

  private static final int MAX = 256;

  public static Optional<String> kUnique(String str, int k) {
    int n = str.length();
    if (countUnique(str) < k) {
      return empty(); // RETURN: no substring can hold k different characters
    }

    int left = 0; // current start
    int right = 0; // current end
    int start = 0; // max window start
    int end = 0; // max window end
    int max = 0; // max window size

    int[] count = new int[MAX];
    char[] chars = str.toCharArray();
    count[chars[0]]++; // START: the window is the first character

    for (int i = 1; i < n; i++) {
      count[chars[i]]++; // ADD
      right++;

      while (!isValid(count, k)) { // SHRINK: while more than k different characters
        count[chars[left]]--;
        left++;
      }

      if (right - left + 1 > max) { // MEASURE: strictly longer, so the first longest stays
        max = right - left + 1;
        start = left;
        end = right;
      }
    }
    return of(str.substring(start, end + 1));
  }

  // true when the window holds at most k different characters
  private static boolean isValid(int[] a, int k) {
    int count = 0;
    for (int i = 0; i < MAX; i++) {
      if (a[i] > 0) {
        count++;
      }
    }
    return k >= count;
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
