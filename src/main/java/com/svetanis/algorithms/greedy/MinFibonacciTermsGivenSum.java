package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

// 1414. Find the Minimum Number of Fibonacci Numbers Whose Sum Is K
//
// Input: n -- the sum wanted (k on LeetCode). The Fibonacci numbers are 1, 1, 2, 3, 5, 8, ...,
// each the sum of the two before it, and the same one may be used more than once. Returns the
// fewest Fibonacci numbers that add up to n. On LeetCode: 1 <= n <= 10^9. The largest Fibonacci
// number built is at most 10^9, and the next one tried stays below 2 * 10^9, inside an int.
//
// The one idea: take the largest Fibonacci number that fits, subtract it, and repeat on what is
// left. This is safe because a shortest answer never needs two neighbours of the sequence: two
// neighbours add up to the next Fibonacci number, so they can be swapped for one number and the
// answer gets shorter. Nor does it need the same number twice: 2 * F equals the next Fibonacci
// number plus the one two places below F, the same count with a bigger number in it. An answer
// with no repeats and no neighbours, all below some Fibonacci number G, always adds up to less
// than G. So if the largest Fibonacci number that fits in n were left out, the rest could never
// reach n -- it must be in the answer, and taking it first never has to be undone. The list holds
// every Fibonacci number up to n, smallest first, and the walk reads it from the large end; n / fib
// counts how many copies fit and n % fib is what is left.
//
// Time: O(log n) -- Fibonacci numbers grow by a factor of about 1.6 per step, so about 45 of them
// are at most 10^9; building the list is one pass over them and the walk is another.
// Space: O(log n) -- the list.

public final class MinFibonacciTermsGivenSum {

	public static int minTerms(int n) {
		List<Integer> list = fibNums(n);
		int count = 0;
		int right = list.size() - 1; // LARGEST Fibonacci number <= n
		while (n > 0) { // WALK from the large end while anything is left
			int fib = list.get(right);
			count += n / fib; // TAKE as many copies as fit
			n = n % fib; // what is left is below fib
			right--;
		}
		return count; // RETURN: the number of terms taken
	}

	private static List<Integer> fibNums(int n) {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(1);

		int i = 3;
		int next = list.get(i - 1) + list.get(i - 2);
		while (next <= n) { // BUILD every Fibonacci number up to n
			list.add(next);
			i++;
			next = list.get(i - 1) + list.get(i - 2);
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(minTerms(4)); // 2
		System.out.println(minTerms(7)); // 2
		System.out.println(minTerms(10)); // 2
		System.out.println(minTerms(19)); // 3
	}
}
