package com.svetanis.algorithms.bits;

// 201. Bitwise AND of Numbers Range
//
// Input: two ints left and right. Returns left & (left + 1) & ... & right, the AND of every
// number from left to right. On LeetCode: 0 <= left <= right <= 2^31 - 1.
//
// The one idea: a slot of the answer is 1 only if every number in the range has a 1 there. Write
// left and right in binary, one above the other. From the top they agree for a while -- the shared
// prefix -- and then come to the first slot where they differ; right has 1 there and left has 0.
// Counting up from left to right passes from prefix-0-111...1 to prefix-1-000...0, two numbers
// that disagree in that slot and every slot below it, so all of those slots are 0 in the answer.
// The prefix itself is the same in every number of the range. So the answer is the shared prefix
// followed by 0s.
//
// bitwiseAnd removes right's lowest 1 while right is still above left. Right keeps its 1 in the
// first differing slot until every 1 below it is gone, and left has 0 there, so right stays above
// left until it is exactly the prefix followed by 0s -- and that is where the loop stops.
// bitwiseAnd2 shifts both numbers right until they are equal, which throws away the differing
// slots, and shifts the prefix back into place.
//
// Time: O(log right) -- at most 31 passes: bitwiseAnd removes one 1 per pass, bitwiseAnd2 one slot.
// Space: O(1) -- two or three ints.

public final class BitwiseAndInRange201 {

  public static int bitwiseAnd(int left, int right) {
    while (left < right) { // STOP: once right has come down to the prefix, which is <= left
      right = right & (right - 1); // DROP: right's lowest 1, while it is still above left
    }
    return right; // RETURN: the shared prefix, 0s below it
  }

  public static int bitwiseAnd2(int left, int right) {
  	int shift = 0; // COUNT: slots dropped from both ends so far
    while (left < right) { // STOP: equal means only the shared prefix is left
    	left >>= 1; // DROP: the lowest slot of both
    	right >>= 1;
    	shift++;
    }
    return left << shift; // RETURN: the shared prefix, moved back into place
  }

  public static void main(String[] args) {
    System.out.println(bitwiseAnd(5, 7)); // 4
    System.out.println(bitwiseAnd(0, 0)); // 0
    System.out.println(bitwiseAnd(1, 2147483647)); // 0

    System.out.println(bitwiseAnd2(5, 7)); // 4
    System.out.println(bitwiseAnd2(0, 0)); // 0
    System.out.println(bitwiseAnd2(1, 2147483647)); // 0
  }
}
