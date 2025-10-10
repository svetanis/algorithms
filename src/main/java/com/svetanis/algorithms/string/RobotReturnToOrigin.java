package com.svetanis.algorithms.string;

// 657. Robot Return to Origin

public final class RobotReturnToOrigin {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static boolean judgeCircle(String moves) {
		int x = 0;
		int y = 0;
		for (char c : moves.toCharArray()) {
			if (c == 'U') {
				y += 1;
			} else if (c == 'D') {
				y -= 1;
			} else if (c == 'R') {
				x += 1;
			} else if (c == 'L') {
				x -= 1;
			}
		}
		return x == 0 && y == 0;
	}

	public static void main(String[] args) {
		System.out.println(judgeCircle("UD")); // true
		System.out.println(judgeCircle("LL")); // false
	}
}
