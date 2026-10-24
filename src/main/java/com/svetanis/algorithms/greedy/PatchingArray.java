package com.svetanis.algorithms.greedy;

// 330. Patching Array
//
// Input: a sorted array nums and a number n. Returns the fewest numbers to add to nums so that
// every whole number from 1 to n is the sum of some of its elements, each element used at most
// once. On LeetCode: 1 <= nums.length <= 1000, 1 <= nums[i] <= 10^4, nums is sorted ascending,
// 1 <= n <= 2^31 - 1. Relies on nums being sorted, as LeetCode promises; on an unsorted array it
// can count too many.
//
// The one idea: miss is the first total that cannot be made yet, so every total from 1 to
// miss - 1 can be. Look at the next array element nums[i]. With it, the totals are the old ones,
// 1..miss - 1, and the old ones plus nums[i], nums[i]..miss - 1 + nums[i]. When nums[i] <= miss
// the second run starts no later than miss, so together they cover 1..miss - 1 + nums[i] with no
// gap, and miss becomes miss + nums[i]. If nums[i] > miss, or the array is used up, then nothing
// left in the array can ever make miss, because every later element is larger still. Some number
// at most miss has to be added, and adding miss itself reaches furthest: a patch p <= miss moves
// the first gap to miss + p, and p = miss makes that 2 * miss. So each step is forced -- use the
// next element when it fits, otherwise patch with miss -- and no step is ever undone.
//
// The array running out sends the walk to PATCH, not to the end: the bound i < nums.length sits
// in the if, and the loop ends only when every total up to n is made. miss is a long: at
// n = 2^31 - 1 the loop stops only once miss reaches 2^31, which no int holds.
//
// Time: O(m + log n) -- m = nums.length; each round either uses one array element, at most m
// rounds, or doubles miss, at most about 31 rounds before miss passes n <= 2^31 - 1.
// Space: O(1) -- three numbers; nums is read and left alone.

public final class PatchingArray {

	public static int minPatches(int[] nums, int n) {
		long miss = 1; // START: an empty array cannot make 1
		int patches = 0;
		int i = 0; // the next array element to read
		while (miss <= n) { // KEEP GOING: the total miss is still needed
			if (i < nums.length && nums[i] <= miss) { // bound first; AT MOST miss -- equal makes miss itself
				miss += nums[i]; // USE: the run 1..miss - 1 slides up by nums[i], no gap
				i += 1;
			} else { // too big, or none left -- nothing later fills the gap
				miss += miss; // PATCH with miss itself, which doubles the reach
				patches += 1;
			}
		}
		return patches; // REPORT
	}

	public static void main(String[] args) {
		int[] nums1 = { 1, 3 };
		System.out.println(minPatches(nums1, 6)); // 1

		int[] nums2 = { 1, 5, 10 };
		System.out.println(minPatches(nums2, 20)); // 2

		int[] nums3 = { 1, 2, 2 };
		System.out.println(minPatches(nums3, 5)); // 0

		int[] nums4 = { 1 };
		System.out.println(minPatches(nums4, 6)); // 2 -- the array runs out, and 2 and 4 are patched
	}
}
