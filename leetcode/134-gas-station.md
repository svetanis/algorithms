# 134. Gas Station — Java Solutions

**The problem, in one line:** stations are arranged in a circle; station `i` gives you `gas[i]` fuel
and it costs `cost[i]` to drive from `i` to the next one — find a station you can start from, with an
empty tank, and get all the way back round, or report that no such station exists.

[LeetCode 134 — Gas Station](https://leetcode.com/problems/gas-station/) · the judge's signature is
`int canCompleteCircuit(int[] gas, int[] cost)`.

---

## Two facts, answering two different questions

Only `gas[i] - cost[i]` matters — call it station `i`'s **net**. On `gas = {1,2,3,4,5}`,
`cost = {3,4,5,1,2}` the nets are `-2, -2, -2, +3, +3`.

**Their total says *whether* an answer exists, and can never say *which*.** A full circuit pays every
cost and collects every drop of gas, so it ends the tank at that total wherever it began — negative
means no station works, non-negative means some station does. But the total does not change when the
stations are reordered, and reordering changes the answer:

| gas | cost | nets | total | the station that works |
|---|---|---|---|---|
| `{1, 0}` | `{0, 1}` | `+1, -1` | `0` | station **0** |
| `{0, 1}` | `{1, 0}` | `-1, +1` | `0` | station **1** |

**A stretch that runs the tank dry kills every station inside it.** If you start at `s` and first go
negative arriving at `f`, no station between them works either: starting later skips a prefix whose
total was non-negative the whole way, so you reach `f` with no more fuel and fail there too. That is
why the scan jumps to `f + 1` rather than `s + 1` — one pass, not `n`.

## Four solutions, three greedy arguments

All four return a start that completes the circuit whenever one exists, and `-1` when none does.
They differ in what stands in for the yes/no test, and in which direction the candidate start moves.

| # | Method | What proves an answer exists | Where the candidate start moves | Station visits |
|---|---|---|---|---|
| 1 | [`GasStationForwardScan.startTwoPass`](../src/main/java/com/svetanis/algorithms/greedy/GasStationForwardScan.java) | a first pass that sums the array on its own | forward — jumps to `i + 1` when the tank goes dry | `2n` |
| 2 | [`GasStationForwardScan.startFused`](../src/main/java/com/svetanis/algorithms/greedy/GasStationForwardScan.java) | the same sum, accumulated inside the scan | forward — jumps to `i + 1` | `n` |
| 3 | [`GasStationBackwardWindow.start`](../src/main/java/com/svetanis/algorithms/greedy/GasStationBackwardWindow.java) | falls out for free: its run of stations ends up covering every station exactly once, so its running sum **is** that sum | backward — takes in the station *behind* the current start | `n` |
| 4 | [`GasStationDoubledSweep.start`](../src/main/java/com/svetanis/algorithms/greedy/GasStationDoubledSweep.java) | nothing separate — a candidate that survives `n` stations in a row has driven the circuit | forward — dropped entirely and re-seeded, sweeping `2n` positions | up to `2n` |

Rows 1 and 2 share a class because they make the **same** greedy argument — 2 is 1 with the passes
fused. All four are `O(n)` in time and `O(1)` in space. **Write #2** — it is the shortest, and its
reset-on-negative scan is the step inside Kadane's algorithm, which is what
[53](https://leetcode.com/problems/maximum-subarray/) is built from.

## 1. Two passes, kept apart

Pass 1 answers *whether*, pass 2 answers *which*, and nothing in pass 2 knows about pass 1.

```java
public static int startTwoPass(int[] gas, int[] cost) {
    int n = gas.length;
    int totalFuel = 0;
    int totalCost = 0;
    for (int i = 0; i < n; i++) {          // pass 1: the yes/no test, and only that
        totalFuel += gas[i];
        totalCost += cost[i];
    }
    if (totalFuel < totalCost) {
        return -1;                         // the ONLY place -1 can come from
    }
    int fuel = 0;
    int start = 0;
    for (int i = 0; i < n; i++) {          // pass 2: which station, assuming one exists
        if (fuel + gas[i] - cost[i] < 0) { // would arriving at i+1 leave us in debt?
            start = i + 1;                 // then everything from start..i is dead
            fuel = 0;                      // and the new candidate starts empty
        } else {
            fuel += gas[i] - cost[i];
        }
    }
    return start;
}
```

## 2. The same two passes fused

`startFused`, in the same class, runs both passes in one loop — which is why it is shorter than §1
and harder to read: `total` and `sum` add up **the same numbers** for two unrelated purposes.

```java
public static int startFused(int[] gas, int[] cost) {
    int n = gas.length;
    int sum = 0;
    int total = 0;
    int start = 0;
    for (int i = 0; i < n; i++) {
        total += gas[i] - cost[i];      // never reset -- this is the yes/no test
        sum += gas[i] - cost[i];        // reset at every failure -- this is the candidate's tank
        if (sum < 0) {                  // `< 0`, not `<= 0`; see trap 2
            start = i + 1;              // the whole stretch start..i is dead
            sum = 0;
        }
    }
    return total >= 0 ? start : -1;     // `start` is only meaningful once total says yes
}
```

`total` is a fact about the input. `sum` is a fact about one candidate, thrown away each time the
candidate is. Deleting either one leaves a program that answers half the question.

**Traced**, on `gas = {1,2,3,4,5}`, `cost = {3,4,5,1,2}` — nets `-2, -2, -2, +3, +3`. `sum` is shown
as it lands, then as the reset leaves it:

| i | net | `total` | `sum` after adding | reset? | `start` |
|---|---|---|---|---|---|
| 0 | −2 | −2 | −2 | yes → `sum = 0` | 1 |
| 1 | −2 | −4 | −2 | yes → `sum = 0` | 2 |
| 2 | −2 | −6 | −2 | yes → `sum = 0` | **3** |
| 3 | +3 | −3 | 3 | no | 3 |
| 4 | +3 | **0** | 6 | no | 3 |

The two columns move independently: `total` was still deeply negative at row 2, when `start` already
held the answer.

## 3. Growing a run of stations at either end

This one keeps a **run of neighbouring stations** — consecutive around the circle, no gaps — and one
rule about it: **totalling the nets from the run's leftmost station, every running total stays
`>= 0`.** Once the run holds every station, that rule says you can start at the leftmost
one with an empty tank and get all the way round — which is the answer.

`start` is the run's leftmost station and only ever moves **down**; `end` is the next station not
yet taken in and only ever moves **up**, wrapping past the last one back to 0. Both begin at the
last station, the only choice that leaves `start` enough room: it can need `n - 1` descents.

The run only ever grows. A **forward** step is the default. A **backward** step happens when the
forward step just taken pushed the run's total below zero, and repeats until that total is back to
`>= 0`.

```java
public static int start(int[] gas, int[] cost) {
    int n = gas.length;
    int sum = 0;
    int start = n - 1;                        // the run is empty, sitting at the last station
    int end = n - 1;
    int stations = 0;                         // how many stations the run holds
    while (stations < n) {
        sum += gas[end] - cost[end];          // grow forward: one more leg to drive
        stations++;
        end = (end + 1) % n;
        while (sum < 0 && stations < n) {
            start--;                          // grow backward: take in the station behind
            sum += gas[start] - cost[start];
            stations++;
        }
    }
    return sum >= 0 ? start : -1;             // `sum` now covers all n, so it IS the grand total
}
```

On `gas = {1,2,3,4,5}`, `cost = {3,4,5,1,2}` — nets `-2, -2, -2, +3, +3`:

| takes in | at which end | run, left to right | running totals along it |
|---|---|---|---|
| station 4 | — | `4` | `+3` |
| station 0 | front | `4, 0` | `+3, +1` |
| station 1 | front | `4, 0, 1` | `+3, +1, **−1**` ← broken |
| station 3 | **back** | `3, 4, 0, 1` | `+3, +6, +4, +2` |
| station 2 | front | `3, 4, 0, 1, 2` | `+3, +6, +4, +2, 0` |

Row 4 repairs row 3 by putting station 3 at the **back**, so its `+3` arrives before all that
driving — lifting every total in the row and adding a new first one.

## 4. A doubled sweep with a restart latch

The only one that never computes a total. It walks positions `0` through `2n - 1` with
`start == -1` meaning *"no candidate right now"*, and returns the first candidate to survive `n`
stations in a row — which, by then, has been watched driving the whole circuit.

```java
public static int start(int[] gas, int[] cost) {
    int fuel = 0;
    int start = -1;                                 // -1 means "between candidates"
    int location = 0;
    int n = gas.length;
    while (location != 2 * n) {                     // two laps, so every start gets a full circuit
        if (start == -1) {
            start = location;                       // adopt this position as the candidate
        }
        fuel += gas[location % n];
        fuel -= cost[location % n];
        if (fuel < 0) {
            fuel = 0;
            start = -1;                             // candidate dead; the next station re-seeds
        }
        location++;
        if (start != -1 && location - start == n) { // survived n in a row == drove the circuit
            return start % n;                       // so there is nothing left to verify
        }
    }
    return -1;                                      // nothing survived n in a row
}
```

**Walking `2n` is what replaces the total.** The answer may sit near the end of the array and its
circuit wraps past position `n - 1`; the second lap is there so that wrap can be walked. That
doubling is the transferable part — it is how [503](https://leetcode.com/problems/next-greater-element-ii/)
and [918](https://leetcode.com/problems/maximum-sum-circular-subarray/) stop treating the wrap as a
special case. The cost is that an impossible route takes the full `2n` steps, where §2 and §3 stop
at `n`.

## Traps

**1. "Start at the first station with a positive net."**

It sounds like it answers the *which* question and it does not — a station being positive says
nothing about what comes after it. On `gas = {1, 0, 1}`, `cost = {0, 2, 0}` — nets `+1, −2, +1` —
the rule picks station 0: you leave with 1 in the tank, reach station 1, and need 2 to get out of
it. The only start that works is station 2.

**Instead:** the sum decides *whether*; the position of the last failure decides *which*.

**2. Writing the reset as `sum <= 0`.**

A tank of exactly zero is not a failure — it is arriving with precisely enough, and the next
station's fuel is waiting for you. On `gas = {2, 0}`, `cost = {0, 2}` station 0 works: fill up with
2, spend 2, arrive back with 0. The `<=` version resets on the final station and answers **2**,
which is not a station; there are only 0 and 1. This bug always presents that way — it returns `n`,
one past the end, never some other real station — so it survives every input where the running sum
does not land on exactly zero at the last index.

**Instead:** `sum < 0`. The test is *"did I go into debt"*, and zero is not debt.

**3. Returning the scan's `start` without the yes/no test.**

The reset scan always finishes holding a number, and has no way to notice that every station failed
— a failure is exactly what makes it move to the next one. On `gas = {0, 1}`, `cost = {2, 0}` —
nets `−2, +1`, sum −1, so no start exists — the scan alone returns **1**: an in-range, entirely
plausible station that cannot complete the circuit.

**Instead:** the sum has to be tested somewhere — §1's separate pass, §2's `total`, §3's final
`sum >= 0`, or §4's *survived `n` in a row*. It is the first thing to look for when reading any of
them.

## Related

- [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) — the same
  reset-when-negative scan on the same running sum, keeping the best window instead of the last
  reset point.
- [55. Jump Game](https://leetcode.com/problems/jump-game/) — one pass where a failure is final
  rather than a reset: track the furthest reachable index and stop when the sweep passes it.
- [503. Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) and
  [918. Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray/)
  — the `2n` walk from §4, where it is the main idea rather than a curiosity.

All source files:
[`greedy/`](../src/main/java/com/svetanis/algorithms/greedy) —
[`GasStationForwardScan.java`](../src/main/java/com/svetanis/algorithms/greedy/GasStationForwardScan.java),
[`GasStationBackwardWindow.java`](../src/main/java/com/svetanis/algorithms/greedy/GasStationBackwardWindow.java),
[`GasStationDoubledSweep.java`](../src/main/java/com/svetanis/algorithms/greedy/GasStationDoubledSweep.java).
The last two also take `List<Integer>` through a delegating overload.
