# LeetCode Write-ups — Java

Long-form write-ups for the problems in this repository that are solved **more than once in the
source tree** — brute force, memoized, tabulated, space-optimized.

Each page walks the whole progression and shows *why each step is forced by the previous one's
failure*, with the measured verdict for every version: what times out, what overflows the stack,
what actually passes. These are the problems where reading only the optimal solution teaches you
the least.

| # | Problem | Approaches | The step most write-ups skip |
|---|---------|-----------|------------------------------|
| 518 | [Coin Change II](518-coin-change-ii.md) | 5 | Memoization fixes the time complexity and the solution **still** fails — on stack depth, not on time |

Java source for every solution lives under
[`src/main/java/com/svetanis/algorithms/`](../src/main/java/com/svetanis/algorithms).
