package com.svetanis.algorithms.string.remove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1233. Remove Sub-Folders from the Filesystem

public final class RemoveSubFolders {

	public static List<String> removeSubfolders(String[] folders) {
		Arrays.sort(folders);
		List<String> list = new ArrayList<>();
		String parent = "";
		for (String folder : folders) {
			if (parent.isEmpty() || !folder.startsWith(parent + "/")) {
				list.add(folder);
				parent = folder;
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String[] f1 = { "/a", "/a/b", "/c/d", "/c/d/e", "/c/f" };
		System.out.println(removeSubfolders(f1)); // ["/a","/c/d","/c/f"]

		String[] f2 = { "/a", "/a/b/c", "/a/b/d" };
		System.out.println(removeSubfolders(f2)); // ["/a"]

		String[] f3 = { "/a/b/c", "/a/b/ca", "/a/b/d" };
		System.out.println(removeSubfolders(f3)); // ["/a/b/c","/a/b/ca","/a/b/d"]
	}
}
