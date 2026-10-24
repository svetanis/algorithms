package com.svetanis.algorithms.greedy;

import java.util.HashMap;
import java.util.Map;

// 860. Lemonade Change
//
// Input: bills[i] -- the bill customer i pays with, in the order they stand in line. Lemonade
// costs $5, the stand starts with no change, and every customer must get exact change. Returns
// whether every customer gets it. On LeetCode: 1 <= bills.length <= 10^5, bills[i] is 5, 10 or 20.
//
// The one idea: only $5 and $10 bills are ever handed back as change; a $20 is never given back.
// A $10 needs one $5 back -- no choice. A $20 needs $15 back: a $10 and a $5, or three $5s. Use
// the $10 whenever there is one: a $5 can pay for any later customer, a $10 helps only a later
// $20, so spending the $10 now keeps the more useful bills. That choice never has to be undone,
// and the first customer who cannot be paid ends the walk.
//
// The two methods make the same choices. lemonadeChange counts the $5s and $10s in two ints and
// lets a switch pick the change; lemonadeChange2 keeps the counts in a map keyed by bill and pays
// change out in $5 steps after taking a $10 for a $20 when it can. LemonadeChangeCheckFirst,
// beside this file, is the same walk written to check each bill is there before handing it over.
//
// Time: O(n) -- one pass; lemonadeChange2's inner loop pays at most three $5s.
// Space: O(1) -- two counters, or a map with at most three keys.
//
// A bill that is not 5, 10 or 20 -- which LeetCode rules out -- falls through lemonadeChange's
// switch untouched, so the two methods can disagree on it.

public final class LemonadeChange {

	public static boolean lemonadeChange(int[] bills) {
		int fives = 0;
		int tens = 0;
		for (int bill : bills) {
			switch (bill) {
			case 5:
				fives++; // KEEP: no change owed
				break;
			case 10:
				tens++;
				fives--; // PAY $5 back
				break;
			case 20:
				if (tens > 0) { // PAY $10 + $5 when there is a $10
					tens--;
					fives--;
				} else {
					fives -= 3; // otherwise PAY three $5s
				}
				break;
			}
			if (fives < 0) { // CHECK: the change just paid did not exist
				return false;
			}
		}
		return true; // RETURN: everyone got change
	}

	public static boolean lemonadeChange2(int[] bills) {
		Map<Integer, Integer> map = new HashMap<>(); // bill -> how many are in the till
		for (int bill : bills) {
			int change = bill - 5;
			int tens = map.getOrDefault(10, 0);
			if (change == 15 && tens > 0) { // PAY a $10 first when it is a $20 and there is one
				change -= 10;
				map.merge(10, -1, Integer::sum);
			}
			while (change > 0) { // PAY the rest in $5s
				int fives = map.getOrDefault(5, 0);
				if (fives == 0) { // CHECK: no $5 left
					return false;
				}
				change -= 5;
				map.merge(5, -1, Integer::sum);
			}
			map.merge(bill, 1, Integer::sum); // KEEP the customer's bill
		}
		return true; // RETURN: everyone got change
	}

	public static void main(String[] args) {
		int[] bills1 = { 5, 5, 5, 10, 20 };
		System.out.println(lemonadeChange(bills1)); // true
		int[] bills2 = { 5, 5, 10, 10, 20 };
		System.out.println(lemonadeChange(bills2)); // false
		int[] bills3 = { 5, 5, 5, 10, 5, 5, 10, 20, 20, 20 };
		System.out.println(lemonadeChange(bills3)); // false
	}
}
