package com.svetanis.algorithms.slidingwindow.array;

// 930. Binary Subarrays With Sum

public final class BinarySubArrWithSum {
	// Time complexity: O(n)

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
