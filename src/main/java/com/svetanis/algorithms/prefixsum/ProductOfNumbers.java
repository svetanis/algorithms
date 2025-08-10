package com.svetanis.algorithms.prefixsum;

import java.util.ArrayList;
import java.util.List;

// 1352. Product of the Last K Numbers

public final class ProductOfNumbers {

	private List<Integer> prefix;

	public ProductOfNumbers() {
		this.prefix = new ArrayList<>();
		prefix.add(1);
	}

	public void add(int num) {
		if (num == 0) {
			prefix.clear();
			prefix.add(1);
		} else {
			int last = prefix.get(prefix.size() - 1);
			prefix.add(num * last);
		}
	}

	public int getProduct(int k) {
		int n = prefix.size();
		if (n <= k) {
			return 0;
		}
		int last = prefix.get(n - 1);
		int first = prefix.get(n - 1 - k);
		return last / first;
	}

	public static void main(String[] args) {
		ProductOfNumbers pon = new ProductOfNumbers();
		pon.add(3); // [3]
		pon.add(0); // [3,0]
		pon.add(2); // [3,0,2]
		pon.add(5); // [3,0,2,5]
		pon.add(4); // [3,0,2,5,4]
		System.out.println(pon.getProduct(2)); // return 20. The product of the last 2 numbers is 5 * 4 = 20
		System.out.println(pon.getProduct(3)); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
		System.out.println(pon.getProduct(4)); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
		pon.add(8); // [3,0,2,5,4,8]
		System.out.println(pon.getProduct(2)); // return 32. The product of the last 2 numbers is 4 * 8 = 32
	}
}