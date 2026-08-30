# LeetCode Write-ups — Java

Long-form write-ups for the problems in this repository that are solved **more than once in the
source tree** — brute force, memoized, tabulated, space-optimized.

Each page walks the whole progression and shows *why each step is forced by the previous one's
failure*, with the measured verdict for every version: what times out, what overflows the stack,
what actually passes. These are the problems where reading only the optimal solution teaches you
the least.

Pages are grouped below by what *"solved more than once"* actually means for that family, because
the thing that connects one approach to the next is different in each: **dynamic programming** (one
recurrence, four implementations), **graphs** (genuinely different algorithms, chosen not forced),
**trees** (recursive vs iterative vs traversal property), and **data structures** (Fenwick tree vs
segment tree vs merge-sort counting). Sections appear as their first page lands.

Filenames stay flat — `NNN-problem-slug.md` — so a page's path never changes once it is linked.

## Dynamic programming

One recurrence, implemented four or five ways. The page is about *why each step is forced by the
previous one's failure*.

| # | Problem | Approaches | The step most write-ups skip |
|---|---------|-----------|------------------------------|
| 416 | [Partition Equal Subset Sum](416-partition-equal-subset-sum.md) | 6 | The same memoization that sinks 518 is **enough** here — and one word in the recurrence tells you which case you are in beforehand |
| 518 | [Coin Change II](518-coin-change-ii.md) | 5 | Memoization fixes the time complexity and the solution **still** fails — on stack depth, not on time |

## Backtracking

Every approach passes, so the progression is not about what the judge rejects. It is about how much
of the search space each one refuses to build, and what carrying the partial answer costs.

| # | Problem | Approaches | The step most write-ups skip |
|---|---------|-----------|------------------------------|
| 22 | [Generate Parentheses](22-generate-parentheses.md) | 5 | **Only one of the five is backtracking in the strict sense.** The others prune just as early but never need an undo — a fresh string per step leaves the caller's untouched, so there is nothing to restore. The undo exists because one version mutates a shared buffer, not because the algorithm demands it |

---

Java source for every solution lives under
[`src/main/java/com/svetanis/algorithms/`](../src/main/java/com/svetanis/algorithms).
