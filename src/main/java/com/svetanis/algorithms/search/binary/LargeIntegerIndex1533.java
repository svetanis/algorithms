package com.svetanis.algorithms.search.binary;

// 1533. Find the Index of the Large Integer

public final class LargeIntegerIndex1533 {

  public static int binary(ArrayReader reader) {
    int left = 0;
    int right = reader.length() - 1;
    while (left < right) {
      int mid1 = left + (right - left) / 3;
      int mid2 = left + (right - left) / 3 * 2 + 1;

      int compare = reader.compareSub(left, mid1, mid1 + 1, mid2);
      if (compare == 0) {
        left = mid2 + 1;
      } else if (compare == 1) {
        right = mid1;
      } else {
        left = mid1 + 1;
        right = mid2;
      }
    }
    return left;
  }

  public static void main(String[] args) {
    int[] a1 = { 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30 };
    ArrayReader reader = new ArrayReader(a1);
    System.out.println(binary(reader));
    System.out.println(binary(reader));

    int[] a2 = { 1, 3, 8, 10, 15 };
    ArrayReader reader2 = new ArrayReader(a2);
    System.out.println(binary(reader2));
    System.out.println(binary(reader2));
  }

  private static class ArrayReader {
    private int[] a;

    public ArrayReader(int[] a) {
      this.a = a;
    }

    public int length() {
      return a.length;
    }

    public int compareSub(int left1, int right1, int left2, int right2) {
      return -1;
    }
  }
}
