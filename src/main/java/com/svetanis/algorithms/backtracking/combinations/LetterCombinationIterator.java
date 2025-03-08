package com.svetanis.algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 1286. Iterator for Combination

public final class LetterCombinationIterator {

	private int index;
	private List<String> combinations;

	public LetterCombinationIterator(String s, int len) {
		this.index = 0;
		List<Character> list = new ArrayList<>();
		this.combinations = new ArrayList<>();
		dfs(s, 0, len, list);
	}

	private void dfs(String s, int index, int len, List<Character> list) {
		if (list.size() == len) {
			String joined = list.stream().map(e -> e.toString()).collect(Collectors.joining());
			this.combinations.add(joined);
			return;
		}
		for (int i = index; i < s.length(); i++) {
			char c = s.charAt(i);
			int n = list.size();
			boolean sortDistinct = n > 0 && list.get(n - 1) < c;
			if (n == 0 || sortDistinct) {
				list.add(c);
				dfs(s, index + 1, len, list);
				list.remove(list.size() - 1);
			}
		}
	}

	public String next() {
		String next = this.combinations.get(index);
		index += 1;
		return next;
	}

	public boolean hasNext() {
		return index < this.combinations.size();
	}

	public static void main(String[] args) {
		LetterCombinationIterator ci = new LetterCombinationIterator("abc", 2);
		System.out.println(ci.next()); // ab
		System.out.println(ci.hasNext()); // true
		System.out.println(ci.next()); // ac
		System.out.println(ci.hasNext()); // true
		System.out.println(ci.next()); // bc
		System.out.println(ci.hasNext()); // false
	}
}
