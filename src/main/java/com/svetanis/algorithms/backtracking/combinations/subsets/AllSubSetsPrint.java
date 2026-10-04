package com.svetanis.algorithms.backtracking.combinations.subsets;

// Print every subset of a string of distinct characters
//
// Given a string, print each of its subsets on its own line, the empty one included (as an empty
// line). Nothing is returned.
//
// Take-or-skip: decide each character in turn, first skipping it, then taking it. out is a fixed
// buffer of n slots and k is how many of them are filled. Taking writes the character into out[k]
// and passes k + 1; skipping passes k unchanged. Nothing is removed afterwards, because the
// caller's k never counted that slot -- the next write simply overwrites it.

public final class AllSubSetsPrint {

  // Time Complexity: O(n * 2^n), 2^n leaves, each printing up to n characters
  // Space Complexity: O(n), the buffer and the recursion depth

  public static void subset(String str) {
    int n = str.length();
    char[] out = new char[n];
    subset(str.toCharArray(), 0, out, 0);
  }

  private static void subset(char[] in, int i, char[] out, int k) {
    if (i == in.length) { // every character decided: out[0..k-1] is one subset
      System.out.println(new String(out, 0, k)); // only the k filled slots
      return;
    }
    // skip in[i]: k is unchanged
    subset(in, i + 1, out, k);
    // take in[i]: write it into the next free slot
    out[k] = in[i];
    subset(in, i + 1, out, k + 1);
  }

  public static void main(String[] args) {
    // "", "c", "b", "bc", "a", "ac", "ab", "abc", one per line
    subset("abc");
  }
}
