# 🎯 LeetCode Solutions - Java

A **curated collection of LeetCode problem solutions** organized by algorithm pattern and difficulty level. Designed as a **study guide** for coding interviews, technical assessments, and algorithm mastery.

**Perfect for:**
- 🎓 Students preparing for technical interviews
- 💼 Job seekers practicing for FAANG interviews
- 🚀 Engineers improving algorithmic thinking
- 📚 Learning fundamental CS concepts through practice

---

## 🗂️ How This Repository is Organized

Solutions are organized by **algorithmic pattern**, not difficulty, because **pattern recognition** is the key to interview success.

Each pattern folder contains **solutions to related LeetCode problems** with:
- ✅ Multiple solution approaches (brute-force → optimal)
- ✅ Time and space complexity analysis
- ✅ Edge cases and test cases
- ✅ Detailed code comments

---

## 📚 Algorithm Patterns (16 Categories)

### 🔄 [Backtracking](src/main/java/com/svetanis/algorithms/backtracking)
**When to use:** Generate combinations, permutations, find all solutions, explore all paths

**Common LeetCode Problems:**
- 46: Permutations
- 47: Permutations II
- 77: Combinations
- 78: Subsets
- 79: Word Search
- 51: N-Queens
- 52: N-Queens II
- 37: Sudoku Solver
- 90: Subsets II

**Key Pattern:** Explore → Try → Backtrack → Try next

**Complexity:** O(N!) to O(2^N) time, O(N) or O(2^N) space

---

### 💾 [Bits & Bitwise Operations](src/main/java/com/svetanis/algorithms/bits)
**When to use:** Optimize space, manipulate individual bits, XOR tricks, count bits

**Common LeetCode Problems:**
- 136: Single Number
- 137: Single Number II
- 260: Single Number III
- 191: Number of 1 Bits
- 231: Power of Two
- 338: Counting Bits
- 29: Divide Two Integers
- 371: Sum of Two Integers
- 389: Find the Difference
- 461: Hamming Distance

**Key Tricks:**
- `n & (n-1)` - Remove rightmost 1 bit
- `n ^ n = 0` - XOR same number = 0
- `n & 1` - Check if odd/even

**Complexity:** O(1) to O(log N) time, O(1) space

---

### 🎯 [Dynamic Programming](src/main/java/com/svetanis/algorithms/dp)
**When to use:** Overlapping subproblems, optimal substructure, optimize recursion with memoization

**Common LeetCode Problems:**
- 70: Climbing Stairs
- 62: Unique Paths
- 63: Unique Paths II
- 64: Minimum Path Sum
- 198: House Robber
- 213: House Robber II
- 300: Longest Increasing Subsequence
- 1143: Longest Common Subsequence
- 72: Edit Distance
- 322: Coin Change
- 518: Coin Change II
- 416: Partition Equal Subset Sum
- 494: Target Sum
- 5: Longest Palindromic Substring
- 10: Regular Expression Matching
- 97: Interleaving String
- 115: Distinct Subsequences
- 131: Palindrome Partitioning

**Two Approaches:**
1. **Top-Down (Memoization)** - Recursion with caching
2. **Bottom-Up (Tabulation)** - Iterative with DP table

**Complexity:** O(N) to O(N³) time, O(N) to O(N²) space

---

### 🤝 [Greedy](src/main/java/com/svetanis/algorithms/greedy)
**When to use:** Local optimal leads to global optimal, choose best option at each step

**Common LeetCode Problems:**
- 121: Best Time to Buy and Sell Stock
- 122: Best Time to Buy and Sell Stock II
- 55: Jump Game
- 45: Jump Game II
- 134: Gas Station
- 455: Assign Cookies
- 452: Minimum Number of Arrows to Burst Balloons
- 435: Non-overlapping Intervals
- 406: Queue Reconstruction by Height
- 763: Partition Labels

**Key Insight:** Proof that greedy choice is optimal is crucial

**Complexity:** O(N) to O(N log N) time, O(1) space usually

---

### 📏 [Intervals](src/main/java/com/svetanis/algorithms/intervals)
**When to use:** Overlapping ranges, scheduling, meeting rooms, calendar events

**Common LeetCode Problems:**
- 56: Merge Intervals
- 57: Insert Interval
- 252: Meeting Rooms
- 253: Meeting Rooms II
- 435: Non-overlapping Intervals
- 1288: Remove Covered Intervals
- 986: Interval List Intersections
- 1109: Corporate Flight Bookings

**Core Technique:**
1. Sort by start time (or end time)
2. Merge/iterate and compare intervals
3. Keep track of endpoints

**Complexity:** O(N log N) time, O(1) space (if no extra array)

---

### 🧮 [Math](src/main/java/com/svetanis/algorithms/math)
**When to use:** Number properties, math formulas, number theory, primes

**Common LeetCode Problems:**
- 7: Reverse Integer
- 9: Palindrome Number
- 50: Pow(x, n)
- 69: Sqrt(x)
- 202: Happy Number
- 204: Count Primes
- 263: Ugly Number
- 264: Ugly Number II
- 343: Integer Break
- 365: Water and Jug Problem
- 1523: Count Odd Numbers in an Interval Range

**Common Techniques:**
- GCD/LCM computation
- Prime checking
- Modular arithmetic
- Power calculation tricks

**Complexity:** O(log N) to O(sqrt(N)) time

---

### 🎲 [Matrix / 2D Array](src/main/java/com/svetanis/algorithms/matrix)
**When to use:** 2D grid problems, matrix transformations, island problems, word search

**Common LeetCode Problems:**
- 48: Rotate Image
- 54: Spiral Matrix
- 59: Spiral Matrix II
- 73: Set Matrix Zeroes
- 200: Number of Islands
- 207: Course Schedule
- 210: Course Schedule II
- 130: Surrounded Regions
- 79: Word Search
- 289: Game of Life
- 1091: Shortest Path in Binary Matrix

**Common Patterns:**
- DFS (Depth-First Search)
- BFS (Breadth-First Search)
- In-place rotation/transformation
- Connected components

**Complexity:** O(M×N) time, O(M×N) or O(1) space

---

### 📊 [Monotonic Stack](src/main/java/com/svetanis/algorithms/monotonicstack)
**When to use:** Next greater/smaller element, largest rectangle, trapping rain water

**Common LeetCode Problems:**
- 496: Next Greater Element I
- 503: Next Greater Element II
- 739: Daily Temperatures
- 42: Trapping Rain Water
- 84: Largest Rectangle in Histogram
- 901: Online Stock Span
- 1019: Next Greater Node In Linked List
- 316: Remove Duplicate Letters
- 1544: Make The String Great

**Why It Works:** O(N) instead of O(N²) naive approach

**Complexity:** O(N) time, O(N) space (for the stack)

---

### 🔗 [Permutations](src/main/java/com/svetanis/algorithms/permutations)
**When to use:** Generate all arrangements, next permutation, permutation sequence

**Common LeetCode Problems:**
- 46: Permutations
- 47: Permutations II
- 31: Next Permutation
- 60: Permutation Sequence
- 1157: Online Majority Element In Subarray

**Two Approaches:**
1. **Backtracking** - Generate all permutations recursively
2. **Lexicographic** - Generate next permutation in order

**Complexity:** O(N! × N) time, O(N!) space

---

### ➕ [Prefix Sum](src/main/java/com/svetanis/algorithms/prefixsum)
**When to use:** Range queries, subarray sum problems, cumulative calculations

**Common LeetCode Problems:**
- 303: Range Sum Query - Immutable
- 304: Range Sum Query 2D - Immutable
- 560: Subarray Sum Equals K
- 325: Maximum Size Subarray Sum Equals K
- 974: Subarray Sums Divisible by K
- 1480: Running Sum of 1d Array
- 1567: Maximum Length of Subarray With Positive Product

**Key Insight:** Precompute prefix sums for O(1) range queries

**Complexity:** O(N) preprocessing, O(1) queries, O(N) space

---

### 🔂 [Recursion](src/main/java/com/svetanis/algorithms/recursive)
**When to use:** Tree/graph traversal, divide-and-conquer, problems with recursive structure

**Common LeetCode Problems:**
- 509: Fibonacci Number
- 206: Reverse Linked List
- 226: Invert Binary Tree
- 100: Same Tree
- 101: Symmetric Tree
- 104: Maximum Depth of Binary Tree
- 110: Balanced Binary Tree
- 112: Path Sum
- 236: Lowest Common Ancestor of a Binary Tree

**Recursion Checklist:**
- ✅ Define base case(s)
- ✅ Define recursive case
- ✅ Ensure progress toward base case
- ✅ Be aware of call stack depth

**Complexity:** O(2^N) to O(N) depending on problem, O(depth) space (call stack)

---

### 🔎 [Search Algorithms](src/main/java/com/svetanis/algorithms/search)
**When to use:** Finding elements, finding boundaries, searching in sorted/rotated arrays

#### **Linear Search**
- Time: O(N), Space: O(1)
- Use: Unsorted data, simple lookup

#### **Binary Search** ⭐
- Time: O(log N), Space: O(1) or O(log N)
- Prerequisites: Sorted array

**Common LeetCode Problems:**
- 704: Binary Search
- 74: Search a 2D Matrix
- 33: Search in Rotated Sorted Array
- 81: Search in Rotated Sorted Array II
- 153: Find Minimum in Rotated Sorted Array
- 154: Find Minimum in Rotated Sorted Array II
- 34: Find First and Last Position of Element in Sorted Array
- 35: Search Insert Position
- 278: First Bad Version
- 4: Median of Two Sorted Arrays

**Binary Search Variants:**
- Find exact match
- Find lower bound (first occurrence)
- Find upper bound (last occurrence)
- Modified binary search (non-standard conditions)

**Complexity:** O(log N) time, O(1) space

---

### 🪟 [Sliding Window](src/main/java/com/svetanis/algorithms/slidingwindow)
**When to use:** Subarray/substring problems, contiguous elements, find maximum/minimum window

**Common LeetCode Problems:**
- 3: Longest Substring Without Repeating Characters
- 76: Minimum Window Substring ⭐
- 209: Minimum Size Subarray Sum
- 424: Longest Repeating Character Replacement
- 567: Permutation in String
- 438: Find All Anagrams in a String
- 159: Longest Substring with At Most Two Distinct Characters
- 340: Longest Substring with At Most K Distinct Characters
- 904: Fruit Into Baskets
- 1004: Max Consecutive Ones III

**Pattern:**
```
while (right < n) {
    expand right
    while (condition violated) {
        contract left
    }
    update answer
}
```

**Complexity:** O(N) time, O(K) space (K = window size or charset)

---

### 🔀 [Sorting Algorithms](src/main/java/com/svetanis/algorithms/sorting)
**When to use:** Arrange data in order, preprocessing for other algorithms

**Algorithms Implemented:**

| Algorithm | Time (Avg) | Time (Worst) | Space | Stable | In-place |
|-----------|-----------|-------------|-------|--------|----------|
| Bubble Sort | O(N²) | O(N²) | O(1) | Yes | Yes |
| Selection Sort | O(N²) | O(N²) | O(1) | No | Yes |
| Insertion Sort | O(N²) | O(N²) | O(1) | Yes | Yes |
| Merge Sort | O(N log N) | O(N log N) | O(N) | Yes | No |
| Quick Sort | O(N log N) | O(N²) | O(log N) | No | Yes |
| Heap Sort | O(N log N) | O(N log N) | O(1) | No | Yes |
| Counting Sort | O(N+K) | O(N+K) | O(K) | Yes | No |
| Radix Sort | O(NK) | O(NK) | O(N+K) | Yes | No |

**Common LeetCode Uses:**
- Custom sorting comparators (179, 1356, etc.)
- Sorting for optimization (merging intervals, etc.)
- Bucket sort (347, 692)
- Topological sort (207, 210)

**Complexity:** O(N log N) optimal for comparison-based sorting

---

### 📝 [String Algorithms](src/main/java/com/svetanis/algorithms/string)
**When to use:** Palindromes, anagrams, pattern matching, substring problems

**Common LeetCode Problems:**
- 5: Longest Palindromic Substring
- 6: ZigZag Conversion
- 14: Longest Common Prefix
- 17: Letter Combinations of a Phone Number
- 20: Valid Parentheses
- 38: Count and Say
- 49: Group Anagrams
- 125: Valid Palindrome
- 151: Reverse Words in a String
- 165: Compare Version Numbers
- 205: Isomorphic Strings
- 214: Shortest Palindrome
- 242: Valid Anagram
- 273: Integer to English Words
- 290: Word Pattern
- 383: Ransom Note
- 387: First Unique Character in a String
- 409: Longest Palindrome
- 438: Find All Anagrams in a String
- 443: String Compression

**Common Techniques:**
- Two pointers
- Character frequency counting
- Pattern matching
- Trie (for prefix problems)

**Complexity:** O(N) to O(N²) depending on approach

---

### 👆 [Two Pointers](src/main/java/com/svetanis/algorithms/twopointers)
**When to use:** Sorted arrays, palindromes, removing elements, pair/triplet problems

**Common LeetCode Problems:**
- 1: Two Sum (with sorted array)
- 15: 3Sum
- 16: 3Sum Closest
- 18: 4Sum
- 19: Remove Nth Node From End of List
- 26: Remove Duplicates from Sorted Array
- 27: Remove Element
- 28: Implement strStr()
- 88: Merge Sorted Array
- 125: Valid Palindrome
- 167: Two Sum II - Input array is sorted
- 344: Reverse String
- 345: Reverse Vowels of a String
- 557: Reverse Words in a String III
- 977: Squares of a Sorted Array
- 1099: Two Sum Less Than K

**Pattern:**
```
left = 0, right = n-1
while (left < right) {
    if (condition) {
        right--
    } else {
        left++
    }
}
```

**Complexity:** O(N) time, O(1) space

---

## 🎯 How to Use This Repository

### 1️⃣ **Learn by Pattern**
```
1. Pick a pattern you're weak in (e.g., Sliding Window)
2. Look at the pattern folder
3. Read code comments to understand approach
4. Study brute-force solution first
5. Learn optimized solution
6. Implement similar LeetCode problems on your own
7. Compare with solution here
```

### 2️⃣ **Study Progression**
**Beginner:** Arrays → Strings → Two Pointers → Sorting  
**Intermediate:** Recursion → DP → Backtracking → BFS/DFS  
**Advanced:** Monotonic Stack → Complex DP → Graph Algorithms  

### 3️⃣ **Interview Prep Workflow**
```
1. Go to LeetCode problem
2. Try solving on your own first
3. If stuck, check the pattern in this repo
4. Review multiple approaches
5. Understand the solution
6. Close the repo and code it again
7. Compare your code with the solution
8. Move to next problem
```

### 4️⃣ **Find Solutions Fast**
```
You see LeetCode problem 76: Minimum Window Substring
↓
Recognize: This is a Sliding Window problem
↓
Go to src/main/java/com/svetanis/algorithms/slidingwindow/
↓
Find the solution and study it
```

---

## 💡 Key Insights

### Pattern Recognition is Key
In interviews, you'll see ~20 core patterns across 500+ LeetCode problems. **Recognizing the pattern is 70% of the battle.**

### Trade-offs Matter
Every solution trades **time vs space**:
- **Brute-force:** Easy to code, slow execution
- **Optimized:** Uses more memory, runs faster
- **Most optimal:** Balances both

### Always Discuss
In interviews:
- ✅ Discuss approach before coding
- ✅ Explain time/space complexity
- ✅ Mention edge cases
- ✅ Ask about constraints
- ✅ Suggest optimizations

### Edge Cases to Consider
- Empty input
- Single element
- Negative numbers
- Duplicates
- Large numbers (overflow)
- All same elements
- Reverse sorted
- Random order

---

## 📊 Problem Statistics

| Pattern | # Problems | Difficulty | Time to Master |
|---------|-----------|-----------|-----------------|
| Two Pointers | 15+ | Easy-Medium | 1-2 weeks |
| Sliding Window | 10+ | Easy-Medium | 1-2 weeks |
| DP | 30+ | Medium-Hard | 3-4 weeks |
| Backtracking | 10+ | Medium-Hard | 2-3 weeks |
| Binary Search | 10+ | Easy-Medium | 1 week |
| Sorting | 8+ | Easy-Medium | 1 week |
| Greedy | 10+ | Medium-Hard | 2 weeks |
| Math | 10+ | Easy-Medium | 1-2 weeks |

---

## 🚀 Getting Started

### Setup
```bash
git clone https://github.com/svetanis/algorithms.git
cd algorithms
mvn clean install
```

### Browse Solutions
```
Navigate to pattern folder, e.g., slidingwindow/
├── Read the source code
├── Review Solution1.java (brute-force)
├── Review Solution2.java (optimized if available)
└── Study the code comments
```

### Code Along
```bash
# Find LeetCode problem (e.g., LeetCode 76)
# Try to solve it on LeetCode first (don't look at solution!)
# Then come to this repo and compare
# Then try again without looking
```

---

## 🎓 Interview Tips by Pattern

### Sliding Window
- **Ask:** Is it contiguous? Can we rearrange?
- **Start:** What's the naive O(N²) approach?
- **Optimize:** Move right pointer, shrink left when needed

### Dynamic Programming
- **Ask:** Overlapping subproblems? Optimal substructure?
- **Start:** Write recursive solution with memoization
- **Optimize:** Convert to bottom-up tabulation

### Backtracking
- **Ask:** Find ONE solution or ALL solutions?
- **Start:** Draw the recursion tree
- **Optimize:** Pruning strategies

### Binary Search
- **Ask:** Is array sorted? Rotated?
- **Watch:** Off-by-one errors in left/right boundaries
- **Test:** Edge cases (single element, duplicates)

### Two Pointers
- **Ask:** Sorted array? Need to find pairs?
- **Start:** Compare from both ends
- **Optimize:** Skip elements you've already seen

---

## 📚 Learning Philosophy

> "These solutions are **study materials**, not answers to copy. The goal is **understanding patterns** so you can solve **unseen problems** in interviews."

Focus on:
1. **Understanding the pattern** - Why does it work?
2. **Recognizing when to apply** - What signals indicate this pattern?
3. **Implementing from memory** - Can you code it without looking?
4. **Explaining clearly** - Can you teach it to someone else?

---

## 🤝 Contributing

Found a better solution? Have a great explanation? Missing a problem?
- Submit a pull request
- Add more test cases
- Improve complexity analysis
- Share insights in code comments

---

## 📝 Tracking Your Progress

Create a checklist to track mastery:

```
Backtracking
[ ] Permutations (46)
[ ] Combinations (77)
[ ] Subsets (78)
[ ] Word Search (79)
[ ] N-Queens (51)

Sliding Window
[ ] Longest Substring Without Repeating (3)
[ ] Minimum Window Substring (76)
[ ] Permutation in String (567)

DP
[ ] Climbing Stairs (70)
[ ] Unique Paths (62)
[ ] Coin Change (322)
[ ] Edit Distance (72)

... continue for other patterns
```

---

## ⏱️ Recommended Study Schedule

### Week 1-2: Fundamentals
- Arrays & Strings basics
- Sorting & Searching
- Two Pointers (easy problems)

### Week 3-4: Core Patterns
- Sliding Window
- Binary Search
- Recursion basics

### Week 5-6: Intermediate
- Dynamic Programming (easy/medium)
- Backtracking
- Matrix/Grid problems

### Week 7-8: Advanced
- Complex DP (hard)
- Monotonic Stack
- Advanced Backtracking

---

## 🎯 Interview Success Metrics

✅ **Can I solve it?** - Implement without hints  
✅ **Can I optimize?** - Suggest better approaches  
✅ **Can I analyze?** - Prove time/space complexity  
✅ **Can I discuss?** - Explain approach clearly  
✅ **Can I handle edge cases?** - Test thoroughly  

If all ✅ for a pattern, you've mastered it!

---

## 💻 Quick Reference: Finding Solutions

### By LeetCode Number
```
LeetCode 76?  → Binary search for "76" in the repo
              → Or check Sliding Window folder
```

### By Pattern
```
Stuck on Sliding Window problems?
→ Go to src/main/java/com/svetanis/algorithms/slidingwindow/
→ See all sliding window solutions
```

### By Difficulty
```
Easy problems: Two Pointers, Sorting, Simple DP
Medium problems: Sliding Window, Most DP, Backtracking
Hard problems: Complex DP, Advanced Backtracking
```

---

## 📞 Using This Repo Effectively

✅ **DO:**
- Read the code carefully
- Try to understand the approach
- Attempt the problem first before looking
- Compare multiple solutions
- Trace through with examples

❌ **DON'T:**
- Copy-paste solutions into LeetCode
- Memorize without understanding
- Skip the complexity analysis
- Ignore edge cases
- Stop after one solution

---

## 🎓 Words of Wisdom

> "The best way to prepare for interviews is to solve 100 problems deeply, not 500 problems shallowly."

This repo helps you solve problems **deeply** by:
- Showing multiple approaches
- Explaining the logic
- Analyzing complexity
- Discussing trade-offs

---

**Happy Coding! 🚀**

Master one pattern at a time, solve problems from this repo as reference, and ace your interviews.

**Pro Tip:** Create a private fork, track your progress, and come back to patterns you struggle with!