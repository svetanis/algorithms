package com.svetanis.algorithms.string.substr;

// 1717. Maximum Score From Removing Substrings
//
// Input: a string s, and two prices x and y. You may remove the substring "ab" and gain x, or
// remove the substring "ba" and gain y, any number of times, in any order; after a removal the
// two sides of the gap join up. Returns the largest total gain. On LeetCode: 1 <= s.length <= 10^5,
// 1 <= x, y <= 10^4, lowercase letters.
//
// The one idea: remove the higher-paying pair first, wherever it can be removed, and then remove
// the lower-paying pair from what is left. Say "ab" pays more (otherwise swap the roles of a and
// b, which the first lines do, so firstChar + secondChar is always the higher pair). A letter that
// is neither a nor b can never join a pair, so it cuts s into independent blocks and the leftovers
// are settled at every cut. Inside a block, walk left to right: countHight counts the firstChars
// waiting for a partner (the stack of a stack solution holds only that one kind of letter, so a
// counter is enough). A secondChar with a waiting firstChar removes the high pair at once; a
// secondChar with none waiting is left over and counted in countLow. What is left in a block is
// secondChars then firstChars -- the shape of the low pair -- so the low pair can be removed
// min(countHight, countLow) times. All together that is min(#a, #b) removals, the most possible,
// with as many as possible at the higher price.
//
// Time: O(n) -- one pass.
// Space: O(n) -- toCharArray copies the string; the counters are O(1). The gain is at most
// 10^5 / 2 * 10^4 = 5 * 10^8, which fits an int.

public final class MaxScoreRemovingSubStrs {

  public static int maxGain(String s, int x, int y) {
    char firstChar = 'a';
    char secondChar = 'b';
    if (x < y) {
      int temp = x;
      x = y;
      y = temp;
      char tempChar = firstChar;
      firstChar = secondChar;
      secondChar = tempChar; // SWAP: now firstChar + secondChar is the higher-paying pair, price x
    }

    int countHight = 0; // firstChars waiting for a secondChar
    int countLow = 0; // secondChars with nothing to pair with
    int totalGain = 0;
    for (char c : s.toCharArray()) {
      if (c == firstChar) {
        countHight++; // WAIT: a possible high pair
      } else if (c == secondChar) {
        if (countHight > 0) {
          totalGain += x; // TAKE: remove the high pair
          countHight--;
        } else {
          countLow++; // LEFT OVER: no firstChar before it
        }
      } else {
        totalGain += y * Math.min(countHight, countLow); // CUT: the block ends; remove its low pairs
        countHight = 0;
        countLow = 0;
      }
    }
    totalGain += y * Math.min(countHight, countLow); // CUT: the last block ends at the end of s
    return totalGain;
  }

  public static void main(String[] args) {
    System.out.println(maxGain("cdbcbbaaabab", 4, 5)); // 19
    System.out.println(maxGain("aabbaaxybbaabb", 5, 4)); // 20
  }
}
