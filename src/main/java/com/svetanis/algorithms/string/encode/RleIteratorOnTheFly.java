package com.svetanis.algorithms.string.encode;


// 900. RLE Iterator
//
// encoding is a run-length encoded sequence: for every even i, the value encoding[i + 1] is
// repeated encoding[i] times, and a count may be 0. next(n) uses up the next n elements of the
// sequence and returns the last one used up. If the sequence runs out before n elements are
// used, it returns -1, and whatever was left is used up all the same.
//
// The sequence is never written out, because a count can be 10^9 and there can be 500 runs.
// The iterator keeps two numbers: index, the position of the count of the run being read
// (0, 2, 4, ...), and count, how many elements of that run are already used up. A call either
// ends inside the current run (count grows by n, and the run's value is returned), or it uses
// up the rest of the run and moves to the next pair, with n reduced by what that run had left.
// A run with count 0 has nothing available, so it is moved past the same way.
//
// -1 is safe as "ran out" here: the statement says every value is non-negative.
//
// Siblings, same problem:
//   RleIteratorExpandQueue  writes every element into a queue first; same answers, but it
//                           holds the whole sequence, so it cannot fit at LeetCode's limits
//   RleIteratorOnTheFly     a position inside the encoding (this file, the version derived
//                           in study)

public final class RleIteratorOnTheFly {
	// Time Complexity: O(1) per call, plus one step per run it moves past; over all calls
	// O(calls + encoding.length), because index only ever moves forward
	// Space Complexity: O(1) beyond the encoding array it keeps a reference to

	private final int[] encoding; // count, value, count, value, ...
	private int index;            // the current pair's count cell: 0, 2, 4, ...
	private int count;            // how many of the current run are used up

	public RleIteratorOnTheFly(int[] encoding) {
		this.encoding = encoding;     // START at the first pair, nothing used
	}

	public int next(int n) {
		while (index < encoding.length) {                  // REPEAT while there is still a pair
			int availableInPair = encoding[index] - count; // LEFT: how many of this run are still there
			if (availableInPair >= n) {                    // STOP: the run has enough for the whole call
				count += n;
				return encoding[index + 1];                // RETURN: this run's value is the last one used
			}
			n -= availableInPair;                          // SKIP: take the rest of this run off n ...
			index += 2;                                    // ... and MOVE to the start of the next pair
			count = 0;
		}
		return -1;                                         // RAN OUT: no pair left
	}

	public static void main(String[] args) {
		RleIteratorOnTheFly rle = new RleIteratorOnTheFly(new int[] { 3, 8, 0, 9, 2, 5 });
		System.out.println(rle.next(2)); // 8
		System.out.println(rle.next(1)); // 8
		System.out.println(rle.next(1)); // 5
		System.out.println(rle.next(2)); // -1

		RleIteratorOnTheFly past = new RleIteratorOnTheFly(new int[] { 2, 8 });
		System.out.println(past.next(3)); // -1, and both 8s are used up
		System.out.println(past.next(1)); // -1
	}
}
