package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Math.max;

import java.util.Map;
import java.util.Queue;

public final class LongestSubStrSizeKUnique {

  private static final int MAX = 256;

  public static String kUnique(String str, int k) {
    // Time complexity: O(n)
    
    int n = str.length();
    if (countUnique(str) < k) {
      System.out.println("Not enough unique chars");
      return "";
    }
    
    int right = 0; // current end
    int max = 0; // max window size
    String result = "";
    char[] chars = str.toCharArray();
    Map<Character, Integer> map = newHashMap();
    Queue<Character> queue = newLinkedList();
    
    for(int left = 0; left < n; left++) {
      while(right < n) {
        if (!map.containsKey(chars[right])) {
          queue.offer(chars[right]);
        }
        map.put(chars[right], right);
        if(map.size() > k) {
          left = max(left, map.get(queue.peek()) + 1);
          map.remove(queue.poll());
        }
        if (right - left > max) {
          max = right - left;
          result = str.substring(left, right + 1);
        }
        right++;
      }
    }
    return result;
  }

  private static int countUnique(String str) {
    int k = 0;
    int[] count = new int[MAX];
    // count num of unique chars
    for (char c : str.toCharArray()) {
      if (count[c] == 0) {
        k++;
      }
      count[c]++;
    }
    return k;
  }

  public static void main(String[] args) {
    String str1 = "aabbcc";
    String out1 = kUnique(str1, 1);
    System.out.println(out1 + ": " + out1.length());
    System.out.println();

    String out2 = kUnique(str1, 2);
    System.out.println(out2 + ": " + out2.length());
    System.out.println();

    String out3 = kUnique(str1, 3);
    System.out.println(out3 + ": " + out3.length());
    System.out.println();

    String str2 = "aaabbb";
    String out4 = kUnique(str2, 3);
    System.out.println(out4 + ": " + out4.length());
    System.out.println();

    String str3 = "aabacbebebe";
    String out5 = kUnique(str3, 3);
    System.out.println(out5 + ": " + out5.length());
    System.out.println();
  }
}
