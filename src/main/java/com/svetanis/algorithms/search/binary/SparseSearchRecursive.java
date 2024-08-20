package com.svetanis.algorithms.search.binary;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.google.common.base.Optional;

// given a sorted array of strings which 
// is interspersed with empty strings,
// find the location of a given string

public final class SparseSearchRecursive {

	public static Optional<Integer> search(String[] a, String str) {
		int n = a.length;
    if (isEmpty(str)) {
      for (int i = 0; i < n; ++i) {
        if (isEmpty(a[i])) {
          return of(i);
        }
      }
    }
		int index = sparse(a, 0, n - 1, str);
    return index == -1 ? absent() : of(index);
	}

	private static int sparse(String[] a, int low, int high, String str) {
		// Time Complexity: O(log n)

		if (low > high) {
			return -1;
		}

		int mid = middle(a, low, high);
		if (mid == -1) {
			return -1;
		}

		int result = a[mid].compareTo(str); 
		
		if (result == 0) {
			return mid;
		} else if (result < 0) {
			return sparse(a, mid + 1, high, str);
		} else {
			return sparse(a, low, mid - 1, str);
		}
	}

	private static int middle(String[] a, int low, int high) {
		int mid = low + (high - low) / 2;
		if (isBlank(a[mid])) {
			int left = mid - 1;
			int right = mid + 1;

			while (true) {
				if (left < low && right > high) {
					return -1;
				}
				if (left >= low && isNotBlank(a[left])) {
					mid = left;
					break;
				} else if (right <= high && isNotBlank(a[right])) {
					mid = right;
					break;
				}
				left--;
				right++;
			}
		}
		return mid;
	}

	public static void main(String[] args) {
    String[] a1 = { "at", "", "", "", "ball", "", "", "car", "", "", "dad", "", "" };
    String[] a2 = { "at", "", "", "", "", "ball", "car", "", "", "dad", "", "" };
    String[] a3 = { "apple", "", "", "banana", "", "", "", "carrot", "duck", "", "", "eel", "", "flower" };

    System.out.println(search(a1, "ball"));
    System.out.println(search(a1, ""));
    System.out.println(search(a2, "ballcar"));
    System.out.println(search(a3, "duck"));
    System.out.println(search(a3, "ac"));
	}
}
