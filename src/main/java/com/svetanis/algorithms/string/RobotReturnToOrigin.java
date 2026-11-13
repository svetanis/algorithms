package com.svetanis.algorithms.string;

// 657. Robot Return to Origin
//
// Input: a string of moves, each U, D, L or R (up, down, left, right), by one step each. The
// robot starts at (0, 0). Returns true if it ends at (0, 0). On LeetCode: 1 <= moves.length
// <= 2 * 10^4.
//
// The one idea: only the final position matters, not the path. (x, y) is where the robot is:
// U and D change y by 1, R and L change x by 1. It is back at the origin exactly when the
// moves cancel in both directions, which is x == 0 and y == 0 at the end.
//
// Time: O(n) -- one pass.
// Space: O(1).

public final class RobotReturnToOrigin {

	public static boolean judgeCircle(String moves) {
		int x = 0; // x: steps right minus steps left
		int y = 0; // y: steps up minus steps down
		for (char c : moves.toCharArray()) { // WALK: apply each move
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
		return x == 0 && y == 0; // RETURN: back at the origin
	}

	public static void main(String[] args) {
		System.out.println(judgeCircle("UD")); // true
		System.out.println(judgeCircle("LL")); // false
	}
}
