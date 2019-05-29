package com.svetanis.algorithms.string.substr;

public class LongestRepeatedSubStrLen {

  public static int maxLen(String s) {
    int n = s.length();
    int max = 0;
    int count = 1;
    for(int i = 0; i < n - 1; i++) {
      if(s.charAt(i) == s.charAt(i + 1)) {
        count++;
      } else {
        max = Math.max(max, count);
        count = 1;
      }
    }
    max = Math.max(max, count);
    return max;
  }
  public static void main(String[] args) {
    System.out.println(maxLen("aabbccddd"));
  }
}
