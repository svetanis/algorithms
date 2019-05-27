package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Lists.newLinkedList;
import static com.svetanis.java.base.utils.Maps.freqMap;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public final class ContainsAnagram {

  public static boolean contains(String str, String pat) {
    // Time complexity: O(n)

    int n = str.length();
    Map<Character, Integer> map = freqMap(pat.toCharArray());
    Queue<Character> queue = newLinkedList();

    int right = 0; // current end

    for (int left = 0; left < n; left++) {
      while (right < n) {
        char c = str.charAt(right);
        if (map.containsKey(c)) {
          map.put(c, map.get(c) - 1);
          queue.offer(c);
        } else {
          while (!queue.isEmpty()) {
            char front = queue.poll();
            map.put(front, map.get(front) + 1);
          }
        }

        if (areZeros(map)) {
          System.out.println(queue);
          return true;
        }

        right++;
      }
    }
    return false;
  }

  private static boolean areZeros(Map<Character, Integer> map) {
    for (Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() != 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String str = "coding interview questions";
    String pat = "weivretni";
    System.out.println(contains(str, pat));
  }
}
