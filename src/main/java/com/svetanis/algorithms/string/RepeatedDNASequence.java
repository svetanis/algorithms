package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 187. Repeated DNA Sequence

public final class RepeatedDNASequence {
	// Time Complexity: O(n)

	public static List<String> dna(String s) {
		List<String> list = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i <= s.length() - 10; i++) {
			String ss = s.substring(i, i + 10);
			map.put(ss, map.getOrDefault(ss, 0) + 1);
			if(map.get(ss) == 2) {
				list.add(ss);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(dna("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT")); // ["AAAAACCCCC","CCCCCAAAAA"]
		System.out.println(dna("AAAAAAAAAAAAA")); // ["AAAAAAAAAA"]
	}
}
