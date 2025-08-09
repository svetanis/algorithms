package com.svetanis.algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 1087. Brace Expansion

public final class BraceExpansion {
	// Time Complexity: O(n + k^n)
	// Space Complexity: O(n^2)

	private List<String[]> parts;
	private List<String> combinations;

	public String[] expand(String s) {
		this.parts = new ArrayList<>();
		parse(s);
		this.combinations = new ArrayList<>();
		dfs(0, new ArrayList<>());
		return combinations.stream().sorted().toArray(String[]::new);
	}

	private void dfs(int index, List<String> list) {
		if (index == parts.size()) {
			combinations.add(String.join("", list));
			return;
		}
		for (String s : parts.get(index)) {
			list.add(s);
			dfs(index + 1, list);
			// backtrack
			list.remove(list.size() - 1);
		}
	}

	private void parse(String s) {
		while (!s.equals("")) {
			if (s.charAt(0) == '{') {
				int end = s.indexOf("}");
				parts.add(s.substring(1, end).split(","));
				s = s.substring(end + 1);
			} else {
				int end = s.indexOf("{");
				if (end != -1) {
					parts.add(new String[] { s.substring(0, end) });
					s = s.substring(end);
				} else {
					parts.add(new String[] { s });
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		BraceExpansion be = new BraceExpansion();
		Print.print(be.expand("a{b,c}")); // ab ac
		Print.print(be.expand("k{a,b}{n,m}")); // kam kan kbm kbn
	}
}
