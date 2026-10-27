package com.svetanis.algorithms.prefixsum;

import java.util.ArrayList;
import java.util.List;

// 1352. Product of the Last K Numbers
//
// Input: a stream of calls. add(num) appends num to a list that starts empty; getProduct(k)
// returns the product of the last k numbers in the list, and the list always holds at least k. On
// LeetCode: 0 <= num <= 100, 1 <= k <= 4 * 10^4, at most 4 * 10^4 calls in all, and at any time
// the product of any run of neighbouring numbers fits in an int.
//
// The one idea: prefix.get(j) holds the product of the first j numbers added since the last zero,
// and prefix.get(0) = 1, the product of no numbers. The product of the last k numbers is then the
// product of all of them since the last zero divided by the product of all but the last k:
// last / prefix.get(n - 1 - k), and the division is exact because the divisor is a factor of
// last. A zero cannot be divided out, so a zero empties the list back to [1]. After that, a k that
// reaches back past the numbers since the zero (n <= k, where n - 1 numbers follow the zero) takes
// the zero into the product, and the answer is 0. Each stored product is the product of a run of
// neighbouring numbers, so LeetCode's guarantee keeps it inside an int.
//
// Time: O(1) per getProduct; O(1) per add on average -- clear() on a zero walks the entries it
// removes, and each entry is removed at most once after being added.
// Space: O(n) -- one entry per number added since the last zero.
//
// Siblings: none.

public final class ProductOfNumbers {

	private List<Integer> prefix;

	public ProductOfNumbers() {
		this.prefix = new ArrayList<>();
		prefix.add(1); // EMPTY PRODUCT: no numbers yet
	}

	public void add(int num) {
		if (num == 0) {
			prefix.clear(); // ZERO: nothing before it can be divided out, so start over
			prefix.add(1);
		} else {
			int last = prefix.get(prefix.size() - 1);
			prefix.add(num * last); // EXTEND: the product so far, times num
		}
	}

	public int getProduct(int k) {
		int n = prefix.size(); // n - 1 numbers since the last zero
		if (n <= k) {
			return 0; // REACHES the zero: the product holds a 0
		}
		int last = prefix.get(n - 1); // ALL the numbers since the last zero
		int first = prefix.get(n - 1 - k); // ALL BUT the last k of them
		return last / first; // DIVIDE: exact, first is a factor of last
	}

	public static void main(String[] args) {
		ProductOfNumbers pon = new ProductOfNumbers();
		pon.add(3); // [3]
		pon.add(0); // [3,0]
		pon.add(2); // [3,0,2]
		pon.add(5); // [3,0,2,5]
		pon.add(4); // [3,0,2,5,4]
		System.out.println(pon.getProduct(2)); // 20 -- 5 * 4
		System.out.println(pon.getProduct(3)); // 40 -- 2 * 5 * 4
		System.out.println(pon.getProduct(4)); // 0 -- 0 * 2 * 5 * 4
		pon.add(8); // [3,0,2,5,4,8]
		System.out.println(pon.getProduct(2)); // 32 -- 4 * 8
	}
}