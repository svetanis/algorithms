package com.svetanis.algorithms.backtracking.additionalstates;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.List;

// All expressions that reach a target -- not a LeetCode problem; it is 282. Expression Add
// Operators with "*", "+" and no operator instead of "+", "-" and "*".
//
// Input: str -- a string of digits, k -- a target. Between each pair of neighbouring digits put
// "" (the two digits join into one number), "*" or "+". Returns every resulting expression whose
// value is k, with "*" binding tighter than "+". A number may start with 0 ("05" is 5), unlike
// in 282. Assumes 1 <= str.length() <= 9: a number then has at most 9 digits and no number,
// product or sum overflows an int; with 10 digits parseInt throws.
//
// The one idea: one call decides the next number -- the digits from index to i -- and the
// operator in front of it. curr is the value of everything chosen so far and prev is the last
// term of it (the number itself, or a product of numbers). After a "+" the new value is
// curr + num and the last term is num. After a "*" the last term prev must be multiplied, not
// added to: take it out of curr and put prev * num back, curr - prev + prev * num, and the last
// term becomes prev * num. That one number prev is all the state "*" needs. The first number has
// no operator in front of it.
//
// Time: O(3^(n - 1) * n) -- every gap between digits takes one of three choices, and each
// expression is a string of up to 2n characters built on the way.
// Space: O(n^2) -- the recursion is n deep and each level holds its own copy of the string, up to
// 2n characters, besides the answers.
//
// Siblings: ExpressionAddOperators is 282 itself -- "+", "-" and "*", no number with a leading 0.

public final class AllExpressionsGivenTarget {

  public static List<String> generate(String str, int k) {
    List<String> list = new ArrayList<>();
    generate(str, "", k, 0, 0, 0, list);
    return list;
  }

  private static void generate(String in, String out, int k, int index, int curr, int prev, List<String> list) {

    int n = in.length();

    if (index == n) { // BASE: every digit is used
      if (curr == k) { // RECORD: only an expression that reaches the target
        list.add(out);
      }
      return;
    }

    for (int i = index; i < n; i++) { // TAKE: the next number is the digits index..i

      String substr = in.substring(index, i + 1);
      int num = parseInt(substr);

      if (index == 0) { // FIRST number: no operator in front, it is both the value and the last term
        generate(in, out + substr, k, i + 1, num, num, list);
      } else {
        generate(in, out + "+" + substr, k, i + 1, curr + num, num, list); // ADD: a new term
        generate(in, out + "*" + substr, k, i + 1, curr - prev + prev * num, prev * num, list); // MULTIPLY: grow the last term
      }
    }
  }

  public static void main(String[] args) {
    String str = "222";
    printLines(generate(str, 24)); // 2+22 then 22+2

    String str2 = "234";
    printLines(generate(str2, 24)); // 2*3*4

  }

  private static void printLines(List<String> list) {
    for (String s : list) {
      System.out.println(s);
    }
    System.out.println();
  }
}
