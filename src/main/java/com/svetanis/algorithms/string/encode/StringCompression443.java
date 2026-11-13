package com.svetanis.algorithms.string.encode;

// 443. String Compression
//
// Input: an array of characters. Rewrites it in place so that each run of one repeated character
// becomes the character followed by the run's length, with the length left out when it is 1, and a
// length of 10 or more written as several characters ("a","b"x12 -> "a","b","1","2"). Returns the
// new length; only that many characters at the front of the array count. On LeetCode:
// 1 <= chars.length <= 2000, each a lower- or upper-case letter, a digit or a symbol.
//
// The one idea: two positions in one array. start reads: it is the first character of the
// current run, and end walks to the first character after the run. len writes: everything
// before it is the finished answer. The written form of a run is never longer than the run itself
// (one character plus at most as many digits as the run has characters), so len never passes
// start and writing never overwrites a character that has not been read yet.
//
// Time: O(n) -- each character is read once by end; each run writes at most 1 + its digits.
// Space: O(1) -- besides the run length's digits, at most 4 characters.
//
// Not the same problem as StringCompression (3167), whose input is already compressed.

public final class StringCompression443 {

  public static int compress(char[] a) {
    int n = a.length;
    int len = 0; // the answer's length so far
    for (int start = 0; start < n;) {
      int end = start + 1;
      while (end < n && a[start] == a[end]) { // RUN: end stops at the first different character
        end++;
      }
      a[len++] = a[start]; // WRITE: the run's character
      if (end - start > 1) { // SKIP the length when the run is 1 long
        String count = String.valueOf(end - start);
        for (char c : count.toCharArray()) {
          a[len++] = c; // WRITE: the length, one digit per character
        }
      }
      start = end; // NEXT RUN
    }
    return len;
  }

  public static void main(String[] args) {
    char[] a = { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };
    System.out.println(compress(a)); // 6 : a2b2c3

    char[] a1 = { 'a' };
    System.out.println(compress(a1)); // 1

    char[] a2 = { 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' };
    System.out.println(compress(a2)); // 4 : ab12
  }
}
