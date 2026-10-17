package com.svetanis.algorithms.twopointers;

// 277. Find the Celebrity
//
// Input: n people labelled 0 to n - 1, and a function knows(a, b) that says whether
// a knows b. A celebrity is known by every other person and knows none of them.
// Return: the celebrity's label, or -1 if there is none. There is at most one: two
// celebrities would each have to know the other and not know the other.
//
// The one idea: one call to knows rules out one of two people. If the candidate
// knows the guest, the candidate is not the celebrity (a celebrity knows nobody), so
// the guest becomes the candidate; if not, the guest is not the celebrity (everyone
// knows the celebrity). After one pass every person except the candidate has been
// ruled out. That proves nobody else can be the celebrity, but not that the
// candidate is one, so a second pass checks the candidate against everybody.
//
// Sibling: CelebrityPointers -- the same two passes, but the first walks two pointers
//   inward from the ends, and it returns an Optional instead of -1.
//
// Time: O(n) -- at most 3n calls to knows: n - 1 in the first pass, 2n in the second.
// Space: O(1).
// On LeetCode, knows() comes from the parent class and there is no constructor; here
// the matrix stands in for it.

public class Celebrity {

	private final int[][] graph;               // graph[a][b] == 1 when a knows b

	public Celebrity(int[][] graph) {
		this.graph = graph;
	}

	public int celebrity(int n) {
		int candidate = candidate(n);          // PASS 1: the only person not ruled out
		return verify(n, candidate);           // PASS 2: check that person against everyone
	}

	private int candidate(int n) {
		int candidate = 0;                     // START: person 0 is the candidate
		for (int guest = 1; guest < n; guest++) {
			if (knows(candidate, guest)) {
				candidate = guest;             // DROP the candidate: a celebrity knows nobody
			}                                  // otherwise DROP the guest: everyone knows a celebrity
		}
		return candidate;
	}

	private int verify(int n, int candidate) {
		for (int i = 0; i < n; i++) {
			boolean knowsSomeone = knows(candidate, i);
			boolean notKnown = !knows(i, candidate);
			if ((i != candidate) && (knowsSomeone || notKnown)) {
				return -1;                     // RETURN -1: either one breaks the definition
			}
		}
		return candidate;                      // FOUND: knows nobody, known by everybody
	}

	private boolean knows(int a, int b) {
		return graph[a][b] == 1;
	}

	public static void main(String[] args) {
		int[][] graph1 = { { 1, 1, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
		System.out.println(new Celebrity(graph1).celebrity(3)); // 1

		int[][] graph2 = { { 1, 0, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
		System.out.println(new Celebrity(graph2).celebrity(3)); // -1
	}
}
