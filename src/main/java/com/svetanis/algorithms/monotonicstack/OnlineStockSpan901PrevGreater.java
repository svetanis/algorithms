package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 901. Online Stock Span
//
// Prices arrive one day at a time. For each new price, return its span: the number of
// consecutive days, ending today, whose price is less than or equal to today's.
//
// The count stops at the nearest earlier day with a strictly HIGHER price, so the span is
// today minus that day: previous greater, walked left to right. A stream has no array, so each
// stack entry carries its own price, and the class counts the days itself. With no higher day,
// prev is -1 and today - (-1) counts every day so far. OnlineStockSpan901 stores each day's
// span instead of its number and adds up the spans it pops. StockSpan is this same walk over a
// whole array of prices, where the array position is the day number.

public final class OnlineStockSpan901PrevGreater {
  // Time Complexity: O(1) amortized per call, each day is pushed once and popped at most once
  // Space Complexity: O(n) for the stack

  private final Deque<Day> stack = new ArrayDeque<>(); // STACK: prices strictly falling to the top
  private int index = 0; // DAY: today's day number

  public int next(int price) {
    while (!stack.isEmpty() && stack.peek().price() <= price) { // POP: equal prices count too
      stack.pop(); // DISCARD: inside today's span, so it can never stop a later count
    }
    int prev = stack.isEmpty() ? -1 : stack.peek().index(); // STOPPER: the nearest higher day
    int span = index - prev; // ANSWER: the days after prev, up to today
    stack.push(new Day(price, index)); // PUSH: it might stop a later day's count
    index++; // NEXT: the next call is the next day
    return span; // RETURN
  }

  // a day's price, and its number for the subtraction
  private record Day(int price, int index) {
  }

  public static void main(String[] args) {
    OnlineStockSpan901PrevGreater oss = new OnlineStockSpan901PrevGreater();
    System.out.println(oss.next(100)); // 1
    System.out.println(oss.next(80)); // 1
    System.out.println(oss.next(60)); // 1
    System.out.println(oss.next(70)); // 2
    System.out.println(oss.next(60)); // 1
    System.out.println(oss.next(75)); // 4
    System.out.println(oss.next(85)); // 6
  }
}
