package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 888. Fair Candy Swap
//
// a[i] is the number of candies in Alice's i-th box, and b[j] the number in Bob's j-th box. They
// swap exactly one box each so that afterwards both have the same total. Return { x, y }: x is
// the candies in the box Alice gives, y the candies in the box Bob gives. The problem promises
// that at least one such swap exists; any one of them is accepted.
//
// After the swap Alice has sumA - x + y and Bob has sumB - y + x. Setting them equal:
//   (sumA - x) + y = (sumB - y) + x
//   sumA - sumB = 2 * (x - y)
//   x - y = (sumA - sumB) / 2
// So once the totals are known, each of Alice's boxes x has exactly one box size y that Bob must
// give back, y = x - (sumA - sumB) / 2. Put Bob's box sizes in a set, and for each of Alice's boxes
// ask the set whether that y exists. The variable diff holds (sumA - sumB) / 2. When sumA - sumB
// is odd no fair swap can exist, and the division rounds, so the method could return a pair that
// is not fair; the problem's promise rules that input out.
//
// Siblings, the same swap from another source, where there may be no answer ({-1, -1}):
//   SwapPairsHashing  the same set lookup
//   SwapPairsSort     sorts both and walks them with two pointers

public final class FairCandySwap {
	// Time Complexity: O(n + m), two sums, one pass to build the set, one pass over Alice's boxes
	// Space Complexity: O(m), the set of Bob's box sizes

	public static int[] fcs(int[] a, int[] b) {
		int sumA = IntStream.of(a).sum();
		int sumB = IntStream.of(b).sum();
		int diff = (sumA - sumB) / 2;            // HALF: x - y must equal this
		Set<Integer> set = Arrays.stream(b).boxed().collect(Collectors.toSet());
		for (int candy : a) {                    // TRY each box Alice could give
			int target = candy - diff;           // NEED: the box size Bob must give back
			if (set.contains(target)) {          // YES: Bob has a box of that size
				int[] result = { candy, target };
				return result;
			}
		}
		return null;                             // NONE: no fair swap, which the problem rules out
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1 };
		int[] b1 = { 2, 2 };
		System.out.println(Arrays.toString(fcs(a1, b1))); // [1, 2]

		int[] a2 = { 1, 2 };
		int[] b2 = { 2, 3 };
		System.out.println(Arrays.toString(fcs(a2, b2))); // [1, 2]

		int[] a3 = { 2 };
		int[] b3 = { 1, 3 };
		System.out.println(Arrays.toString(fcs(a3, b3))); // [2, 3]
	}
}
