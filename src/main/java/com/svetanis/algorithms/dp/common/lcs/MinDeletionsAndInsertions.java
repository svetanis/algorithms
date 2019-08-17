package com.svetanis.algorithms.dp.common.lcs;

import static com.svetanis.algorithms.dp.common.lcs.LongestCommonSubSeqLenBottomUp.lcs;

import com.svetanis.java.base.Pair;

// Given strings s1 and s2, 
// we need to transform s1 into s2 
// by deleting and inserting characters. 
// Write a function to calculate the count 
// of the min num of deletion and insertion operations

public final class MinDeletionsAndInsertions {

  public static Pair<Integer, Integer> count(String x, String y) {
    int n = x.length();
    int m = y.length();
    int lcs = lcs(x, y);
    int delete = n - lcs;
    int insert = m - lcs;
    return Pair.build(delete, insert);
  }

  public static void main(String[] args) {
    System.out.println(count("abc", "fbc"));
    System.out.println(count("abdca", "cbda"));
    System.out.println(count("passport", "ppsspt"));
  }
}