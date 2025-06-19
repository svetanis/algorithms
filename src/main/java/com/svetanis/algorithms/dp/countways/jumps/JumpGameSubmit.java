package com.svetanis.algorithms.dp.countways.jumps;

// 55. Jump Game

public final class JumpGameSubmit {
	// Time Complexity: O(n)

	public static boolean canJumpBackward(int[] a) {
		int goal = a.length - 1;
		for (int jump = a.length - 2; jump >= 0; jump--) {
			if(jump + a[jump] >= goal) {
				goal = jump;
			}
		}
		return goal == 0;
	}

	public static boolean canJump(int[] a) {
		int n = a.length;
		int max = 0;
		for (int jump = 0; jump < n; jump++) {
			if (max < jump) {
				return false;
			}
			max = Math.max(max, jump + a[jump]);
		}
		return true;
	}

	public static void main(String[] args) {
		int[] a = { 2, 3, 1, 1, 4 };
		System.out.println(canJumpBackward(a)); // true

		int[] a1 = { 3, 2, 1, 0, 4 };
		System.out.println(canJumpBackward(a1)); // false
	}
}
