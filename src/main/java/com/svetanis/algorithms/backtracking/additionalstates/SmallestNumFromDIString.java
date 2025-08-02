package com.svetanis.algorithms.backtracking.additionalstates;

// 2375. Construct Smallest Number From DI String

public final class SmallestNumFromDIString {

	private String smallest;

	public String smallestNum(String s) {
		boolean[] visited = new boolean[10];
		StringBuilder sb = new StringBuilder();
		dfs(0, s, visited, sb);
		return smallest;
	}

	private void dfs(int index, String s, boolean[] visited, StringBuilder sb) {
		if (smallest != null) {
			return;
		}
		if (index == s.length() + 1) {
			this.smallest = sb.toString();
			return;
		}
		for (int num = 1; num <= 9; num++) {
			if (!visited[num]) {
				boolean inc = index > 0 && s.charAt(index - 1) == 'I' && num < sb.charAt(index - 1) - '0';
				boolean dec = index > 0 && s.charAt(index - 1) == 'D' && num > sb.charAt(index - 1) - '0';
				if (inc || dec) {
					continue;
				}
				sb.append(num);
				visited[num] = true;
				dfs(index + 1, s, visited, sb);
				visited[num] = false;
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	public static void main(String[] args) {
		SmallestNumFromDIString sms = new SmallestNumFromDIString();
		System.out.println(sms.smallestNum("IIIDIDDD")); // 123549876
		SmallestNumFromDIString sms2 = new SmallestNumFromDIString();
		System.out.println(sms2.smallestNum("DDD")); // 4321
	}
}
