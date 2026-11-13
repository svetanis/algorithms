package com.svetanis.algorithms.string.encode;

import java.util.HashMap;
import java.util.Map;

// 535. Encode and Decode TinyURL
//
// Input: encode(longUrl) returns a short URL; decode(shortUrl) returns the long URL it was made
// from. Any scheme is accepted as long as decode(encode(url)) gives url back. On LeetCode:
// 1 <= url.length <= 10^4, url is a valid URL.
//
// The one idea: the short URL's last part is a key, and a map from key to long URL remembers
// every pair. The key is a counter, so two encodes never share a key -- the same long URL encoded
// twice gets two short URLs, both of which decode correctly. decode cuts the key off after the
// last '/' and looks it up.
//
// Time: O(L) per call -- L = the URL's length, to hash and copy it.
// Space: O(total length of all URLs encoded) -- every long URL is kept in the map.

public final class TinyUrl {

	private static final String DOMAIN = "https://tinyurl.com/";

	public TinyUrl() {
		this.index = 0;
		this.map = new HashMap<>();
	}

	private int index; // the last key given out
	private Map<String, String> map; // key -> long URL

	public String encode(String longUrl) {
		String key = String.valueOf(++index); // KEY: the next number, never used before
		map.put(key, longUrl); // REMEMBER: key -> long URL
		return DOMAIN + key;
	}

	public String decode(String shortUrl) {
		int index = shortUrl.lastIndexOf('/') + 1;
		String key = shortUrl.substring(index); // KEY: everything after the last '/'
		return map.get(key); // LOOK UP: the long URL
	}

	public static void main(String[] args) {
		String url = "https://leetcode.com/problems/design-tinyurl";
		TinyUrl tiny = new TinyUrl();
		String encoded = tiny.encode(url);
		System.out.println(tiny.decode(encoded)); // https://leetcode.com/problems/design-tinyurl
	}
}
