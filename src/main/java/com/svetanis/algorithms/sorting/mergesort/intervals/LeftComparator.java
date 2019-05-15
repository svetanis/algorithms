package com.svetanis.algorithms.sorting.mergesort.intervals;

import java.util.Comparator;

import com.svetanis.java.base.Pair;

public final class LeftComparator implements Comparator<Pair<Integer, Integer>> {


  public static Comparator<Pair<Integer, Integer>> left() {
    return new LeftComparator();
  }

  @Override
  public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
    return p1.getLeft() - p2.getLeft();
  }
}