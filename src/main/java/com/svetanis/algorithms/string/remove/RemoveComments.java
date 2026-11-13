package com.svetanis.algorithms.string.remove;

import java.util.ArrayList;
import java.util.List;

// 722. Remove Comments
//
// Input: a String[] src, one C++ source line per entry. "//" starts a line comment that runs to
// the end of its line; "/*" starts a block comment that runs to the next "*/", over as many
// lines as needed. The first match, scanning left to right, wins, and "/*/" does not close
// itself. Returns the lines with the comments removed, without empty lines; the text before and
// after a block comment that spans lines is joined into one line. On LeetCode: 1 <= src.length <= 100,
// 0 <= src[i].length <= 80, only ASCII characters, no quotes, and every block comment is closed.
//
// The one idea: read the characters left to right with one flag, comment, that says whether a
// block comment is open, and one builder, sb, that holds the line being built. Outside a block
// comment, "/*" opens one, "//" drops the rest of this line, and any other character goes into
// sb. Inside one, only "*/" matters: it closes the comment. The builder is written out only at
// the end of a line, and only when no block comment is open and it is not empty; so a block comment
// that spans lines keeps one builder across them, which joins the text before and after it. If a
// block comment is still open at the end of src, the text before it is lost; LeetCode does not
// allow that input.
//
// Time: O(n) for n the total number of characters -- each is read once, and a two-character token
// is read as one step.
// Space: O(n) -- the builder and the output.

public final class RemoveComments {

	public static List<String> remove(String[] src) {
		boolean comment = false; // COMMENT: a block comment is open
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<>();
		for (String s : src) {
			int n = s.length();
			for (int i = 0; i < n; i++) {
				char c = s.charAt(i);
				boolean end = i + 1 < n && s.charAt(i + 1) == '/'; // the next character is '/'
				if (comment) {
					if (c == '*' && end) {
						comment = false; // CLOSE: "*/"
						i++; // SKIP: the '/' too
					}
				} else {
					boolean star = i + 1 < n && s.charAt(i + 1) == '*'; // the next character is '*'
					if (c == '/' && star) {
						comment = true; // OPEN: "/*"
						i++; // SKIP: the '*' too
					} else if (c == '/' && end) {
						break; // LINE COMMENT: ignore the rest of the line
					} else {
						sb.append(s.charAt(i)); // KEEP: ordinary code
					}
				}
			}
			if (!comment && sb.length() > 0) { // WRITE: a line ends with no block comment open
				list.add(sb.toString());
				sb.setLength(0);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String[] src1 = { "/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;",
				"/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}" };
		System.out.println(remove(src1)); // [int main(), { ,   , int a, b, c;, a = b + c;, }]

		String[] src2 = { "a/*comment", "line", "more_comment*/b" };
		System.out.println(remove(src2)); // [ab]
	}
}
