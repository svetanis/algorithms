package com.svetanis.algorithms.backtracking.additionalstates;

// 2375. Construct Smallest Number From DI String

public final class SmallestNumFromDIStringSimple {

	public String smallestNum(String s) {
		int n = s.length();
		int index = 0;
		int[] a = new int[n + 1];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= n; i++) {
			a[index++] = i + 1;
			if (i == n || s.charAt(i) == 'I') {
				while (index > 0) {
					sb.append(a[--index]);
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		SmallestNumFromDIStringSimple sms = new SmallestNumFromDIStringSimple();
		System.out.println(sms.smallestNum("IIIDIDDD")); // 123549876
		SmallestNumFromDIStringSimple sms2 = new SmallestNumFromDIStringSimple();
		System.out.println(sms2.smallestNum("DDD")); // 4321
	}
}
