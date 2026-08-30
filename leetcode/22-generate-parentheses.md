# 22. Generate Parentheses — Java Solutions

Five working Java solutions to [LeetCode 22 · Generate Parentheses](https://leetcode.com/problems/generate-parentheses/),
all of which pass, separated by *when* each one notices that a string cannot work —
only at full length, on arrival at a node, or before the step is taken at all.

**The problem, in one line:** given `n` pairs of brackets, produce every string in
which every `(` is matched by a later `)`. For `n = 2` that is `(())` and `()()`,
and nothing else.

---

## Which approach actually passes

**All five do**, including the brute force — LeetCode caps `n` at 8, which is small enough that
building every possible string still finishes in a few milliseconds.

| # | Approach | What it is | The check happens | The half-built string is | Pending work sits |
|---|----------|-----------|-------------------|--------------------------|-------------------|
| 1 | [Build everything, then filter](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesBruteForce.java) | **brute force** | at full length, once | a new `String` per queued state | in a queue — **exponential** |
| 2 | [Branch first, reject on arrival](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesRecursive.java) | **recursion with pruning** | on arrival at each node | a new `String` per step | on the call stack — O(n) |
| 3 | [Guard before recursing](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesGuarded.java) | **recursion with pruning** | before the call is made | a new `String` per step | on the call stack — O(n) |
| 4 | [One shared buffer with an undo](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesBacktracking.java) — **submit this** | **backtracking** | before the call is made | **one `StringBuilder`, reused** | on the call stack — O(n) |
| 5 | [The same search without recursion](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesIterative.java) | the same pruning, **iteratively** | before the state is queued | a new `String` per queued state | in a queue — **exponential** |

**Only row 4 is backtracking in the strict sense** — choose, explore, **unchoose**. It is the only
one with an undo, and the reason is in the fifth column: it mutates one shared `StringBuilder`, so
whatever a call appends it must remove on the way out. Rows 2, 3 and 5 build each string with
`s + "("`, which creates a *new* string and leaves the caller's untouched, so there is nothing to
restore.

**The progression is worth reading in that order**, because each step follows from the one before.
Rows 2 and 3 differ only in *when* the pruning happens. Row 4 is what you get when you stop
allocating a string per step and mutate one buffer instead — **and mutating is precisely what forces
an undo to exist.** Row 5 changes where the pending work is kept, and pays for it in memory.

**Time: exponential, in two different senses.** Row 1 builds all `2^(2n)` strings of length `2n`,
because every position is one of two characters — so `O(2^(2n) * n)`. Rows 2–5 build only valid
prefixes, so they never create most of those; but the number of *valid* strings is itself exponential
(1,430 at `n = 8`, 208,012 at `n = 12`), and every solution has to produce all of them.

---

## 1. Build everything, then filter

**Source:** [`GenerateBalancedParenthesesBruteForce.java`](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesBruteForce.java)

The version that needs no insight at all. A valid answer is a string of length `2n` made of two
characters, so build every such string and keep the ones that balance.

```java
private static final char[] BOTH = { '(', ')' };

public static ImmutableList<String> generate(int n) {
    List<String> list = newArrayList();
    Queue<String> queue = newLinkedList();
    queue.add("");
    while (!queue.isEmpty()) {
        String s = queue.poll();
        if (s.length() == 2 * n) {       // the ONLY test, and only on a finished string
            if (isBalanced(s)) {
                list.add(s);
            }
            continue;
        }
        for (char c : BOTH) {            // both always go back on -- nothing is refused
            queue.add(s + c);
        }
    }
    return newList(list);
}
```

A string comes off the front. If it is not yet full length, it goes back on twice — once with each
character — and if it is full length, it is tested and the loop moves on. **Nothing is tested while
it is being built**, which is the only thing separating this from the four versions below.

The test is a running count that must never go negative and must end at zero:

```java
private static boolean isBalanced(String s) {
    int open = 0;
    for (int i = 0; i < s.length(); i++) {
        open += s.charAt(i) == '(' ? 1 : -1;
        if (open < 0) {                  // a ) with nothing open -- dead, whatever follows
            return false;
        }
    }
    return open == 0;                    // and nothing may be left unclosed
}
```

**`open < 0` is the whole validity rule**, and it is the same rule every other version uses — a `)`
appearing when nothing is open. The difference is only *when* it gets asked. Here it is asked once,
at the end, about a finished string; everywhere else it is asked while the string is still being
built.

**How much it throws away, and why it passes anyway:**

| n | strings built | of them valid | |
|---|---|---|---|
| 2 | 16 | 2 | 12.5% |
| 4 | 256 | 14 | 5.5% |
| 6 | 4,096 | 132 | 3.2% |
| 8 | 65,536 | 1,430 | **2.2%** |

At the ceiling it discards 97.8% of what it built and still finishes in about 5 ms. **The surviving
fraction keeps shrinking**, so it degrades fast past the limit: ~380 ms at `n = 10`, ~2.3 s at
`n = 11`, and an `OutOfMemoryError` at `n = 12`, where the others are still well under a tenth of a
second.

It is worth writing once to see how little being clever buys on an input this small — and worth not
submitting, because it is the one version that stops working the moment the constraint moves.

## 2. Branch first, reject on arrival

**Source:** [`GenerateBalancedParenthesesRecursive.java`](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesRecursive.java)

The most direct reading of the problem. At every step there are two things you could write, so
write both, and let the call that receives an impossible state notice and return.

```java
public static List<String> parentheses(int n) {
    List<String> list = new ArrayList<>();
    dfs(n, 0, 0, "", list);          // both counts start at ZERO
    return list;
}

private static void dfs(int n, int open, int close, String s, List<String> list) {
    if (open > n || close > n || open < close) {
        return;                          // the reject -- but the call was already made
    }
    if (open == n && close == n) {
        list.add(s);
        return;
    }
    dfs(n, open + 1, close, s + "(", list);   // both calls happen unconditionally
    dfs(n, open, close + 1, s + ")", list);   // s + "(" is a NEW string; the caller's is untouched
}
```

`open` and `close` count what has been placed so far. Three things make a state impossible and all
three are checked on entry: more than `n` of either bracket, or `open < close`, which means a `)`
was written with nothing to match it.

**This is correct and it is the version to understand first**, because the two recursive calls at
the bottom say plainly what the search is: *at every position, either bracket.* Everything below is
that idea with the waste removed.

**What it costs:** the string `s + "("` is a fresh `String` every time, since Java strings cannot be
modified. At `n = 8` that is 10,974 strings created and discarded on the way to 1,430 answers.

## 3. Guard before recursing

**Source:** [`GenerateBalancedParenthesesGuarded.java`](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesGuarded.java)

The same search with the tests moved from the top of the callee to the call site. This version also
counts *down* — `open` and `close` are how many of each are still available, so it starts at
`(n, n)` and finishes at `(0, 0)`.

```java
public static ImmutableList<String> generate(int n) {
    List<String> list = newArrayList();
    dfs(n, n, "", list);             // both counts start at N
    return newList(list);
}

private static void dfs(int open, int close, String s, List<String> list) {
    if (open == 0 && close == 0) {
        list.add(s);
        return;
    }
    if (open > 0) {                      // the same tests as §2, moved to the call site
        dfs(open - 1, close, s + '(', list);
    }
    if (close > open) {                  // so the impossible call is never made at all
        dfs(open, close - 1, s + ')', list);
    }
}
```

**`close > open` is the same rule as `open < close` in §2, restated for counting down.** With
`open` and `close` meaning *remaining*, having more closes left than opens left is what says an
opening bracket is still unmatched — so a `)` is legal exactly then.

**The two `if`s around the recursive calls are what removes the dead calls.** Counted against §2,
which makes both calls and rejects the bad ones on arrival:

| n | answers | §2 calls | of them dead on arrival | §3 calls |
|---|---|---|---|---|
| 4 | 14 | 101 | 37 (36%) | 64 |
| 6 | 132 | 987 | 362 (36%) | 625 |
| 8 | 1430 | 10,975 | 4,058 (36%) | 6,917 |
| 10 | 16,796 | 131,407 | 48,908 (37%) | 82,499 |

**Roughly one call in three does nothing but return**, and the proportion is stable as `n` grows — so
it is a constant factor rather than a change of complexity, which is why §2 still passes and why the
fix is still worth making.

Notice also what is *not* here:
there is no rejection test at the top at all. §2 needs one because it can be called into an
impossible state; this version cannot be, so the test has nowhere to go.

**Counting down or counting up is a preference, not a difference** — the call counts are identical
either way. What matters is that the test happens before the call.

## 4. One shared buffer with an undo

**Source:** [`GenerateBalancedParenthesesBacktracking.java`](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesBacktracking.java)

Rows 1 and 2 both create a new string at every step. That is unnecessary: only one path is being
explored at a time, so one buffer can be shared by every call, provided each call puts back exactly
what it added.

```java
public static List<String> generate(int n) {
    StringBuilder sb = new StringBuilder();
    List<String> list = new ArrayList<>();
    dfs(n, 0, 0, sb, list);
    return list;
}

private static void dfs(int n, int open, int close, StringBuilder sb, List<String> list) {
    if (sb.length() == 2 * n) {
        list.add(sb.toString());
        return;
    }
    if (open < n) {
        sb.append('(');                          // choose
        dfs(n, open + 1, close, sb, list);       // explore -- open + 1 is a value, undoes itself
        sb.deleteCharAt(sb.length() - 1);        // UNCHOOSE -- the buffer is shared, so by hand
    }
    if (close < open) {
        sb.append(')');
        dfs(n, open, close + 1, sb, list);
        sb.deleteCharAt(sb.length() - 1);
    }
}
```

**Measured: zero intermediate strings.** The only strings created are the 1,430 answers themselves,
where rows 1, 2, 3 and 5 create 65,536, 10,974, 6,916 and 6,916 discarded ones on the way.

**Two details in this version are worth being precise about.**

`sb.length() == 2 * n` needs no balance check beside it. The two guards make an unbalanced string
impossible to build in the first place — a `)` is only ever appended when there is an unmatched `(`
to consume — so reaching full length already implies the string is well formed.

**`open` and `close` are never undone, and the `StringBuilder` always is.** That is not an
inconsistency. `open + 1` is computed into the argument, so this call's own `open` was never
modified and is still correct when the recursive call returns. The builder is one object shared by
every call, so a change made deep in the tree is visible everywhere and has to be reversed by hand.
The rule that follows: **a value passed as an argument undoes itself; a shared object must be put
back.**

## 5. The same search without recursion

**Source:** [`GenerateBalancedParenthesesIterative.java`](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesIterative.java)

The recursion can be replaced by a queue of partial answers, each carrying its own string and its
own two counts.

```java
public static ImmutableList<String> generate(int n) {
    List<String> list = newArrayList();
    Queue<Parentheses> queue = newLinkedList();
    queue.add(new Parentheses("", 0, 0));    // the state a recursive call would have carried
    while (!queue.isEmpty()) {
        Parentheses p = queue.poll();
        if (p.open == n && p.close == n) {
            list.add(p.str);
        } else {
            if (p.open < n) {                // same test as §3's open > 0
                String s = p.str + "(";
                int open = p.open + 1;
                queue.add(new Parentheses(s, open, p.close));
            }
            if (p.open > p.close) {          // same test as its close > open
                String s = p.str + ")";
                int close = p.close + 1;
                queue.add(new Parentheses(s, p.open, close));
            }
        }
    }
    return newList(list);                    // no undo anywhere - each state owns its string
}

private static final class Parentheses {     // what the call stack held for free
    private String str;
    private int open;
    private int close;

    public Parentheses(String str, int open, int close) {
        this.str = str;
        this.open = open;
        this.close = close;
    }
}
```

```
   generate(2)  ->  [(()), ()()]
   generate(3)  ->  [((())), (()()), (())(), ()(()), ()()()]
```

The two `if`s are the same two rules as §2 and §3. Nothing about the search changed — only where the
unfinished work is kept.

**This is the version to reach for when recursion is not available or the depth is a worry**, and
for this problem neither applies: the depth is `2n`, which is 16 at the ceiling.

**What it costs is memory.** The recursion holds one path at a time; the queue holds every partial
answer at the same level simultaneously. Measured at its widest, that is one entry per answer —
1,430 live strings at `n = 8`. Because it explores level by level, the queue is at its fullest just
before the answers start coming out.

**A stack would work exactly as well** and would explore depth-first, keeping the queue narrow. The
choice of a queue here is what makes the memory grow with the answer count.

---

## A worked trace

**Source:** [`GenerateBalancedParenthesesBacktracking.java`](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/GenerateBalancedParenthesesBacktracking.java)

`n = 2`, traced through §4. `open` and `close` are the counts placed so far; `sb` is the shared
buffer at the moment the call is entered.

| step | sb on entry | open | close | length == 4? | `open < n` | `close < open` | action |
|---|---|---|---|---|---|---|---|
| 1 | `` | 0 | 0 | no | 0 < 2 yes | 0 < 0 no | append `(` |
| 2 | `(` | 1 | 0 | no | 1 < 2 yes | 0 < 1 yes | append `(` |
| 3 | `((` | 2 | 0 | no | 2 < 2 **no** | 0 < 2 yes | append `)` |
| 4 | `(()` | 2 | 1 | no | 2 < 2 **no** | 1 < 2 yes | append `)` |
| 5 | `(())` | 2 | 2 | **yes** | — | — | **record `(())`** |
| | `(()` | | | | | | undo, returning from the `)` branch |
| | `((` | | | | | | undo, returning from the `)` branch |
| | `(` | | | | | | undo, returning from the `(` branch |
| 6 | `()` | 1 | 1 | no | 1 < 2 yes | 1 < 1 no | append `(` |
| 7 | `()(` | 2 | 1 | no | 2 < 2 **no** | 1 < 2 yes | append `)` |
| 8 | `()()` | 2 | 2 | **yes** | — | — | **record `()()`** |
| | `()(` · `()` · `(` · `` | | | | | | four more undos, unwinding to the start |

Two answers, and the buffer is empty again when the outermost call returns — every `append` was
matched by a `deleteCharAt`.

**Two things in that table are worth stopping on.**

**Step 3.** Both opening brackets have been used, so `open < n` is false and the only legal move is
to close. Nothing rejects a bad choice here, because no bad choice was offered — which is the whole
difference between §1 and §2.

**Between steps 5 and 6, three undos happen in a row.** Recording an answer does not restart the
search from the top; it returns into the call that made it, which finishes its own loop and returns
in turn. The buffer shrinks one character per return until a call is reached that still has a branch
left to try — here that is the call holding `(`, which has done its `(` branch and now takes its
`)` branch. **A trace that jumps straight from one answer to the next hides the only part of
backtracking that is actually hard to see.**

---

## Traps

**The finish test written as `open == close`.**
It looks right — the brackets balance, so the string is done. It is the test for *balanced*, and
every balanced prefix on the way down is also balanced, including the empty one.

```
   n=1   wanted 1    got 2    "", "()"
   n=2   wanted 2    got 4    "", "(())", "()", "()()"
   n=3   wanted 5    got 9
   n=4   wanted 14   got 23
```

**Instead:** test that the whole string has been built — `sb.length() == 2 * n`, or equivalently
`close == n`. Balanced is not the same as finished.

**The `StringBuilder` without the undo.**
Removing `deleteCharAt` leaves a version that compiles, runs, throws nothing, and returns **exactly
one answer for every `n`** — the first one it reaches. The buffer never shrinks, so once it hits
length `2n` every subsequent call returns immediately.

```
   n=2   wanted 2    got 1    "(())"
   n=3   wanted 5    got 1    "((()))"
   n=4   wanted 14   got 1    "(((())))"
```

**Instead:** every `append` on a shared buffer needs its matching `deleteCharAt` on the way out. A
result of exactly one answer is the signature of this bug — not zero, not a crash.

**Reassigning the `StringBuilder` parameter.**
Writing `sb = new StringBuilder()` inside the base case looks like it resets the shared buffer. It
does nothing at all: the parameter holds a copy of the reference, so reassigning it points the local
copy at a new object the caller never sees. Java passes references by value — a method can mutate
the object it was handed but cannot replace it for the caller.

**Instead:** delete the line. It is harmless only by accident, and if it worked the way it reads it
would destroy the search.

**`sb.remove(...)`.**
`StringBuilder` has no `remove` method — that is `List`. It is `deleteCharAt(int)`.

**Instead:** `sb.deleteCharAt(sb.length() - 1)`. This one is caught by the compiler, which is the
best case; the three above are not.

---

## Related

- [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) — checks one string
  instead of generating all of them, and the same `open < close` rule is what makes it fail fast.
- [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)
  — the same shape with the choices coming from a map rather than being the same two every time.
- [301. Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/) —
  the reverse: a string is given and brackets are deleted to make it valid.

All source files:
[`backtracking/additionalstates/`](../src/main/java/com/svetanis/algorithms/backtracking/additionalstates/)
