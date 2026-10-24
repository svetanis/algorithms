package com.svetanis.algorithms.greedy;

// 665. Non-decreasing Array
//
// Input: an array a. Returns whether a can be made non-decreasing -- every element at most the
// one after it -- by changing at most one element to any value. On LeetCode: n == a.length,
// 1 <= n <= 10^4, -10^5 <= a[i] <= 10^5.
//
// The one idea: walk the pairs left to right. A pair with a[i] > a[i + 1] is a fall, and it needs
// a change at i or at i + 1; the walk repairs it at once, counts the change, and goes on over the
// repaired array, so a second fall means a second change and the answer is false. Which of the
// two to change is the whole problem. Lowering a[i] to a[i + 1] is preferred: it leaves a[i + 1]
// as it is, and a smaller value there can only make the pairs further right easier. But lowering
// is legal only when it does not break the pair behind, that is when i == 0 or
// a[i - 1] <= a[i + 1]. When it is not legal, the change is forced the other way: raise a[i + 1]
// to a[i], the smallest value it can take once a[i] stays. Either way position i + 1 ends holding
// the smallest value it can hold with a[0..i + 1] in order, and the rest of the walk depends only
// on that value, so no other single change could have left the rest easier.
//
// The two methods make the same choices. checkPossibility walks pair (i, i + 1) and counts the
// change before testing it; checkPossibility2 walks pair (i - 1, i), fails at a drop once one
// change is already made, and names the jobs KEEP, FAIL, CHOOSE.
//
// Time: O(n) -- one pass over the pairs.
// Space: O(1) -- nothing is allocated. The repair is written straight into the caller's array,
// which comes back modified: the array is the running state on purpose, and that is what keeps
// this O(1), as in CanPlaceFlowers605. The signature promises only a boolean, so a caller who
// needs the array intact calls checkPossibility(a.clone()).

public final class NonDecreasingArray {

	public static boolean checkPossibility(int[] a) {
		int n = a.length;
		int count = 0;
		for (int i = 0; i < n - 1; i++) {
			if (a[i] > a[i + 1]) { // FALL: one change is needed at i or at i + 1
				count += 1; // COUNT the change
				if (count > 1) {
					return false;
				}
				if (i == 0 || a[i - 1] <= a[i + 1]) { // LEGAL to lower: no pair behind, or it still holds
					a[i] = a[i + 1]; // LOWER a[i] -- preferred
				} else {
					a[i + 1] = a[i]; // RAISE a[i+1] -- forced
				}
			}
		}
		return true; // RETURN: at most one change
	}

	public static boolean checkPossibility2(int[] nums) {
		int changes = 0; // CARRY: how many numbers changed so far
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] <= nums[i]) {
				continue; // KEEP: no drop at (i - 1, i)
			}
			if (changes == 1) {
				return false; // FAIL: a second drop
			}
			if (i - 2 < 0 || nums[i - 2] <= nums[i]) { // CHOOSE: lowering is allowed -- nothing left of it, or it fits
				nums[i - 1] = nums[i]; // LOWER the left number -- preferred, it leaves the smaller bar
			} else {
				nums[i] = nums[i - 1]; // RAISE the right one -- forced; the next pair reads it
			}
			changes += 1;
		}
		return true; // DONE: at most one change was needed
	}

	public static void main(String[] args) {
		int[] p1 = { 4, 2, 3 };
		System.out.println(checkPossibility(p1)); // true
		int[] p2 = { 4, 2, 1 };
		System.out.println(checkPossibility(p2)); // false
		int[] p3 = { 3, 4, 2, 3 };
		System.out.println(checkPossibility(p3)); // false
	}
}
