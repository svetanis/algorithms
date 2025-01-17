package com.svetanis.algorithms.string.encode;

// 443. String Compression

public final class StringCompression443 {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int compress(char[] a) {
    int n = a.length;
    int len = 0;
    for (int start = 0; start < n;) {
      int end = start + 1;
      while (end < n && a[start] == a[end]) {
        end++;
      }
      a[len++] = a[start];
      if (end - start > 1) {
        String count = String.valueOf(end - start);
        for (char c : count.toCharArray()) {
          a[len++] = c;
        }
      }
      start = end;
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
