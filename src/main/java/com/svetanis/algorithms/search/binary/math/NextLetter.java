package com.svetanis.algorithms.search.binary.math;

// Given an array of lowercase letters sorted in ascending order, 
// find the smallest letter in the given array greater than a given ‘key’.
// Assume the given array is a circular list, 
// which means that the last letter is assumed to be connected with the first letter. 
// This also means that the smallest letter in the given array is greater 
// than the last letter of the array and is also the first letter of the array.
// Write a function to return the next letter of the given ‘key’.

public final class NextLetter {

  public static char binary(char[] a, char k) {
    int n = a.length;
    int start = 0;
    int end = n - 1;

    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (k < a[mid]) {
        end = mid - 1;
      } else { // k >= a[mid]
        start = mid + 1;
      }
    }
    return a[start % n];
  }

  public static void main(String[] args) {
    char[] a = { 'a', 'c', 'f', 'h' };
    System.out.println(binary(a, 'f'));
    System.out.println(binary(a, 'b'));
    System.out.println(binary(a, 'm'));
    System.out.println(binary(a, 'h'));
  }
}
