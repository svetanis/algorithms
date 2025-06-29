package com.svetanis.algorithms.recursive;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public final class TowerOfHanoi {

  public static ImmutableList<Pair<Integer, Integer>> moveTower(int n) {
    List<Pair<Integer, Integer>> list = newArrayList();
    transfer(n, 1, 3, 2, list);
    return newList(list);
  }

  private static void transfer(int n, int src, int dst, int aux, 
                            List<Pair<Integer, Integer>> list) {

    if (n == 1) {
      list.add(Pair.build(src, dst));
      return;
    }

    transfer(n - 1, src, aux, dst, list);
    list.add(Pair.build(src, dst));
    transfer(n - 1, aux, dst, src, list);
  }

  public static void main(String[] args) {
    print(moveTower(2));
  }
}
