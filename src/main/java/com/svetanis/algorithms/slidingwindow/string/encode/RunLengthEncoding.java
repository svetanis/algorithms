package com.svetanis.algorithms.slidingwindow.string.encode;

import static java.lang.Character.isDigit;

public final class RunLengthEncoding {

  public static String encode(String src) {
    int count = 1;
    int n = src.length();
    StringBuilder sb = new StringBuilder();
    char[] chars = src.toCharArray();
    
    for (int i = 1; i < n; ++i) {
      if (chars[i] == chars[i - 1]) {
        ++count;
      } else {
        sb.append(count);
        sb.append(chars[i - 1]);
        count = 1;
      }
    }
    sb.append(count);
    sb.append(chars[n - 1]);
    return sb.toString();
  }

  public static String decode(String src) {
    int count = 0;
    StringBuilder sb = new StringBuilder();

    for (char c : src.toCharArray()) {
      if (isDigit(c)) {
        count = count * 10 + c - '0';
      } else {
        for (int i = 0; i < count; ++i) {
          sb.append(c);
        }
        count = 0;
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    String str = "wwwwaaadexxxxxxxxxxxx";
    System.out.println("Source: " + str);
    System.out.println("Encoded: " + encode(str));
    System.out.println("Decoded: " + decode(encode(str)));
  }
}
