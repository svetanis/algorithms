package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 901. Online Stock Span
//
// Prices arrive one day at a time. For each new price, return its span: the number of
// consecutive days, ending today, whose price is less than or equal to today's.
//
// A stream cannot be scanned backwards, so the answer is read at push time. The stack keeps
// only days with a higher price than every day after them, each with its own span; a day
// that pops them absorbs their spans, because every day they covered is covered by it too.
//
// Siblings: OnlineStockSpan901PrevGreater answers the same calls by storing each day's number
// and subtracting the nearest higher day's number. StockSpan is that previous-greater walk over
// a whole array of prices at once.

public final class OnlineStockSpan901 {
  // Time Complexity: O(1) amortized per call, each day is pushed once and popped at most once
  // Space Complexity: O(n) for the stack

  private final Deque<Day> stack = new ArrayDeque<>(); // STACK: prices strictly falling to the top

  public int next(int price) {
    int span = 1; // SPAN: today
    while (!stack.isEmpty() && stack.peek().price() <= price) { // POP: equal prices count too
      span += stack.pop().span(); // ADD: absorb the popped day's span
    }
    stack.push(new Day(price, span)); // PUSH: today, carrying its span
    return span; // RETURN
  }

  private record Day(int price, int span) {
  }

  public static void main(String[] args) {
    OnlineStockSpan901 oss = new OnlineStockSpan901();
    System.out.println(oss.next(100)); // 1
    System.out.println(oss.next(80)); // 1
    System.out.println(oss.next(60)); // 1
    System.out.println(oss.next(70)); // 2
    System.out.println(oss.next(60)); // 1
    System.out.println(oss.next(75)); // 4
    System.out.println(oss.next(85)); // 6
  }
}
