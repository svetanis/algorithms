package com.svetanis.algorithms.search.quickselect;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;
import static com.svetanis.java.base.utils.Random.randomIndex;
import static com.svetanis.java.base.utils.Swap.swap;
import static java.lang.Math.pow;

import java.awt.Point;
import java.util.List;

import com.google.common.collect.ImmutableList;

public final class KClosestPoints {

  public static ImmutableList<Point> select(List<Point> points, int k) {
    // Time complexity: O(n)
    // Worst case: O(n^2)

    Distance[] a = distances(points);
    int pivot = select(a, 0, a.length - 1, k);
    List<Point> list = newArrayList();
    for (int i = 0; i <= pivot; i++) {
      list.add(points.get(a[i].index));
    }
    return newList(list);
  }

  private static int select(Distance[] a, int left, int right, int k) {
    int index = randomIndex(left, right);
    int pivot = partition(a, left, right, index);
    int dist = pivot - left + 1;
    if (dist == k) {
      return pivot;
    } else if (k < dist) {
      return select(a, left, pivot, k);
    } else {
      return select(a, pivot + 1, right, k - dist);
    }
  }

  // smaller elements to the left
  public static int partition(Distance[] a, int left, int right, int index) {
    int i = left;
    swap(a, right, index);
    Distance pivot = a[right];
    for (int j = left; j < right; ++j) {
      if (a[j].compareTo(pivot) < 1) {
        swap(a, i, j);
        i++;
      }
    }
    // move pivot to its final place
    swap(a, i, right);
    return i;
  }

  public static void main(String[] args) {
    List<Point> points = points();
    print(select(points, 2));
  }

  private static Distance[] distances(List<Point> points) {
    Distance[] a = new Distance[points.size()];
    for (int i = 0; i < points.size(); i++) {
      a[i] = new Distance(distToOrigin(points.get(i)), i);
    }
    return a;
  }

  private static ImmutableList<Point> points() {
    List<Point> list = newArrayList();
    list.add(new Point(1, 0));
    list.add(new Point(2, 1));
    list.add(new Point(0, 1));
    return newList(list);
  }

  private static int distToOrigin(Point p) {
    return dist(p, new Point(0, 0));
  }

  private static int dist(Point p1, Point p2) {
    double dx = p1.getX() - p2.getX();
    double dy = p1.getY() - p2.getY();
    return new Double(pow(dx, 2) + pow(dy, 2)).intValue();
  }

  private static class Distance implements Comparable<Distance> {

    private int dist;
    private int index;

    public Distance(int dist, int index) {
      this.dist = dist;
      this.index = index;
    }

    @Override
    public int compareTo(Distance other) {
      return this.dist - other.dist;
    }
  }

}
