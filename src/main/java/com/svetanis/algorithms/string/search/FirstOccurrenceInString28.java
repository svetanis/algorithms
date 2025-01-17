package com.svetanis.algorithms.string.search;

// 28. Find the Index of the First Occurrence in a String
// com.svetanis.algorithms.string.search.StrStr

public final class FirstOccurrenceInString28 {

  public static int search(String haystack, String needle) {
    if (needle.isEmpty()) {
      return 0;
    }
    int n = haystack.length();
    int m = needle.length();
    int hp = 0;
    int np = 0;
    while (hp < n) {
      if (haystack.charAt(hp) == needle.charAt(np)) {
        if (m == 1) {
          return hp;
        }
        hp++;
        np++;
      } else {
        hp -= np - 1;
        np = 0;
      }

      if (np == m) {
        return hp - np;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(search("sadbutsad", "sad")); // 0
    System.out.println(search("leetcode", "leeto")); // -1
  }
}
