package com.svetanis.algorithms.greedy;

// 1578. Minimum Time to Make Rope Colorful
//
// Input: colors -- colors.charAt(i) is the colour of balloon i on a rope; time[i] -- the seconds
// it takes to remove balloon i. Remove balloons so that no two neighbouring balloons that are
// left have the same colour. Returns the least total time. On LeetCode:
// n == colors.length == time.length, 1 <= n <= 10^5, 1 <= time[i] <= 10^4, colors holds lowercase
// English letters.
//
// The one idea: cut the rope into runs -- stretches of neighbouring balloons of one colour, each
// as long as it goes. Two runs side by side have different colours, so keeping one balloon from
// every run already leaves no clash, and removing a whole run would only cost more. Inside a run,
// every balloon but one has to go: two kept balloons from one run would end up neighbours, with
// only balloons of their own colour between them. So each run keeps exactly one balloon, and the
// cheapest choice is to keep the one that takes longest to remove: the run costs its total time
// minus its largest time. Each run is decided on its own and nothing later changes it.
//
// The two methods: minCost finds each run with two indices and adds total - largest;
// minCostSimple compares each balloon with the survivor just before it, removes the cheaper of
// the two, and writes the survivor's time into time[i], so the caller's array comes back changed.
//
// Time: O(n) -- both methods; in minCost right only moves forward and left jumps to right, so
// each balloon is read once.
// Space: O(1) for minCost; O(n) for minCostSimple -- colors.toCharArray() copies the string.

public final class MinTimeToMakeRopeColorful {

	public static int minCostSimple(String colors, int[] time) {
		int total = 0;
		char[] chars = colors.toCharArray(); // COPY of the colours: O(n)
		for (int i = 1; i < time.length; i++) { // WALK: balloon i against the survivor before it
			if (chars[i] == chars[i - 1]) { // CLASH: same colour as the survivor
				total += Math.min(time[i], time[i - 1]); // REMOVE the cheaper of the two
				time[i] = Math.max(time[i], time[i - 1]); // CARRY the survivor's time to slot i -- writes into the caller's array
			}
		}
		return total; // RETURN
	}

	public static int minCost(String colors, int[] time) {
		int total = 0;
		int n = time.length;
		for (int left = 0, right = 0; left < n; left = right) { // RUN: left is its first balloon; the next run starts at right
			right = left;
			int sum = 0; // SUM of the run's times
			int max = 0; // MAX: the balloon that stays
			while (right < n && colors.charAt(right) == colors.charAt(left)) { // EXTEND while the colour holds
				sum += time[right];
				max = Math.max(max, time[right]);
				right++;
			}
			if (right - left > 1) { // REMOVE all of the run but its most expensive balloon
				total += sum - max;
			}
		}
		return total; // RETURN
	}

	public static void main(String[] args) {
		int[] time1 = { 1, 2, 3, 4, 5 };
		System.out.println(minCost("abaac", time1)); // 3
		System.out.println(minCostSimple("abaac", time1)); // 3

		int[] time2 = { 1, 2, 3 };
		System.out.println(minCost("abc", time2)); // 0
		System.out.println(minCostSimple("abc", time2)); // 0

		int[] time3 = { 1, 2, 3, 4, 1 };
		System.out.println(minCost("aabaa", time3)); // 2
		System.out.println(minCostSimple("aabaa", time3)); // 2

		int[] time4 = { 3, 5, 10, 7, 5, 3, 5, 5, 4, 8, 1 };
		System.out.println(minCost("aaabbbabbbb", time4)); // 26
		System.out.println(minCostSimple("aaabbbabbbb", time4)); // 26
	}
}
