# 200. Number of Islands — Java Solutions

Three ways to solve [LeetCode 200 · Number of Islands](https://leetcode.com/problems/number-of-islands/)
in Java, in five files. All three do the same two things in the same order; they differ in **where
they record that a cell has already been counted**, in **where they test a cell before walking into
it**, and in **whether the cells still to be walked wait on the call stack or in a queue**.

**The problem, in one line:** given a rectangular grid whose cells are land or water, count the
connected groups of land — two land cells are connected if they share an edge, and touching at a
corner is not enough.

---

## 1. The shape every solution shares

Sweep the grid. On reaching land that no earlier sweep has accounted for, add one to the total, then
walk that whole island marking every cell, so the sweep does not count it again when it reaches its
other cells.

```java
for (int r = 0; r < rows; r++) {
    for (int c = 0; c < cols; c++) {
        if (isLand(r, c) && notYetAccountedFor(r, c)) {
            walkTheWholeIsland(r, c);   // <-- the only part that differs between solutions
            count++;
        }
    }
}
```

**The count is incremented in the sweep, never inside the walk** — the walk visits many cells, but
only the sweep's *first* arrival on an island is a new island. That is the same in all five files.

## 2. Depth-first, with a `visited` matrix

The straightforward version: a `boolean[rows][cols]` beside the grid, a loop over four direction
offsets, and the grid handed back untouched.

**Of the three, [`NumberOfIslandsMatrixSubmit`][submit] is the one to read.** [`NumberOfIslandsMatrix`][mtx] and
[`NumberOfIslandsDfs`][dfs] are the same solution over `int[][]` and `List<List<Integer>>`; only
this one takes the `char[][]` of `'1'` and `'0'` that LeetCode passes.

```java
private static final char WATER = '0';

// up, left, right, down -- the four edge-sharing neighbours, and no diagonals
private static int[] dx = { -1, 0, 0, 1 };
private static int[] dy = { 0, -1, 1, 0 };

public static int count(char[][] g) {
    int count = 0;
    int n = g.length;
    int m = g[0].length;
    boolean[][] visited = new boolean[n][m];
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            if (g[r][c] != WATER && !visited[r][c]) {
                dfs(g, r, c, visited);   // walk the whole island
                count++;                 // ... so it only ever counts once
            }
        }
    }
    return count;
}

private static void dfs(char[][] g, int row, int col, boolean[][] visited) {
    visited[row][col] = true;            // written on the way IN, and never undone
    for (int k = 0; k < dx.length; ++k) {
        int x = row + dx[k];
        int y = col + dy[k];
        if (isLand(g, x, y) && !visited[x][y]) {
            dfs(g, x, y, visited);
        }
    }
}

private static boolean isLand(char[][] g, int row, int col) {
    boolean one = row >= 0 && row < g.length;      // row number is in range
    boolean two = col >= 0 && col < g[0].length;   // col number is in range
    return one && two && g[row][col] != WATER;
}
```

**`visited[row][col] = true` is the first line of `dfs`, and nothing sets it back to `false`.**
Marking on the way in stops two neighbouring land cells calling each other forever. Never unmarking
is what makes the count right: *"this cell belongs to an island already counted"* is still true
after the call returns, so there is nothing to undo. §3 measures what an undo costs.

## 3. Depth-first, in place, testing before the call

[`NumberOfIslandsInPlaceGuarded`][guarded] changes two things, and one of them is worth far more
than the other.

**It keeps no separate record.** Each land cell is overwritten with water as it is reached, so the
grid itself says what has been counted, and the bounds check, the water check and the visited check
become one test.

**It tests at the four call sites instead of at the top of the recursion**, so a call is made only
when it will do work.

```java
private static final char LAND = '1';
private static final char WATER = '0';

public static int count(char[][] g) {
    int count = 0;
    int n = g.length;
    int m = g[0].length;
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            // still LAND means no earlier walk has reached it, so this one
            // test does the work of the separate visited test in section 2
            if (g[r][c] == LAND) {
                sink(g, r, c);
                count++;
            }
        }
    }
    return count;
}

private static void sink(char[][] g, int row, int col) {
    g[row][col] = WATER;                 // no guard on entry: whoever called
                                         // already knows this cell is land
    if (row > 0 && g[row - 1][col] == LAND) {
        sink(g, row - 1, col);
    }
    if (row + 1 < g.length && g[row + 1][col] == LAND) {
        sink(g, row + 1, col);
    }
    if (col > 0 && g[row][col - 1] == LAND) {
        sink(g, row, col - 1);
    }
    if (col + 1 < g[0].length && g[row][col + 1] == LAND) {
        sink(g, row, col + 1);
    }
}
```

**The test placement is the largest effect on this problem.** §2 calls `dfs` four times for every
land cell, and on a grid that is half water most of those calls return on their first line, having
pushed a stack frame to do nothing. On a 300 × 300 grid that is half water, not making those calls
is worth **about 40%** — 27–30 ms against 47–50 ms. For scale, writing §2's form without the
direction arrays, changing nothing else, is worth about 13%.

**What it costs.** The caller's grid comes back as solid water, which LeetCode does not care about
and a caller with other plans for its grid would. And the bounds test is now written four times,
each reading a different neighbour: four places to make a typo that still compiles.

> ⚠️ **The trap: putting the mark back on the way out.** Once the grid is doing double duty as the
> record, restoring a cell looks like tidying up. Adding `g[row][col] = LAND;` as the last line of
> `sink` is wrong:
>
> A cell restored there is land again while the sweep is still running, so the sweep walks into it
> and counts its island a second time. **On a solid 3 × 3 grid it answers 9 instead of 1** — one
> island per cell — and it is wrong on **every** grid where two land cells touch. It is right only
> where no two do, which is where every island is a single cell and the undo never matters.
>
> The mark answers *"has this island already been counted"*, and that stays true when the call
> returns. There is nothing for the return to undo, in either §2 or §3.

## 4. Breadth-first, with the pending cells in a queue

**The constraints allow a grid that breaks both recursive versions.** 300 × 300 of solid land is one
island of 90,000 cells, so the walk goes 90,000 calls deep and throws `StackOverflowError` on a
default stack instead of answering.

No recursion avoids that, because the depth *is* the island. Keep the pending cells in a queue on the
heap instead: take one out, put its unmarked land neighbours in.

**Submit [`NumberOfIslandsBfsSubmit`][bfssub].** Same `char[][]`, same `boolean[][]` record as §2,
same `isLand` and `dx`/`dy`; only `dfs` becomes a queue loop.

```java
public static int count(char[][] g) {
    int count = 0;
    int n = g.length;
    int m = g[0].length;
    boolean[][] visited = new boolean[n][m];
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < m; c++) {
            if (g[r][c] != WATER && !visited[r][c]) {
                visited[r][c] = true;      // the start is marked BEFORE the walk begins,
                bfs(g, r, c, visited);     // not when the walk polls it
                count++;                   // the sweep counts, exactly as in section 1
            }
        }
    }
    return count;
}

private static void bfs(char[][] g, int sr, int sc, boolean[][] visited) {
    Queue<int[]> queue = new ArrayDeque<>();
    queue.add(new int[] { sr, sc });
    while (!queue.isEmpty()) {             // no recursion anywhere: the queue is the whole walk
        int[] curr = queue.poll();
        for (int k = 0; k < dx.length; ++k) {
            int x = curr[0] + dx[k];
            int y = curr[1] + dy[k];
            if (isLand(g, x, y) && !visited[x][y]) {   // the same test §2's dfs makes
                visited[x][y] = true;      // mark and queue, always together --
                queue.add(new int[] { x, y });   // see the trap below
            }
        }
    }
}
```

The caller's grid comes back untouched, unlike §3.

### The walk, cell by cell

Four land cells in a square, starting at `(0,0)`, neighbours in the file's order — up, left, right,
down:

| turn | polled | its land neighbours | added | queue afterwards |
|---|---|---|---|---|
| — | — | — | `(0,0)` | `(0,0)` |
| 1 | `(0,0)` | `(0,1)`, `(1,0)` | both | `(0,1)`, `(1,0)` |
| 2 | `(0,1)` | `(0,0)` marked, `(1,1)` | `(1,1)` | `(1,0)`, `(1,1)` |
| 3 | `(1,0)` | `(0,0)` marked, `(1,1)` marked | nothing | `(1,1)` |
| 4 | `(1,1)` | `(0,1)` marked, `(1,0)` marked | nothing | empty |

**Turn 3 is the one to watch.** `(1,1)` is already in the queue *and already marked*, so `(1,0)` adds
nothing. Four cells, four additions, queue never longer than two.

> ⚠️ **The trap: marking a cell when it comes out of the queue instead of when it goes in.**
>
> A cell waits in the queue for several turns. If nothing marks it until it is polled, every
> neighbour polled during that wait sees an unmarked cell and queues it again — in the trace above,
> turn 3 would queue `(1,1)` a second time. The extra copies are thrown away when polled, so the
> answer is right on every grid and nothing ever tells you.
>
> On a grid the waste stops at four times, because a cell has four neighbours to be queued by; on
> solid land it is twice. Off a grid there is no such ceiling — a node with 999 neighbours can be
> queued 999 times.
>
> **Instead: write the mark in the line that adds.** It means *"already spoken for"*, and that is
> true the moment the cell goes in, not when it comes out.

## Complexity

`r` = rows, `c` = columns. All three are `O(r·c)` in time and `O(r·c)` in space; the worst case
throughout is one island covering the whole grid. The space is made of different things:

| | Space is made of | Caller's grid |
|---|---|---|
| §2, `visited` matrix | `r × c` booleans **plus** `r·c` stack frames | untouched |
| §3, in place | `r·c` stack frames alone | returned as solid water |
| §4, queue + `visited` matrix | `r × c` booleans **plus** a queue holding `O(r + c)` cells | untouched |

**The recursion depth is the size of the island, not `rows + cols`.** On a solid square the deepest
chain of open calls is exactly the land-cell count: the walk snakes row by row and never returns
until it has reached everything.

---

[dfs]: https://github.com/svetanis/data-structures/blob/master/src/main/java/com/svetanis/datastructures/graph/islands/NumberOfIslandsDfs.java
[mtx]: https://github.com/svetanis/data-structures/blob/master/src/main/java/com/svetanis/datastructures/graph/islands/NumberOfIslandsMatrix.java
[submit]: https://github.com/svetanis/data-structures/blob/master/src/main/java/com/svetanis/datastructures/graph/islands/NumberOfIslandsMatrixSubmit.java
[guarded]: https://github.com/svetanis/data-structures/blob/master/src/main/java/com/svetanis/datastructures/graph/islands/NumberOfIslandsInPlaceGuarded.java
[bfssub]: https://github.com/svetanis/data-structures/blob/master/src/main/java/com/svetanis/datastructures/graph/islands/NumberOfIslandsBfsSubmit.java

Java source for these solutions lives in the sibling repository
[`svetanis/data-structures`](https://github.com/svetanis/data-structures), under
`src/main/java/com/svetanis/datastructures/graph/islands/`.
