package com.svetanis.algorithms.recursive;

public final class RegExMatcher {

  // * --> Matches with 0 or more instances of any character or set of
  // characters.
  // ? --> Matches with any one character.

  public static boolean match(String str, String regex) {
    return match(str, regex, 0, 0);
  }

  private static boolean match(String str, String regex, int i, int j) {

    int n = str.length();
    int m = regex.length();

    if (j == m) {
      return i == n;
    }

    // make sure that the chars
    // after '*' are present in text
    if (regex.charAt(j) == '*' && i == n) {
      return j == m - 1;
    }

    // if the pattern contains '?' or
    // current chars of both strings match
    if (i < n && isMatch(str, regex, i, j)) {
      return match(str, regex, i + 1, j + 1);
    }

    // if there is '*', then there are two possibilities
    // 1. we consider current char of text
    // 2. we ignore current char of text
    if (regex.charAt(j) == '*') {
      boolean incl = match(str, regex, i, j + 1);
      boolean excl = match(str, regex, i + 1, j);
      return incl || excl;
    }
    return false;
  }

  private static boolean isMatch(String str, String regex, int i, int j) {
    boolean one = regex.charAt(j) == '?';
    boolean two = str.charAt(i) == regex.charAt(j);
    return one || two;
  }

  public static void main(String[] args) {
    System.out.println(match("geeks", "g*ks")); // true
    System.out.println(match("geeksforgeeks", "ge?ks*")); // true
    System.out.println(match("gee", "g*k")); // false
    System.out.println(match("pqrst", "*pqrs")); // false
    System.out.println(match("abcdhghgbcd", "abc*bcd")); // true
    System.out.println(match("abcd", "abc*c?d")); // false
    System.out.println(match("abcd", "*c*d")); // true
    System.out.println(match("abcd", "*?c*d")); // true
    System.out.println(match("ab", "ab*")); // true
  }
}
