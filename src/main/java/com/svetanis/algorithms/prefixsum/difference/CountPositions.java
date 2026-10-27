package com.svetanis.algorithms.prefixsum.difference;

// 2237. Count Positions on Street With Required Brightness
//
// Input: n -- the street has positions 0 to n - 1; lights[i] = [position, range] -- a lamp that
// lights every position from position - range to position + range, both ends included, cut off
// at the ends of the street; requirement[p] -- the brightness position p needs. The brightness of
// a position is the number of lamps that light it. Returns how many positions get at least what
// they need. On LeetCode: 1 <= n <= 10^5, 1 <= lights.length <= 10^5, 0 <= position < n,
// 0 <= range <= 10^5, requirement.length == n, 0 <= requirement[p] <= 10^5. The fixed array in
// meetRequirement relies on n staying below 100010.
//
// The one idea: one cell line[p] holds the change in brightness at position p: +1 where a lamp's
// stretch begins, -1 at right end + 1, because the right end itself is still lit. A stretch that
// begins left of 0 is moved to 0, since from the first position on the lamp is already on. Adding
// the cells from left to right gives the brightness at each position.
//
// The two methods return the same answer and differ only in how they keep the -1 inside the
// array: mr sizes the array to the street and skips a -1 that would land past it;
// meetRequirement cuts the stretch at n - 1 first, so its -1 lands at n at most, inside a fixed
// array of 100010 cells.
//
// Time: O(n + m) for mr -- two marks per lamp (m lamps), then one pass over the street;
// O(m + 100010) for meetRequirement -- the same, plus allocating its fixed array.
// Space: O(n) for mr, O(100010) for meetRequirement -- the mark array.

public final class CountPositions {

	public static int mr(int n, int[][] lights, int[] requirement) {
		int[] line = new int[n]; // MARKS: one cell per position
		for (int[] light : lights) {
			int pos = light[0], range = light[1];
			int left = Math.max(0, pos - range); // CUT: the street starts at 0
			line[left]++; // START
			if (pos + range + 1 < n) { // STOP: a stretch reaching the last position needs no -1
				line[pos + range + 1]--;
			}
		}
		int sum = 0;
		int count = 0;
		for (int i = 0; i < n; i++) { // WALK
			sum += line[i]; // brightness at position i
			if (sum >= requirement[i]) { // COUNT: bright enough
				count++;
			}
		}
		return count; // RETURN
	}

	public static int meetRequirement(int n, int[][] lights, int[] requirement) {
		int[] line = new int[100010]; // MARKS: fixed size, n < 100010
		for (int[] light : lights) {
			int left = Math.max(0, light[0] - light[1]); // CUT: the street starts at 0
			int right = Math.min(n - 1, light[0] + light[1]); // CUT: and ends at n - 1
			line[left]++; // START
			line[right + 1]--; // STOP: right is at most n - 1, so this cell is at most n
		}
		int count = 0;
		int sum = 0;
		for (int i = 0; i < n; i++) { // WALK
			sum += line[i]; // brightness at position i
			if (sum >= requirement[i]) { // COUNT: bright enough
				count++;
			}
		}
		return count; // RETURN
	}

	public static void main(String[] args) {
		int[][] lights = { { 1, 2 }, { 3, 1 } };
		int[] requirements = { 1, 2, 1, 1, 2 };
		System.out.println(meetRequirement(5, lights, requirements)); // 3
		System.out.println(mr(5, lights, requirements)); // 3
	}
}
