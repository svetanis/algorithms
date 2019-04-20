package com.svetanis.algorithms.recursive.combinations;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.lang.Integer.parseInt;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class AllExpressionsGivenTarget {

  // Time Complexity: O((3^(n - 1)) * n)
  
  public static ImmutableList<String> generate(String str, int k) {
    List<String> list = newArrayList();
    generate(str, "", k, 0, 0, 0, list);
    return newList(list);
  }

  private static void generate(String in, String out, int k, int index, int curr, int prev, List<String> list) {

    int n = in.length();

    if (index == n) {
      if (curr == k) {
        list.add(out);
      }
      return;
    }

    for (int i = index; i < n; i++) {
      if (i != index && in.charAt(index) == '0') {
        break;
      }

      String substr = in.substring(index, i + 1);
      int num = parseInt(substr);

      if (index == 0) {
        generate(in, out + substr, k, i + 1, num, num, list);
      } else {
        generate(in, out + "+" + substr, k, i + 1, curr + num, num, list);
        generate(in, out + "*" + substr, k, i + 1, curr - prev + prev * num, prev * num, list);
      }
    }
  }

  public static void main(String[] args) {
    String str = "222";
    printLines(generate(str, 24));
    
    String str2 = "234";
    printLines(generate(str2, 24));

  }
}
