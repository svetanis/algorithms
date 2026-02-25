package com.svetanis.algorithms.twopointers;

import static java.lang.String.valueOf;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

// 2562. Find the Array Concatenation Value

public final class ArrayConcatenationValue {
	// Time Complexity: O(n log m)
	// Space Complexity: O(log m)

	public static long arrayConcatenationValue(int[] a) {
		int left = 0;
		int right = a.length - 1;
		long total = 0;

		while (left < right) {
			int x = a[left];
			int y = a[right];

			// find how many digits y has
			int pow = 1;
			int temp = y;
			if (temp == 0) {
				pow = 10; // special case: 0 has 1 digit
			} else {
				while (temp > 0) {
					pow *= 10;
					temp /= 10;
				}
			}

			long concat = (long) x * pow + y; // x followed by digits of y
			total += concat;

			left++;
			right--;
		}

		if (left == right) {
			total += a[left];
		}
		return total;
	}

	public static long dividePlayers(int[] a) {
		int left = 0;
		int right = a.length - 1;
		long total = 0;
		while (left < right) {
			String concat = valueOf(a[left]) + valueOf(a[right]);
			total += Long.parseLong(concat);
			left++;
			right--;
		}
		// total += (left == right) ? a[left] : 0;
		if (left == right) {
			total += a[left];
		}
		return total;
	}

	public static void main(String[] args) throws IOException {
		int[] a1 = { 7, 52, 2, 4 };
		long r1 = dividePlayers(a1);
		int[] a2 = { 5, 14, 13, 8, 12 };
		long r2 = dividePlayers(a2);

		System.out.println(r1); // 596
		System.out.println(r2); // 673
		System.out.flush();

		// Also write to file so you can see output if terminal doesn't show it
		String outPath = Paths.get("ArrayConcatenationValue-output.txt").toAbsolutePath().toString();
		try (PrintWriter w = new PrintWriter(outPath)) {
			w.println(r1);
			w.println(r2);
		}
		System.out.println("(output also written to " + outPath + ")");
	}
}
