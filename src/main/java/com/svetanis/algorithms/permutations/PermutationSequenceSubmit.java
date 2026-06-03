package com.svetanis.algorithms.permutations;

// 60. Permutation Sequence

public final class PermutationSequenceSubmit {

  public String kPermutation(int n, int k) {
    StringBuilder sb = new StringBuilder();
    boolean[] used = new boolean[n + 1];
    for (int position = 0; position < n; position++) {
      int factorial = 1;
      for (int i = 1; i < n - position; i++) {
        factorial *= i;
      }

      for (int num = 1; num <= n; num++) {
        if (!used[num]) {
          if (k > factorial) {
            k -= factorial;
          } else {
            sb.append(num);
            used[num] = true;
            break;
          }
        }
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    PermutationSequenceSubmit pss = new PermutationSequenceSubmit();
    System.out.println(pss.kPermutation(3, 3)); // 213
    System.out.println(pss.kPermutation(4, 9)); // 2314
    System.out.println(pss.kPermutation(3, 1)); // 123
  }
}
