package com.svetanis.algorithms.dp.coinchange;

import static java.util.Arrays.asList;

import java.util.List;

// 518. Coin Change II

// Given a value N, if we want to make change for N cents, 
// and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins,
// how many ways can we make the change? The order of coins doesn’t matter.

// n - size of array of coins a
// v - coin value
// returns the count of ways we can sum
// a[0 ... n-1] coins to get sum v

// f(a) : min number of coins needed to make 
//        the amount of a using denominations
//        d0, ..., dk-1

// f(a) = 1 + min(f(a - d0), f(a - d1), ..., f(a - dk))

public final class CoinChangeRecursive {

	public static int count(List<Integer> list, int amount) {
		return count(list, 0, amount);
	}

	private static int count(List<Integer> list, int index, int amount) {
		// base case
		if (amount == 0) {
			return 1;
		}
		// if V < 0 then no solution exists
		if (amount < 0) {
			return 0;
		}
		// if there are no coins and V > 0,
		// then no solution exists
		if (index >= list.size() && amount >= 1) {
			return 0;
		}

		// return the sum of solutions
		// 1. include a[n - 1]: count(a[], n, v - a[n-1])
		int incl = count(list, index, amount - list.get(index));
		// 2. excluding a[n - 1]: count(a[], n - 1, v)
		int excl = count(list, index + 1, amount);
		return incl + excl;
	}

	public static void main(String[] args) {
		System.out.println(count(asList(1, 2, 3), 4)); // 4
		System.out.println(count(asList(1, 2, 5), 5)); // 4
		System.out.println(count(asList(2), 3)); // 0
	}
}
