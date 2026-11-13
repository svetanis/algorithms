package com.svetanis.algorithms.string.reverse;

import java.util.ArrayDeque;
import java.util.Deque;

// Reverse a string (LeetCode 344, Reverse String, asks for the in-place char[] version)
//
// Input: a string str. Returns str with its characters in the opposite order.
//
// The one idea: a stack gives items back in the opposite order from the one they went in. Push the
// characters of str from the first to the last; the last character pushed is on top, so popping
// until the stack is empty returns the characters of str from the last to the first.
//
// Time: O(n) -- n pushes and n pops of constant cost.
// Space: O(n) -- the stack holds every character at the deepest point.
//
// Siblings: ReverseStringIterative reads the characters backwards instead of using a stack;
// ReverseStringSwap and ReverseStringInPlace swap in place and need no extra storage.

public final class ReverseStringStack {

  public static String reverse(String str) {
    StringBuilder sb = new StringBuilder();
    Deque<Character> stack = build(str);
    while (!stack.isEmpty()) {
      sb.append(stack.pop()); // POP: the last character pushed comes out first
    }
    return sb.toString();
  }

  private static Deque<Character> build(String str) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : str.toCharArray()) {
      stack.push(c); // PUSH: first to last
    }
    return stack;
  }

  public static void main(String[] args) {
    String str = "GeeksQuiz";
    System.out.println(reverse(str)); // ziuQskeeG
  }
}
