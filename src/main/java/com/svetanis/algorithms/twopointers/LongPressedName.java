package com.svetanis.algorithms.twopointers;

// 925. Long Pressed Name
//
// Input: name, the word that was meant, and typed, what came out of a keyboard where a
// key may be held too long and repeat its letter. Both are lowercase and non-empty.
// Return: true when typed could be name with some of its letters repeated extra times.
//
// The one idea: compare RUNS, not letters. Cut both strings into runs of one repeated
// letter. typed is a long press of name exactly when the two have the same runs in the
// same order, letter for letter, and each run in typed is at least as long as its run
// in name. A longer run is a held key; a shorter one is a key that did not register,
// which no long press explains -- only that direction fails. Both strings must run out
// together: a run left over on either side has no partner.
//
// Time: O(n + m) -- both pointers only move forward, a whole run at a time.
// Space: O(1).

public final class LongPressedName {

	public static boolean isLongPressedName(String name, String typed) {
		int n = name.length();
		int m = typed.length();
		int nindex = 0, tindex = 0;        // START: the first run of each
		while (nindex < n && tindex < m) { // STOP: one of them ran out
			char c = name.charAt(nindex);
			char t = typed.charAt(tindex);
			if (c != t) {
				return false;              // COMPARE: the runs must be of the same letter
			}
			int nend = nindex + 1;
			while (nend < n && name.charAt(nend) == c) {
				nend += 1;                 // MEASURE this run in name
			}
			int tend = tindex + 1;
			while (tend < m && typed.charAt(tend) == t) {
				tend += 1;                 // MEASURE the matching run in typed
			}
			if (nend - nindex > tend - tindex) {
				return false;              // COMPARE: typed's run is SHORTER -- impossible
			}
			nindex = nend;
			tindex = tend;                 // MOVE: both jump a whole run, not one letter
		}
		return nindex == n && tindex == m; // RETURN: both must finish together
	}

	public static void main(String[] args) {
		System.out.println(isLongPressedName("alex", "aaleex")); // true
		System.out.println(isLongPressedName("saeed", "ssaaedd")); // false -- the e run got shorter
		System.out.println(isLongPressedName("alex", "aaleexr")); // false -- a run left over in typed
		System.out.println(isLongPressedName("alexa", "aaleex")); // false -- a run left over in name
	}
}
