package com.svetanis.algorithms.string.encode;

import java.util.HashMap;
import java.util.Map;

// 535. Encode and Decode TinyURL

public final class TinyUrl {

	private static final String DOMAIN = "https://tinyurl.com/";

	public TinyUrl() {
		this.index = 0;
		this.map = new HashMap<>();
	}

	private int index;
	private Map<String, String> map;

	public String encode(String longUrl) {
		String key = String.valueOf(++index);
		map.put(key, longUrl);
		return DOMAIN + key;
	}

	public String decode(String shortUrl) {
		int index = shortUrl.lastIndexOf('/') + 1;
		String key = shortUrl.substring(index);
		return map.get(key);
	}

	public static void main(String[] args) {
		String url = "https://leetcode.com/problems/design-tinyurl";
		TinyUrl tiny = new TinyUrl();
		String encoded = tiny.encode(url);
		System.out.println(tiny.decode(encoded));
	}
}
