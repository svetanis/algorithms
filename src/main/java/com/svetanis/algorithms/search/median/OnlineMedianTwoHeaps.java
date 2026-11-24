package com.svetanis.algorithms.search.median;

import static java.util.Comparator.reverseOrder;

import java.util.PriorityQueue;
import java.util.Queue;

// 295. Find Median from Data Stream

// Implement addNum(num), which adds a number from the stream, and
// findMedian(), which returns the median of all numbers so far -- the
// middle value for an odd count, the average of the two middle values
// for an even one. Here they are add and median.
// Takes -10^5 <= num <= 10^5, at most 5 * 10^4 calls, and at least one
// number before findMedian; median() on an empty structure throws.

// Two heaps split the numbers by value: max holds the smaller half and
// its top is their largest; min holds the larger half and its top is
// their smallest. add() places by comparing with max's top, so everything
// in max stays <= everything in min; rebalance() moves one value across
// when the sizes differ by two. The tops are then the two middles.

// Time: add -- O(log n); median -- O(1).
// Space: O(n) -- every number is kept.

// Siblings:
//   OnlineMedianTwoHeapsRemove -- the same two heaps plus remove(), for SlidingWindowMedianHeapRemove.
//   MedianTwoHeapsLazyDeletion -- two heaps with lazy deletion, for a window of fixed size.

public final class OnlineMedianTwoHeaps {

  private static final String EMPTY = "no values added";

  private Queue<Integer> min;
  private Queue<Integer> max;

  public OnlineMedianTwoHeaps() {
    this.min = new PriorityQueue<>(Integer::compare);
    this.max = new PriorityQueue<>(reverseOrder());
  }

  public void add(int x) {
    if (!max.isEmpty() && x > max.peek()) { // ADD: above max's top, to the larger half
      min.offer(x);
    } else { // ADD: to the smaller half
      max.offer(x);
    }
    rebalance();
  }

  private void rebalance() {
    if (min.size() > max.size() + 1) { // BALANCE: move one value across
      max.offer(min.poll());
    } else if (max.size() > min.size() + 1) {
      min.offer(max.poll());
    }
  }

  public double median() {
    if (max.isEmpty()) { // EMPTY: add-only, so an empty max means nothing was added
      throw new IllegalStateException(EMPTY);
    }
    if (min.size() == max.size()) {
      return 0.5 * (min.peek() + max.peek()); // EVEN: the average of the two tops
    } else {
      return max.size() > min.size() ? max.peek() : min.peek(); // ODD: the bigger heap's top
    }
  }

  public static void main(String args[]) {
    OnlineMedianTwoHeaps om = new OnlineMedianTwoHeaps();
    om.add(1);
    om.add(2);
    System.out.println(om.median()); // 1.5
    om.add(3);
    System.out.println(om.median()); // 2.0
  }
}
