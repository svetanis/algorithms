package com.svetanis.algorithms.string.reverse;

import java.util.Stack;

public final class ReverseStringStack {

  public static String reverse(String str) {
    StringBuilder sb = new StringBuilder();
    Stack<Character> stack = build(str);
    while (!stack.empty()) {
      sb.append(stack.pop());
    }
    return sb.toString();
  }

  private static Stack<Character> build(String str) {
    Stack<Character> stack = new Stack<>();
    for (Character c : str.toCharArray()) {
      stack.push(c);
    }
    return stack;
  }

  public static void main(String[] args) {
    String str = "GeeksQuiz";
    System.out.println(reverse(str));
  }
}
