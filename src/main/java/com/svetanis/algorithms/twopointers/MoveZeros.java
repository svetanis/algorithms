package com.svetanis.algorithms.twopointers;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;

// 283. Move Zeroes
//
// Input: an array of integers.
// Return: nothing -- the array is changed in place so that every 0 is at the back and
// the other numbers keep their original order.
//
// The one idea: two pointers moving the same way. fast reads every number once; slow
// is the next slot for a number that is kept. Everything before slow is finished: the
// non-zeros seen so far, in their original order. A non-zero at fast is written at
// slow, and slow moves; a zero is passed over. Because 283 returns no length, the
// tail after slow must end up all zeros, not merely ignorable.
//
// Three methods, the same problem -- all three are correct; pick one before an
// interview:
//   moveZeros(int[])         -- SWAPS a[slow] and a[fast]: a zero is carried rightward
//                               for free, one pass
//   segregate(int[])         -- OVERWRITES a[slow] with a[fast], then fills the tail
//                               with zeros in a second pass
//   moveZeros(List<Integer>) -- the swap version on a List. It reads both numbers into
//                               int locals first, so != 0 compares ints, never two
//                               Integer references
// Sibling: twopointers.segregate.MoveZerosToEnd -- the same swap and overwrite methods
//   on int[], filed with the other partition problems.
//
// Time: O(n) -- fast visits each number once; segregate's fill adds at most n writes.
// Space: O(1).

public final class MoveZeros {

	public static void moveZeros(List<Integer> nums) {
		int slow = 0;                                    // START: the next slot for a non-zero
		for (int fast = 0; fast < nums.size(); fast++) { // SEE every number once
			int curr = nums.get(fast);
			if (curr != 0) {                             // KEEP a non-zero; an int, not an Integer
				int prev = nums.get(slow);
				nums.set(slow, curr);                    // WRITE it at slow
				nums.set(fast, prev);                    // SWAP: the zero is pushed rightward for free
				slow++;
			}
		}
	}

	public static void segregate(int[] a) {
		int n = a.length;
		int slow = 0;                          // START: the next slot for a non-zero
		for (int fast = 0; fast < n; fast++) { // SEE every number once
			if (a[fast] != 0) {
				a[slow++] = a[fast];           // WRITE: pass 1 compacts the non-zeros
			}
		}
		while (slow < n) {
			a[slow++] = 0;                     // FILL: pass 2 writes the zeros into the tail
		}
	}

	public static void moveZeros(int[] a) {
		int n = a.length;
		int slow = 0;                          // START: the next slot for a non-zero
		for (int fast = 0; fast < n; fast++) { // SEE every number once
			if (a[fast] != 0) {
				int temp = a[slow];
				a[slow] = a[fast];             // WRITE it at slow
				a[fast] = temp;                // SWAP: the zero is pushed rightward for free
				slow++;
			}
		}
	}

	public static void main(String[] args) {
		List<Integer> list1 = asList(1, 0, 2, 0, 0, 7);
		List<Integer> list2 = asList(3, 1, 0, 1, 3, 8, 9);
		List<Integer> list3 = asList(0, 0, 9, 4, 0, 0, 3, 8, 0);
		moveZeros(list1);
		moveZeros(list2);
		moveZeros(list3);
		System.out.println(list1); // [1, 2, 7, 0, 0, 0]
		System.out.println(list2); // [3, 1, 1, 3, 8, 9, 0]
		System.out.println(list3); // [9, 4, 3, 8, 0, 0, 0, 0, 0]

		int[] a = { 1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9 };
		segregate(a);
		System.out.println(Arrays.toString(a)); // [1, 9, 8, 4, 2, 7, 6, 9, 0, 0, 0, 0]

		int[] a1 = { 0, 1, 0, 3, 12 };
		moveZeros(a1);
		System.out.println(Arrays.toString(a1)); // [1, 3, 12, 0, 0]
	}
}
