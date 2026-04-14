package com.svetanis.algorithms.string.substr;

// 1717. Maximum Score From Removing Substrings

public final class MaxScoreRemovingSubStrs {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int maxGain(String s, int x, int y) {
    char firstChar = 'a';
    char secondChar = 'b';
    if (x < y) {
      int temp = x;
      x = y;
      y = temp;
      char tempChar = firstChar;
      firstChar = secondChar;
      secondChar = tempChar;
    }

    int countHight = 0;
    int countLow = 0;
    int totalGain = 0;
    for (char c : s.toCharArray()) {
      if (c == firstChar) {
        countHight++;
      } else if (c == secondChar) {
        if (countHight > 0) {
          totalGain += x;
          countHight--;
        } else {
          countLow++;
        }
      } else {
        totalGain += y * Math.min(countHight, countLow);
        countHight = 0;
        countLow = 0;
      }
    }
    totalGain += y * Math.min(countHight, countLow);
    return totalGain;
  }

  public static void main(String[] args) {
    System.out.println(maxGain("cdbcbbaaabab", 4, 5)); // 19
    System.out.println(maxGain("aabbaaxybbaabb", 5, 4)); // 20
  }
}
