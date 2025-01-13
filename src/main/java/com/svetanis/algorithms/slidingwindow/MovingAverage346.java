package com.svetanis.algorithms.slidingwindow;

// 346. Moving Average from Data Stream

public final class MovingAverage346 {

  private int[] window;
  private int sum;
  private int count;

  public MovingAverage346(int size) {
    this.window = new int[size];
  }

  public double next(int val) {
    int index = count % window.length;
    sum -= window[index];
    sum += val;
    window[index] = val;
    count++;
    int min = Math.min(count, window.length);
    double average = (double) sum / min;
    return average;
  }

  public static void main(String[] args) {
    MovingAverage346 mva = new MovingAverage346(3);
    System.out.println(mva.next(5)); // 5
    System.out.println(mva.next(10)); // 7.5
    System.out.println(mva.next(15)); // 10
    System.out.println(mva.next(20)); // 15
  }
}