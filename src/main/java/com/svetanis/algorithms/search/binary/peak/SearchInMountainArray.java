package com.svetanis.algorithms.search.binary.peak;

// 1095. Find in Mountain Array

public final class SearchInMountainArray {
	// Time Complexity: O(log n)

	public static int search(int target, MountainArray ma) {
		int n = ma.length();
		// 1. find the peak index
		int peak = peak(ma, 0, n - 1);
		// 2. search target in increasing part
		int index = binary(ma, target, 0, peak, true);
		if (index != -1) {
			return index;
		}
		// 3. search target in decreasing part
		return binary(ma, target, peak + 1, n - 1, false);
	}

	private static int binary(MountainArray ma, int target, int left, int right, boolean asc) {
		while (left <= right) {
			int mid = left + (right - left) / 2;
			int val = ma.get(mid);
			if (val == target) {
				return mid;
			}
			if (asc) {
				if (val < target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else {
				if (val > target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}

	private static int peak(MountainArray ma, int left, int right) {
		int peak = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (ma.get(mid) > ma.get(mid + 1)) {
				peak = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return peak;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4, 5, 3, 1 };
		MountainArray ma1 = new MountainArray(a1);
		System.out.println(search(3, ma1)); // 2

		int[] a2 = { 0, 1, 2, 4, 2, 1 };
		MountainArray ma2 = new MountainArray(a2);
		System.out.println(search(3, ma2)); // -1
	}

	private static class MountainArray {
		private int[] a;

		public MountainArray(int[] a) {
			this.a = a;
		}

		public int get(int k) {
			return k < a.length ? a[k] : -1;
		}

		public int length() {
			return a.length;
		}
	}
}