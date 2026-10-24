package com.svetanis.algorithms.greedy;

// 860. Lemonade Change
//
// Input: bills[i] -- the bill customer i pays with, in the order they stand in line. Lemonade
// costs $5, the stand starts with no change, and every customer must get exact change. Returns
// whether every customer gets it. On LeetCode: 1 <= bills.length <= 10^5, bills[i] is 5, 10 or 20.
//
// The one idea: only $5 and $10 bills are ever handed back as change, so those are the only two
// counts the walk carries. A $20 needs $15 back: a $10 and a $5, or three $5s. Try the $10 first:
// a $5 helps every later customer who needs change, a $10 only a later $20, so keeping the $5s
// never loses a customer the other choice could have served.
//
// This version checks that the bills are there BEFORE handing them over, so a count never goes
// below zero. LemonadeChange, beside it, hands the change over first and then checks fives < 0;
// the two make the same choices and agree on every legal input.
//
// Time: O(n) -- one pass. Space: O(1) -- two counters.

public final class LemonadeChangeCheckFirst {

	public static boolean lemonadeChange(int[] bills) {
		int fives = 0; // CARRY: the only two bills that ever come back out
		int tens = 0;
		for (int bill : bills) {
			if (bill == 5) {
				fives += 1; // TAKE: nothing owed
			} else if (bill == 10) {
				if (fives < 1) {
					return false; // FAIL: no $5 to give
				}
				fives -= 1; // GIVE $5
				tens += 1; // TAKE the $10
			} else {
				if (tens > 0 && fives > 0) { // CHOOSE: $10 + $5 first -- keeps the $5s
					tens -= 1;
					fives -= 1;
				} else if (fives >= 3) { // only then three $5
					fives -= 3;
				} else {
					return false; // FAIL: $15 cannot be made
				}
			}
		}
		return true; // DONE: everyone got change
	}

	public static void main(String[] args) {
		int[] bills1 = { 5, 5, 5, 10, 20 };
		System.out.println(lemonadeChange(bills1)); // true
		int[] bills2 = { 5, 5, 10, 10, 20 };
		System.out.println(lemonadeChange(bills2)); // false
		int[] bills3 = { 5, 5, 5, 5, 10, 20, 10 };
		System.out.println(lemonadeChange(bills3)); // true -- three $5 at the $20 would fail the last $10
	}
}
