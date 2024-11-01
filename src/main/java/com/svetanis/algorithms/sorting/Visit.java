package com.svetanis.algorithms.sorting;

import com.google.common.base.Joiner;

public final class Visit {

	private final int timestamp;
	private final String name;
	private final String website;

	public Visit(int timestamp, String name, String website) {
		this.timestamp = timestamp;
		this.name = name;
		this.website = website;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public String getName() {
		return name;
	}

	public String getWebsite() {
		return website;
	}

	@Override
	public String toString() {
		return Joiner.on(":").join(name, timestamp, website);
	}
}
