package com.svetanis.algorithms.slidingwindow.array;

// 930. Binary Subarrays With Sum

// count subarrays summing to EXACTLY target. exactly-K is not a
// window: shrinking a valid window drops you to target-1, so there
// is no shrink rule and no single window can answer it. the escape
// is atMost(target) - atMost(target-1), and this file FUSES those
// two passes into one loop by running two left pointers at once.

// left1 trails the "sum <= target" window: after its loop,
//   sum(left1..right) <= target, so the starts in [left1, right]
//   are exactly the atMost(target) subarrays ending at right
//     -> that count is (right - left1 + 1)
//
// left2 trails the "sum < target" window: after its loop,
//   sum(left2..right) < target, i.e. <= target-1
//     -> that count is (right - left2 + 1)
//
// so, per position:
//   exactly(target) = (right - left1 + 1) - (right - left2 + 1)
//                   = left2 - left1
//
// THE +1s CANCEL. that is why the line below has no +1 and needs
// none -- it is a difference of two counts, not a count itself.
// contrast CountSubArraysKDistinct, where atMost is a standalone
// helper: there the +1 must be present, because that helper can be
// called on its own and would otherwise be wrong by n.

public final class BinarySubArrWithSum {
	// Time complexity: O(n) -- one pass; each of the three indices
	// only ever moves forward, so it is O(n) despite the inner loops
	// Aux Space: O(1)

	public static int countSubArrs(int[] a, int target) {
		int sum1 = 0, sum2 = 0;
		int left1 = 0, left2 = 0;
		int right = 0, count = 0;
		int n = a.length;
		while(right < n) {
			sum1 += a[right];
			sum2 += a[right];
			while(left1 <= right && sum1 > target) {
				sum1 -= a[left1++];
			}
			while(left2 <= right && sum2 >= target) {
				sum2 -= a[left2++];
			}
			// atMost(target) - atMost(target-1), for windows ending
			// at 'right'. no +1: see the derivation above.
			count += left2 - left1;
			right++;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1,0,1,0,1 };
		System.out.println(countSubArrs(a1, 2)); // 4

		int[] a2 = { 0,0,0,0,0 };
		System.out.println(countSubArrs(a2, 0)); // 15
	}
}
