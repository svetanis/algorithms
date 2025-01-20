package com.svetanis.algorithms.search.binary.rotated;

// 33. Search in Rotated Sorted Array

// given a sorted array of integers in ascending order
// and also rotated by some arbitrary number, 
// find if a given key is present in it

/*
calculate the middle
compare the numbers at indices start and middle. 

there are two options:

If arr[start] <= arr[middle], the numbers from start to middle
are sorted in ascending order.
Else, the numbers from middle+1 to end are sorted in ascending order.

Once we know which part of the array is sorted, it is easy to adjust our ranges. 
For example, if option-1 is true, there are two choices:

By comparing the ‘key’ with the numbers at index start and middle 
find out if the ‘key’ lies between indices start and middle; 
if it does, skip the second part => end = middle -1.
Else, skip the first part => start = middle + 1.
*/

public final class RotatedGivenElementNoDuplicatesIterative {
	// Time Complexity: O(log n)

	public static int search(int[] a, int target) {
		int left = 0;
		int right = a.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (a[mid] == target) {
				return mid;
			}
			// a[left ... mid] sorted
			if (a[left] <= a[mid]) {
				if (target >= a[left] && target < a[mid]) {
					right = mid - 1;
				} else { // k > a[mid]
					left = mid + 1;
				}
			} else {
				// a[mid ... right] sorted
				if (target >= a[mid] && target <= a[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 6, 7, 8, 9, 10, 1, 2, 3 };
		System.out.println(search(a1, 3));

		int[] a2 = { 3, 4, 5, 1, 2 };
		System.out.println(search(a2, 3));
		System.out.println(search(a2, 4));

		int[] a3 = { 1, 2, 3, 4 };
		System.out.println(search(a3, 3));
	}
}