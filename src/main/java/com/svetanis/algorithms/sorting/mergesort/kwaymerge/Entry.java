package com.svetanis.algorithms.sorting.mergesort.kwaymerge;

import java.util.Iterator;

import com.google.common.collect.ComparisonChain;

public final class Entry<C extends Comparable<C>> implements Comparable<Entry<C>> {

  private C value;
  private Iterator<C> iter;

  public Entry(C value, Iterator<C> iter) {
    this.value = value;
    this.iter = iter;
  }

  public C getValue() {
    return this.value;
  }

  public boolean readNext() {
    if (iter.hasNext()) {
      value = iter.next();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int compareTo(Entry<C> other) {
    ComparisonChain chain = ComparisonChain.start();
    chain = chain.compare(value, other.value);
    return chain.result();
  }

  @Override
  public String toString() {
    return value + "";
  }
}