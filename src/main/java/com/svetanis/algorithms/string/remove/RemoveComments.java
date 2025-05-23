package com.svetanis.algorithms.string.remove;

import java.util.ArrayList;
import java.util.List;

// 722. Remove Comments

public final class RemoveComments {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static List<String> remove(String[] src) {
		boolean comment = false;
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<>();
		for (String s : src) {
			int n = s.length();
			for (int i = 0; i < n; i++) {
				char c = s.charAt(i);
				boolean end = i + 1 < n && s.charAt(i + 1) == '/';
				if (comment) {
					if (c == '*' && end) {
						comment = false;
						i++;
					}
				} else {
					boolean star = i + 1 < n && s.charAt(i + 1) == '*';
					if (c == '/' && star) {
						comment = true;
						i++;
					} else if (c == '/' && end) {
						break; // ignore the rest of the line
					} else {
						sb.append(s.charAt(i));
					}
				}
			}
			if (!comment && sb.length() > 0) {
				list.add(sb.toString());
				sb.setLength(0);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String[] src1 = { "/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;",
				"/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}" };
		System.out.println(remove(src1));

		String[] src2 = { "a/*comment", "line", "more_comment*/b" };
		System.out.println(remove(src2)); // ab
	}
}
