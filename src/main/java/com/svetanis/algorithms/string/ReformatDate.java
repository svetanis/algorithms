package com.svetanis.algorithms.string;

// 1507. Reformat Date

public final class ReformatDate {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static String reformat(String date) {
		String[] parts = date.split(" ");
		String months = " JanFebMarAprMayJunJulAugSepOctNovDec";
		int day = Integer.parseInt(parts[0].substring(0, parts[0].length() - 2));
		int month = 1 + months.indexOf(parts[1]) / 3;
		return String.format("%s-%02d-%02d", parts[2], month, day);
	}

	public static void main(String[] args) {
		System.out.println(reformat("20th Oct 2052")); // 2052-10-20
		System.out.println(reformat("6th Jun 1933")); // 1933-06-06
		System.out.println(reformat("26th May 1960")); // 1960-05-26
	}
}
