package com.svetanis.algorithms.string.permutation;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.svetanis.algorithms.string.permutation.NextHigherPermutation.nextHigher;
import static java.lang.String.valueOf;
import static java.util.Arrays.sort;

import com.google.common.base.Optional;

public final class KthPermutationSorted {

  public static Optional<String> kPermutation(String str, int k) {

    char[] chars = str.toCharArray();
    sort(chars);

    if (k == 1) {
      return of(str);
    }

    int count = 1;
    while (count < k && nextHigher(chars)) {
      count++;
      if (count == k) {
        return of(valueOf(chars));
      }
    }
    return absent();
  }

  public static void main(String[] args) {
    String str = "ABCD"; // ACBD
    System.out.println(kPermutation(str, 3));
  }
}
