# 207. Course Schedule — Java Solutions

**The problem, in one line:** given `n` courses and a list of pairs meaning *"take this one before
that one"*, decide whether all `n` can be taken.

[LeetCode 207 — Course Schedule](https://leetcode.com/problems/course-schedule/) · the judge's
signature is `boolean canFinish(int numCourses, int[][] prerequisites)`.

---

## The reframe, which is most of the answer

The pairs are edges. `prerequisites[i] = [a, b]` means `b` must come before `a`, so draw an arrow
`b → a` and the question becomes: **can these arrows be laid out in a straight line?**

Two facts fall out of that, and the second one is the whole algorithm.

**An order exists exactly when the graph has no cycle.** If A needs B and B needs A, one of them has
to be first and then it is wrong. So there is no separate cycle-detection step to write — a
topological sort *is* a cycle detector that happens to also produce an order, and 207 is the version
that throws the order away and keeps the success flag.

**A course is takeable when every prerequisite it has is already taken.** Not *one* of them — all of
them, which is why the code keeps a count per course rather than a flag:

```java
inDegree[neighbor]--;                    // one prerequisite of `neighbor` is now done
if (inDegree[neighbor] == 0) {           // that was its LAST one
    queue.add(neighbor);
}
```

`inDegree[v]` starts as the number of arrows pointing at `v` and only ever goes down. A
`boolean visited[]` cannot hold that: *"have I been here?"* has a yes-or-no answer, *"are all four of
my prerequisites finished?"* does not — you have to know how many are still outstanding, and a
switch that flips once cannot count.

## The solution

`O(V + E)` in time and space: every course leaves the queue once, every pair is read once.
[`IsCourseScheduleSubmit`][arr].

```java
public static boolean isValidCourseSchedule(int n, int[][] prerequisites) {
    int[] inDegree = new int[n];
    List<List<Integer>> graph = graphInit(n);
    for (int[] prerequisite : prerequisites) {
        int course = prerequisite[0];
        int prereq = prerequisite[1];        // [a, b] means b BEFORE a
        graph.get(prereq).add(course);       // so the arrow runs b -> a
        inDegree[course]++;                  // and it is `a` that owes one more
    }
    return topoSort(graph, inDegree);
}

private static boolean topoSort(List<List<Integer>> g, int[] inDegree) {
    int count = 0;
    Queue<Integer> queue = sources(inDegree);
    while (!queue.isEmpty()) {
        count++;                             // one more course taken
        int course = queue.poll();
        for (int neighbor : g.get(course)) {
            inDegree[neighbor]--;            // one of its prerequisites is now done
            if (inDegree[neighbor] == 0) {
                queue.add(neighbor);         // its last prerequisite just landed
            }
        }
    }
    return count == g.size();                // ran out of queue early == a cycle
}

private static Queue<Integer> sources(int[] inDegree) {
    Queue<Integer> queue = new ArrayDeque<>();
    // all vertices with 0 in-degree
    for (int i = 0; i < inDegree.length; i++) {
        if (inDegree[i] == 0) {              // owes nothing, so it can be taken now
            queue.add(i);
        }
    }
    return queue;
}
```

Three lines carry the meaning and the rest is bookkeeping. `inDegree[course]++` is *"this course
waits for one more thing"*. `inDegree[neighbor] == 0` is *"that was its last one"*.
`count == g.size()` is *"did I get through them all"*.

## A worked trace

`n = 4`, `prerequisites = [[1,0], [2,0], [3,1], [3,2]]` — course 0 first, then 1 and 2 in either
order, then 3.

Arrows: `0 → 1`, `0 → 2`, `1 → 3`, `2 → 3`. Starting counts: `0:0  1:1  2:1  3:2`.

| step | queue before | taken | counts after | queue after |
|---|---|---|---|---|
| seed | — | — | `0:0  1:1  2:1  3:2` | `[0]` |
| 1 | `[0]` | **0** | `1:0  2:0  3:2` | `[1, 2]` |
| 2 | `[1, 2]` | **1** | `2:0  3:1` | `[2]` |
| 3 | `[2]` | **2** | `3:0` | `[3]` |
| 4 | `[3]` | **3** | — | `[]` |

`count` reached 4, so the answer is `true`.

**Look at step 2.** Both 1 and 2 are in the queue, and either could have been taken first — so this
input has more than one valid order. 207 only counts, so it does not care. LC 210 returns the order,
and there it matters a great deal.

**And the cycle case:** add `[0,3]` and course 0's count becomes 1. Nothing starts at zero, the queue
is empty before the loop begins, `count` stays 0, and the answer is `false`. The loop never goes
looking for the cycle; it just runs out.

## Traps

**1. The arrow direction — silent here, fatal on the sequel.**

`prerequisites[i] = [a, b]` means take `b` before `a`. So the arrow runs `b → a` and it is **`a`**
whose count goes up. Write it the other way:

```java
graph.get(course).add(prereq);      // WRONG direction
inDegree[prereq]++;
```

...and on 207 **nothing happens.** No wrong answer, no failing test, nothing to notice — because
**a graph has a cycle exactly when its reverse does.** Reverse every arrow of a ring and you still
have a ring, so a yes-or-no question about cycles cannot see the mistake at all.

The bill arrives on [210](https://leetcode.com/problems/course-schedule-ii/), where the same code
must return the order. On `n = 4` with `[[1,0], [2,0], [3,1], [3,2]]`, the reversed build returns
`[3, 1, 2, 0]` — the right length, every course present exactly once, nothing thrown — and it
satisfies **none of the four rules**. That is not a special case: on any graph with a valid order,
reversing the arrow returns a full-length order that violates at least one rule, because the answer
it produces is a correct order *of the reversed graph*.

**Instead:** write the direction as a comment above the loop, then read one example off the problem
statement and check it against the comment. Two lines, before any code.

**2. Reaching for `boolean visited[]` inside the loop.**

A flag answers *"have I been here?"*. It cannot answer *"are all four of my prerequisites done?"*.
If you write `visited[v] = true` in this loop you have written breadth-first search, which emits a
node as soon as **any** predecessor reaches it rather than when **all** of them have.

**Instead:** the only per-course state is the integer, and the only test on it is `== 0`.

**3. Writing a separate cycle detector.**

There is nothing to add. `count == g.size()` already is one, because a course inside a ring never
reaches count zero and so never enters the queue.

**Instead:** if you find yourself starting a second traversal to look for cycles, stop — the first
one already told you.

## Three sequels

**210 and 1462 are the loop above with one thing added** — the order kept instead of the count, and
a set of known prerequisites passed forward along every arrow. **630 has no prerequisites in it anywhere**,
and is a greedy heap.

**[210 — Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)** takes the same
input and runs the same loop. In [`CourseScheduleSubmit`][ii] only `topoSort` differs from the
solution above — the graph build and `sources` are identical — and inside it the standalone
`count++` becomes the recording line:

```java
private static int[] topoSort(List<List<Integer>> g, int[] inDegree) {
    int count = 0;
    int[] a = new int[g.size()];
    Queue<Integer> queue = sources(inDegree);
    while (!queue.isEmpty()) {
        int course = queue.poll();
        a[count++] = course;                 // 207 has a bare count++ here
        for (int neighbor : g.get(course)) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] == 0) {
                queue.add(neighbor);
            }
        }
    }
    return count == g.size() ? a : new int[0];   // short == a cycle, so no order exists
}
```

**Most inputs have more than one correct order** — after step 1 of the trace the queue held
`[1, 2]`, so `[0,1,2,3]` and `[0,2,1,3]` both satisfy every pair. So check the rule rather than a
specific array: for every pair `[a, b]`, does `b` appear before `a` in the output?

**[1462 — Course Schedule IV](https://leetcode.com/problems/course-schedule-iv/)** asks, for a list
of query pairs, whether one course is a prerequisite of another — directly or indirectly. So the
answer is a **table**, not a line: for every pair of courses, can you get from one to the other by
following arrows at all? (The table of every such indirect arrow is called the graph's *transitive
closure*.) [`CourseScheduleIV`][iv] fills that table in one pass. `f` is an `n × n` grid of
true/false where **`f[x][y]` means *x is a prerequisite of y***, and each time a course comes off
the queue, everything already known to be a prerequisite of it is copied into each of its
neighbours:

```java
public static List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries){
    int[] inDegree = new int[n];
    boolean[][] f = new boolean[n][n];
    List<List<Integer>> graph = graphInit(n);
    for (int[] prerequisite : prerequisites) {
        int course = prerequisite[1];        // 1462: [a, b] means a BEFORE b --
        int prereq = prerequisite[0];        // the opposite of 207 and 210
        graph.get(prereq).add(course);
        inDegree[course]++;
    }
    topoSort(n, graph, inDegree, f);
    List<Boolean> list = new ArrayList<>();
    for(int[] query : queries) {
        list.add(f[query[0]][query[1]]);
    }
    return list;
}

private static void topoSort(int n, List<List<Integer>> g, int[] inDegree, boolean[][] f) {
    Queue<Integer> queue = sources(inDegree);
    while (!queue.isEmpty()) {
        int course = queue.poll();
        for (int neighbor : g.get(course)) {
            f[course][neighbor] = true;
            for(int pre = 0; pre < n; pre++) {
                // everything that reaches course now reaches neighbor.
                // safe because course came off the queue, so its row is final
                f[pre][neighbor] |= f[pre][course];
            }
            inDegree[neighbor]--;
            if (inDegree[neighbor] == 0) {
                queue.add(neighbor);
            }
        }
    }
}
```

The merge costs `O(n)` per arrow, so `O(n · E)` in time, and the table itself is `O(n²)` in space —
which is the number that decides whether the approach is usable.

⚠️ **And its `[a, b]` means the opposite of 207's.** LeetCode 1462 says `[a, b]` means take `a`
before `b`; 207 and 210 say take `b` before `a`. Identical-looking input, opposite meaning — read the
sentence in *this* statement, and never carry a convention across from another problem.

**[630 — Course Schedule III](https://leetcode.com/problems/course-schedule-iii/)** is not this
technique and not a graph. Courses have a **duration and a deadline**, no prerequisites at all, and
you take as many as you can. [`CourseScheduleIII`][iii] sorts by deadline, takes everything greedily,
and throws back the longest course already taken whenever the clock overruns:

```java
public static int scheduleCourse(int[][] courses) {
    Arrays.sort(courses, comparingInt(c -> c[1]));   // earliest deadline first
    PriorityQueue<Integer> pq = new PriorityQueue<>(reverseOrder());
    int time = 0;
    for (int[] course : courses) {
        int duration = course[0];
        int lastDay = course[1];
        pq.offer(duration);                          // take it, then repair if it does not fit
        time += duration;
        while (time > lastDay) {
            time -= pq.poll();                       // drop the longest course taken so far
        }
    }
    return pq.size();
}
```

`O(n log n)`, and it agrees with an exhaustive search over every subset. **Read the statement, not
the title:** the input here is durations and deadlines, and there is no arrow to draw.

## Related

- [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) — the same algorithm
  keeping the order instead of the count. One line changed, and the arrow direction now matters.
- [1462. Course Schedule IV](https://leetcode.com/problems/course-schedule-iv/) — many reachability
  queries. The same loop, passing each course's known prerequisites forward.
- [630. Course Schedule III](https://leetcode.com/problems/course-schedule-iii/) — no prerequisites.
  Greedy with a max-heap; shares only the name.
- [269. Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) *(LeetCode Premium)* —
  the same loop where the arrows are not given to you and have to be read off a sorted word list.
- [1136. Parallel Courses](https://leetcode.com/problems/parallel-courses/) *(LeetCode Premium)* —
  the same loop counting rounds instead of courses, when any number can be taken at once.

All source files:
[`graph/directed/ts/schedule/`](https://github.com/svetanis/data-structures/tree/master/src/main/java/com/svetanis/datastructures/graph/directed/ts/schedule)
in the `data-structures` repository, and
[`search/heap/CourseScheduleIII.java`](https://github.com/svetanis/algorithms/blob/master/src/main/java/com/svetanis/algorithms/search/heap/CourseScheduleIII.java)
here.

[arr]: https://github.com/svetanis/data-structures/blob/master/src/main/java/com/svetanis/datastructures/graph/directed/ts/schedule/IsCourseScheduleSubmit.java
[ii]: https://github.com/svetanis/data-structures/blob/master/src/main/java/com/svetanis/datastructures/graph/directed/ts/schedule/CourseScheduleSubmit.java
[iv]: https://github.com/svetanis/data-structures/blob/master/src/main/java/com/svetanis/datastructures/graph/directed/ts/schedule/CourseScheduleIV.java
[iii]: https://github.com/svetanis/algorithms/blob/master/src/main/java/com/svetanis/algorithms/search/heap/CourseScheduleIII.java
