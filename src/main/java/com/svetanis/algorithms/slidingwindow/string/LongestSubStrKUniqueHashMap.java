package com.svetanis.algorithms.slidingwindow.string;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Longest Substring with Exactly K Unique Characters
// (no LeetCode number; source unknown -- LeetCode 340 is the "at most k" version)
//
// Input: a string of characters with codes below 256, and a number k >= 1.
// Return: the longest substring holding exactly k different characters -- the
// first one when several tie -- or an empty Optional when the whole string holds
// fewer than k different characters.
// k = 0 is outside the problem: it returns the first character instead of an
// empty substring, and throws on an empty string. No rule for it is known, so it
// is left as it is.
//
// The one idea: run the "at most k different" window -- while the map holds more
// than k characters, lose characters from the left, deleting a character's key
// when its count reaches 0 so that map.size() is the number of different
// characters in the window. After the shrink, left..right is the longest window
// ending at right with at most k; it counts only when it has exactly k.
//
// Siblings:
//   LongestSubStrKUniqueCountArray -- the same answer with an int[256] of counts;
//     it records only windows with at most k, relying on the fact that, when the
//     string holds at least k different characters, the longest such window has
//     exactly k. It uses this file's countUnique.
//   LongestSubStrLenAtMostKUnique (LeetCode 340) -- at most k, returns the length.
//
// Time: O(n) -- each character enters the window once and leaves at most once;
//   countUnique is one more pass.
// Space: O(k) -- the map holds at most k + 1 characters; countUnique adds a fixed
//   256 counts.

public final class LongestSubStrKUniqueHashMap {

  private static final int MAX = 256;

  public static Optional<String> kUnique(String str, int k) {
    int n = str.length();
    if (countUnique(str) < k) {
      return empty(); // RETURN: no substring can hold k different characters
    }

    int left = 0; // current start
    int start = 0; // max window start
    int end = 0; // max window end
    int max = 0; // max window size
    Map<Character, Integer> map = new HashMap<>();

    for (int right = 0; right < n; right++) {
      char next = str.charAt(right);
      int freq = map.getOrDefault(next, 0) + 1;
      map.put(next, freq); // ADD
      while (map.size() > k) { // SHRINK: while more than k different characters
        char front = str.charAt(left);
        map.put(front, map.get(front) - 1);
        if (map.get(front) == 0) {
          map.remove(front); // REMOVE: count 0, so map.size() stops counting it
        }
        left++;
      }
      if (map.size() == k && right - left + 1 > max) { // MEASURE: exactly k, strictly longer
        max = right - left + 1;
        start = left;
        end = right;
      }
    }
    return of(str.substring(start, end + 1));
  }

  // the number of different characters in str; codes of 256 or more throw
  public static int countUnique(String str) {
    int k = 0;
    int[] count = new int[MAX];
    for (char c : str.toCharArray()) {
      if (count[c] == 0) {
        k++; // a character not seen before
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
