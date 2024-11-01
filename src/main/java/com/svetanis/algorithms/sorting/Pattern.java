package com.svetanis.algorithms.sorting;

public final class Pattern implements Comparable<Pattern> {

	private final int freq;
	private final String key;

	public Pattern(String key, int freq) {
		this.freq = freq;
		this.key = key;
	}

	public int getFreq() {
		return freq;
	}

	public String getKey() {
		return key;
	}

	@Override
	public int compareTo(Pattern other) {
		if (this.freq == other.freq) {
			return this.key.compareTo(other.key);
		}
		return other.freq - this.freq;
	}
}
