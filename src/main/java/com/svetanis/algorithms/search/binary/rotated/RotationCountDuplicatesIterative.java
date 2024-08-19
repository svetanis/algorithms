package com.svetanis.algorithms.search.binary.rotated;

// given a sorted array of distinct integer in ascending order
// and is rotated k times around a pivot, find the number of rotations

// number of rotations is equal to index of minimum element

public final class RotationCountDuplicatesIterative {

	public static int count(int[] a) {
		// time complexity: O(log n)

		int left = 0, right = a.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;

			if (mid < right && a[mid] > a[mid + 1]) {
				return mid + 1;
			}

			if (mid > left && a[mid - 1] > a[mid]) {
				return mid;
			}
			
			// if numbers at left, mid and right are the same
			// skip one number from both sides if they are not
			// the smallest number
			if(a[left] == a[mid] && a[mid] == a[right]) {
				if(a[left] > a[left + 1]) {
					return left + 1;
				}
				left++;
				if(a[right - 1] > a[right]) {
					return right;
				}
				right--;
			} else if (a[left] < a[mid] || (a[left] == a[mid] && a[mid] > a[right])) {
			// left side is sorted,
			// pivot is on the right side
				left = mid + 1;
			} else {
			// right side is sorted,
			// pivot is on the left side
				right = mid - 1;
			}
		}
		// the array is not rotated
		return 0;
	}

	public static void main(String[] args) {
		int[] a = { 3, 3, 7, 3 };
		System.out.println(count(a));
	}
}