package com.svetanis.algorithms.string.reorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

// No LeetCode problem: names in alphabetical order, with a count and the last surname.
//
// Input: lines of the form "first last", one person per line. Returns one line per distinct
// first name, in alphabetical order of the first name, written "first:count,lastSurname" where
// count is the number of different surnames seen with that first name and lastSurname is the
// alphabetically last of them. The same person listed twice counts once.
//
// The one idea: one entry of the map is a first name and the sorted set of surnames seen with it.
// A TreeMap keeps the first names in alphabetical order and a TreeSet keeps each set of surnames
// in alphabetical order without repeats, so the count is the size of the set and the last
// surname is the set's last element. Nothing has to be sorted afterwards.
//
// Time: O(n log n) -- n lines; each goes into a sorted map and a sorted set, O(log n) name
// comparisons each.
// Space: O(n) -- every surname is stored once.

public final class SurnamesByFirstName {

  public static List<String> order(Iterable<String> lines) {
    Map<String, TreeSet<String>> surnames = new TreeMap<>(); // first name -> its surnames, sorted
    for (String line : lines) {
      String[] parts = line.split(" ", 2); // SPLIT: first name, then the rest as the surname
      surnames.computeIfAbsent(parts[0], k -> new TreeSet<>()).add(parts[1]); // RECORD: a repeat is ignored
    }
    List<String> result = new ArrayList<>();
    for (Map.Entry<String, TreeSet<String>> entry : surnames.entrySet()) { // WALK: first names in order
      TreeSet<String> set = entry.getValue();
      result.add(entry.getKey() + ":" + set.size() + "," + set.last()); // RETURN: count and last surname
    }
    return result;
  }

  public static void main(String[] args) {
    List<String> list = Arrays.asList("mark zuckerberg", "tim cook", "mark twain");
    for (String line : order(list)) {
      System.out.println(line); // mark:2,zuckerberg  then  tim:1,cook
    }
    List<String> repeated = Arrays.asList("mark twain", "mark twain");
    System.out.println(order(repeated)); // [mark:1,twain]
  }
}
