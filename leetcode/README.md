# LeetCode Write-ups — Java

Long-form write-ups that cover every version of a solution, not only the fastest one. The versions
that fail are what explain the one that works, and each page says exactly what broke and why the
next version was forced. A few pages instead follow one technique across several problems, where
recognising it is the hard part.

Every version carries a measured verdict: what times out, what overflows the stack, what returns a
plausible wrong answer. Every page traces a small input filling in step by step, because that is the
part a solutions tab never shows you.

Filenames stay flat — `NNN-problem-slug.md` — so a page's path never changes once it is linked.

## Backtracking

Every approach passes, so the progression is not about what the judge rejects. It is about how much
of the search space each one refuses to build, and what carrying the partial answer costs.

| # | Problem | Approaches | The step most write-ups skip |
|---|---------|-----------|------------------------------|
| 22 | [Generate Parentheses](22-generate-parentheses.md) | 5 | **Only one of the five is backtracking in the strict sense.** The others prune just as early but never need an undo — a fresh string per step leaves the caller's untouched, so there is nothing to restore. The undo exists because one version mutates a shared buffer, not because the algorithm demands it |

## Grid and graph traversal

Depth-first and breadth-first look interchangeable on these problems. Input size is what decides
between them.

| # | Problem | Approaches | The step most write-ups skip |
|---|---------|-----------|------------------------------|
| 200 | [Number of Islands](200-number-of-islands.md) | 3 | **A grid that is all land is one island, and the recursion needs a stack frame for every cell in it** — 90,000 at the size the constraints allow, which no default stack takes. The queue version has no depth at all |

## Topological sort

One loop serves all of these. What changes from problem to problem is what each node carries along
it and what is kept at the end.

| # | Problem | Covers | The step most write-ups skip |
|---|---------|-----------|------------------------------|
| 207 | [Course Schedule](207-course-schedule.md) | 4 problems | Two solutions that read the prerequisite pairs in opposite directions give the same answer on every input here — only one of them is right on Course Schedule II |

## Greedy

Guessing the greedy choice is easy; justifying it is not. The rules that fail are the ones that are
right on most inputs.

| # | Problem | Approaches | The step most write-ups skip |
|---|---------|-----------|------------------------------|
| 134 | [Gas Station](134-gas-station.md) | 4 | The total of `gas[i] - cost[i]` decides **whether** an answer exists and can never name **which** station — reordering the same stations moves the answer without moving the total |

## Dynamic programming

One recurrence, implemented four or five ways. The page is about *why each step is forced by the
previous one's failure*.

| # | Problem | Approaches | The step most write-ups skip |
|---|---------|-----------|------------------------------|
| 416 | [Partition Equal Subset Sum](416-partition-equal-subset-sum.md) | 6 | The same memoization that sinks 518 is **enough** here — and one word in the recurrence tells you which case you are in beforehand |
| 518 | [Coin Change II](518-coin-change-ii.md) | 7 | Memoization fixes the time complexity and the solution **still** fails — on stack depth, not on time |

---

Java source for every solution lives under
[`src/main/java/com/svetanis/algorithms/`](../src/main/java/com/svetanis/algorithms).
