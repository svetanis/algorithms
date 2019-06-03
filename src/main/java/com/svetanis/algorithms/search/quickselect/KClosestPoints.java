package com.svetanis.algorithms.search.quickselect;

import static com.svetanis.java.base.utils.Print.print;
import static com.svetanis.java.base.utils.Random.randomIndex;
import static com.svetanis.java.base.utils.Swap.swap;
import static java.lang.Math.pow;
import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;

import java.awt.Point;
import java.util.Comparator;
import java.util.List;

public final class KClosestPoints {

  private static final Comparator<Point> COMPARATOR = new PointComparator();

  public static List<Point> select(Point[] a, int k) {
    // Time complexity: O(n)
    // Worst case: O(n^2)

    int n = a.length;
    if (k <= 0) {
      return asList(new Point());
    }
    int pivot = select(a, 0, n - 1, k);
    return asList(copyOf(a, pivot + 1));
  }

  private static int select(Point[] a, int left, int right, int k) {
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
  public static int partition(Point[] a, int left, int right, int index) {
    int i = left;
    swap(a, right, index);
    Point pivot = a[right];
    for (int j = left; j < right; ++j) {
      if (COMPARATOR.compare(a[j], pivot) < 1) {
        swap(a, i, j);
        i++;
      }
    }
    // move pivot to its final place
    swap(a, i, right);
    return i;
  }

  public static void main(String[] args) {
    Point[] points = points();
    print(select(points, 2));
  }

  private static class PointComparator implements Comparator<Point> {

    private final Point origin = new Point();

    private double dist(Point p1, Point p2) {
      double dx = p1.getX() - p2.getX();
      double dy = p1.getY() - p2.getY();
      return pow(dx, 2) + pow(dy, 2);
    }

    @Override
    public int compare(Point p1, Point p2) {
      double d1 = dist(p1, origin);
      double d2 = dist(p2, origin);
      if (d1 < d2) {
        return -1;
      }
      if (d1 == d2) {
        return 0;
      }
      return 1;
    }
  }

  private static Point[] points() {
    Point[] points = new Point[3];
    points[0] = new Point(3, 3);
    points[1] = new Point(5, -1);
    points[2] = new Point(-2, 4);
    return points;
  }
}
