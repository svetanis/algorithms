package com.svetanis.algorithms.dp.countways.jumps;

import java.util.ArrayDeque;
import java.util.Queue;

// 1871. Jump Game VII

public final class JumpGameVIIQueue {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static boolean canReach(String s, int minJump, int maxJump) {
    int n = s.length();
    if (n == 1) {
      return true;
    }
    boolean[] visited = new boolean[n];
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);
    visited[0] = true;
    int maxReach = 0;
    while (!queue.isEmpty()) {
      int index = queue.poll();
      if (index == n - 1) {
        return true;
      }
      int start = Math.max(index + minJump, maxReach + 1);
      int end = Math.min(index + maxJump, n - 1);
      for (int i = start; i <= end; i++) {
        if (s.charAt(i) == '0' && !visited[i]) {
          visited[i] = true;
          queue.offer(i);
        }
      }
      maxReach = end;
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println(canReach("011010", 2, 3)); // true
    System.out.println(canReach("01101110", 2, 3)); // false
  }
}
