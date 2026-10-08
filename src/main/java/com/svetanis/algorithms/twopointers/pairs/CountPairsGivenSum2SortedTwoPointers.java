package com.svetanis.algorithms.twopointers.pairs;

// Count Pairs With a Given Sum From Two Sorted Arrays -- two pointers
//
// Input: two arrays sorted in non-decreasing order, of any lengths, and a target k.
// Return: how many pairs (x from a1, y from a2) add up to k, each number used in at
// most one pair.
// Convention the code follows: with no repeated value inside either array this is the
// number of pairs of positions (i, j) with a1[i] + a2[j] == k. With repeats, each copy
// is used up by its pair: a1 = {5, 5}, a2 = {5}, k = 10 gives 1, not 2. The source
// statement would settle which count is wanted.
//
// The one idea: the converging loop of LC 167 Two Sum II with the two ends in two
// arrays: left walks a1 up from its smallest number, right walks a2 down from its
// largest. If the sum is too small, a1[left] is too small even with the largest number
// a2 has left, so it is in no pair and is dropped; the mirror holds for a2[right] when
// the sum is too big. Each pointer is bounded by its OWN array's length.
//
// Siblings:
//   CountPairsGivenSum2SortedBinary -- binary-searches a2 for each number of a1
//   CountPairsGivenSum2ArraysHashing -- unsorted input, a map over a1
//   twopointers.MaxNumOfKSumPairs1679 -- the same loop inside ONE array
//
// Time: O(n + m) -- every step moves left forward or right back.
// Space: O(1).

public final class CountPairsGivenSum2SortedTwoPointers {

  public static int count(int[] a1, int[] a2, int k) {
    int n1 = a1.length;
    int n2 = a2.length;
    int left = 0;                     // START: the smallest of a1
    int right = n2 - 1;               // START: the largest of a2
    int count = 0;
    while (left < n1 && right >= 0) { // STOP: left walks a1, so it is bounded by a1's length
      int sum = a1[left] + a2[right]; // COMPARE
      if (sum == k) {
        left++;                       // FOUND: both numbers are used up
        right--;
        count++;                      // RECORD the pair
      } else if (sum < k) {
        left++;                       // DROP LEFT: too small even with a2's largest left
      } else {
        right--;                      // DROP RIGHT: too big even with a1's smallest left
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 5, 7 };
    int[] a2 = { 2, 3, 5, 8 };
    System.out.println(count(a1, a2, 10)); // 2

    int[] a3 = { 1, 2, 3, 4, 5, 7, 11 };
    int[] a4 = { 2, 3, 4, 5, 6, 8, 12 };
    System.out.println(count(a3, a4, 9)); // 5

    int[] a5 = { 1 };
    int[] a6 = { 5, 6, 7 };
    System.out.println(count(a5, a6, 100)); // 0
  }
}
