package com.svetanis.algorithms.string.palindrome;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.string.palindrome.PalindromeIterative.isPalindrome;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLists;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class AllPalindromicPartitions {

  // Time Complexity: O(n*2^(n - 1))

  public static ImmutableList<ImmutableList<String>> partitions(String str) {
    List<String> list = newArrayList();
    List<ImmutableList<String>> lists = newArrayList();
    partitions(str, 0, list, lists);
    return newList(lists);
  }

  private static void partitions(String str, int index, List<String> list, List<ImmutableList<String>> lists) {
    int n = str.length();
    if (index >= n) {
      lists.add(newList(list));
      return;
    }

    for (int i = index; i < n; i++) {
      if (isPalindrome(str, index, i)) {
        list.add(str.substring(index, i + 1));
        partitions(str, i + 1, list, lists);
        list.remove(list.size() - 1);
      }
    }
  }

  public static void main(String[] args) {
    String str1 = "nitin";
    printLists(partitions(str1));

    String str2 = "abab";
    printLists(partitions(str2));
  }
}