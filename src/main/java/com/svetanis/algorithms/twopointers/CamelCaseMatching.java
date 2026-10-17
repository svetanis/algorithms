package com.svetanis.algorithms.twopointers;

import static java.lang.Character.isLowerCase;

import java.util.ArrayList;
import java.util.List;

// 1023. Camelcase Matching
//
// Input: an array of query words and a pattern, all made of English letters.
// Return: for each query, whether it matches the pattern -- whether lowercase letters
// can be inserted into the pattern (anywhere, any number of them, possibly none) to
// make the query. Read the other way: deleting some LOWERCASE letters of the query
// leaves exactly the pattern.
//
// The one idea: this is the subsequence scan of LC 392 with one extra rule. A query
// letter that is not matched is an insertion, and only lowercase letters may be
// inserted, so an unmatched UPPERCASE letter means no match. Scan the query; when its
// letter equals the pattern's next letter, match it, otherwise check it is lowercase.
// Once the pattern is used up, every query letter left is an insertion too.
// Matching an equal letter at once is safe, by the same argument as LC 392: the
// letters still ahead in the query can match everything a later choice could.
//
// Two helpers, the same check -- camelMatch calls match:
//   match  -- one walk along the query, checking each unmatched letter as it passes
//   match2 -- for each pattern letter, skips the lowercase letters that differ from
//             it, then demands an equal letter; not called by camelMatch
//
// Sibling: AppendCharsToMakeSubseq -- LC 2486, the same scan without the uppercase rule.
//
// Time: O(k * (n + m)) -- k queries, each scanned once (n = query length, m = pattern length).
// Space: O(k) for the output list.

public final class CamelCaseMatching {

  public static List<Boolean> camelMatch(String[] queries, String pattern) {
    List<Boolean> list = new ArrayList<>();
    for (String query : queries) {
      list.add(match(query, pattern));
    }
    return list;
  }

  private static boolean match(String s, String p) {
    int n = s.length();
    int m = p.length();
    int i = 0;                         // START: i scans the query
    int j = 0;                         // START: j is the next pattern letter to find
    while (i < n && j < m) {           // STOP: the query is used up, or the pattern is all found
      if (s.charAt(i) == p.charAt(j)) {
        j += 1;                        // MATCH: the next pattern letter is found
      } else {
        char c = s.charAt(i);
        if (c >= 'A' && c <= 'Z') {
          return false;                // REJECT: an uppercase letter cannot be an insertion
        }
      }
      i += 1;                          // MOVE: every query letter is looked at once
    }
    if (j != m) {
      return false;                    // REJECT: the query ran out before the pattern did
    }
    while (i < n) {                    // CHECK: every query letter left is an insertion
      char c = s.charAt(i);
      if (c >= 'A' && c <= 'Z') {
        return false;                  // REJECT: an uppercase insertion
      }
      i += 1;
    }
    return true;
  }

  private static boolean match2(String s, String p) {
    int n = s.length();
    int m = p.length();
    int i = 0;
    int j = 0;
    while (j < m) {                    // STOP: every pattern letter is matched
      while (i < n && isLowerCase(s.charAt(i))//
          && s.charAt(i) != p.charAt(j)) {
        i++;                           // SKIP: a lowercase insertion
      }
      if (i == n || s.charAt(i) != p.charAt(j)) {
        return false;                  // REJECT: query ran out, or an uppercase letter differs
      }
      i += 1;                          // MATCH: both move past the equal letter
      j += 1;
    }
    while (i < n && isLowerCase(s.charAt(i))) {
      i++;                             // SKIP: lowercase insertions after the pattern
    }
    return i == n;                     // REJECT unless nothing uppercase is left
  }

  public static void main(String[] args) {
    String[] queries = { "FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack" };
    System.out.println(camelMatch(queries, "FB"));    // [true, false, true, true, false]
    System.out.println(camelMatch(queries, "FoBa"));  // [true, false, true, false, false]
    System.out.println(camelMatch(queries, "FoBaT")); // [false, true, false, false, false]

    System.out.println(match2("FooBar", "FB"));       // true
    System.out.println(match2("FooBarTest", "FB"));   // false
    System.out.println(match2("ForceFeedBack", "FB")); // false
  }
}
