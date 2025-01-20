package com.svetanis.algorithms.search.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1146. Snapshot Array

public final class SnapshotArray {

  private final List<Pair>[] snapshot;
  private int sid;

  public SnapshotArray(int len) {
    this.snapshot = new List[len];
    Arrays.setAll(snapshot, k -> new ArrayList<>());
  }

  public void set(int index, int val) {
    snapshot[index].add(new Pair(sid, val));
  }

  public int snap() {
    return ++sid;
  }

  public int get(int index, int sid) {
    List<Pair> list = snapshot[index];
    int left = 0;
    int right = list.size();
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (list.get(mid).sid > sid) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left == 0 ? 0 : list.get(left - 1).val;
  }

  public static void main(String[] args) {
    SnapshotArray sa = new SnapshotArray(3);
    sa.set(0, 5);
    System.out.println(sa.snap()); // 0
    sa.set(0, 6);
    System.out.println(sa.get(0, 0)); // 5
  }

  private static class Pair {
    private int sid;
    private int val;

    public Pair(int sid, int val) {
      this.sid = sid;
      this.val = val;
    }
  }
}