package com.svetanis.algorithms.string.remove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1233. Remove Sub-Folders from the Filesystem
//
// Input: a String[] of folder paths. A folder is a sub-folder of another if its path starts with
// that folder's path followed by '/'. Returns the folders that are not sub-folders of any other, in
// any order. On LeetCode: 1 <= folder.length <= 4 * 10^4, 2 <= folder[i].length <= 100, every path
// starts with '/', has lowercase letters and '/' only, never ends with '/', and no two are equal.
// The method sorts the array it is given.
//
// The one idea: sort the paths as strings. A folder is then followed directly by all its
// sub-folders, because every sub-folder starts with the folder's path plus '/', and '/' sorts before
// every lowercase letter, so nothing else can come between them. So walk the sorted list and keep
// the last folder kept, parent; a path that starts with parent + "/" is a sub-folder of it and is
// skipped, and any other path is not under anything kept so far, so it is kept and becomes the new
// parent.
//
// Time: O(n * L * log n) for n paths of at most L characters -- the sort makes O(n log n)
// comparisons of up to L characters each; the walk makes n prefix tests of O(L) each.
// Space: O(n * L) -- the output list; the sort itself needs only a few extra references.

public final class RemoveSubFolders {

	public static List<String> removeSubfolders(String[] folders) {
		Arrays.sort(folders); // SORT: a folder comes right before its sub-folders
		List<String> list = new ArrayList<>();
		String parent = "";
		for (String folder : folders) {
			if (parent.isEmpty() || !folder.startsWith(parent + "/")) { // NOT under the last kept folder
				list.add(folder); // KEEP it
				parent = folder; // and it is the folder to test the next ones against
			} // else SKIP: a sub-folder of parent
		}
		return list;
	}

	public static void main(String[] args) {
		String[] f1 = { "/a", "/a/b", "/c/d", "/c/d/e", "/c/f" };
		System.out.println(removeSubfolders(f1)); // [/a, /c/d, /c/f]

		String[] f2 = { "/a", "/a/b/c", "/a/b/d" };
		System.out.println(removeSubfolders(f2)); // [/a]

		String[] f3 = { "/a/b/c", "/a/b/ca", "/a/b/d" };
		System.out.println(removeSubfolders(f3)); // [/a/b/c, /a/b/ca, /a/b/d]
	}
}
