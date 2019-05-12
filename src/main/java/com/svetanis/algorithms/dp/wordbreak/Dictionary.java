package com.svetanis.algorithms.dp.wordbreak;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class Dictionary {

  public static ImmutableList<String> dictionary() {
    List<String> dict = newArrayList();
    dict.add("i");
    dict.add("am");
    dict.add("iam");
    dict.add("super");
    dict.add("lady");
    dict.add("superlady");
    return newList(dict);
  }

}
