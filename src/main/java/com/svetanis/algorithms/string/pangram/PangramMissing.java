package com.svetanis.algorithms.string.pangram;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

// You are to write a method getMissingLetters, 
// which takes a String, sentence, and returns all the letters it is missing 

public final class PangramMissing {

  public static String missing(String str, List<String> list) {
    int n = list.size();
    str = str.toLowerCase();
    List<String> missing = newArrayList();
    for (int i = 0; i < n; i++) {
      if (!str.contains(list.get(i))) {
        missing.add(list.get(i));
      }
    }
    return Joiner.on("").join(missing);
  }

  public static void main(String[] args) {
    ImmutableList<String> list = alphabet();
    String str1 = "A quick brown fox jumps over the lazy dog";
    System.out.println(missing(str1, list));

    String str2 = "A slow yellow fox crawls under the proactive dog";
    System.out.println(missing(str2, list));

    String str3 = "Lions, and tigers, and bears, oh my!";
    System.out.println(missing(str3, list));

    String str4 = "";
    System.out.println(missing(str4, list));
  }

  private static ImmutableList<String> alphabet() {
    Set<String> set = newHashSet();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");
    set.add("e");
    set.add("f");
    set.add("g");
    set.add("h");
    set.add("i");
    set.add("j");
    set.add("k");
    set.add("l");
    set.add("m");
    set.add("n");
    set.add("o");
    set.add("p");
    set.add("q");
    set.add("r");
    set.add("s");
    set.add("t");
    set.add("u");
    set.add("v");
    set.add("w");
    set.add("x");
    set.add("y");
    set.add("z");
    return newList(set);
  }
}