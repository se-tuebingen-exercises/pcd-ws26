# Exercise 02: Nested Data Parallelism

## Benchmark

Benchmark Futhark against PyTorch on a number of self-selected programs. You can
use the small programs from the lecture, or the larger ones from last week's
homework.

If you have access to a GPU, compile the Futhark programs with `futhark cuda` or
`futhark opencl`, and use `torch.set_default_device('cuda')`.

Remember the best practices from the very first lecture, such as performing
warm-up runs and reporting statistics over multiple executions. We recommend
using `futhark bench` and `torch.utils.benchmark`.

## Parse Parentheses

Given an array of bytes, check that all parentheses `'('` and `')'` are
well-bracketed.

```
def parse_parentheses [n] (characters: [n]u8) : bool
```

For example:

```
let good1 = "()()"
let good2 = "(a())"
let bad1  = "())"
let bad2  = ")("

> parse_parentheses good1
true

> parse_parentheses good2
true

> parse_parentheses bad1
false

> parse_parentheses bad2
false
```

Use file `parse_parentheses.fut` as a starting point and feel free to use
whatever the Futhark standard library offers.

Optional challenge: extend the parser to different kinds of brackets, for
example `({)}` should not be accepted.

## Sparse Matrices

Given a sparse matrix in coordinate list format, compute the mean value of each
row.

```
def mean_coo [k] (rows: [k]i64) (columns: [k]i64) (values: [k]f64) (n: i64) (m: i64) : [n]f64
```

For example:

```
let rows = [0, 0, 1, 2, 2]
let columns = [0, 2, 1, 0, 2]
let values = [1.0, 3.0, 4.0, 2.0, 5.0]

> mean_coo rows columns values 3 3
[1.33, 1.33, 2.33]
```

Use file `sparse_matrix.fut` as a starting point and feel free to use whatever
the Futhark standard library offers.

Optional challenge: given a sparse matrix in compressed sparse row format,
transpose it.

## Radix Sort

Implement a parallel bitwise radix sort in Futhark. The heart of the algorithm
is the following function

```
def radix_split [n] (bits: [n]bool) (values: [n]i64) : [n]i64
```

that sorts the given values according to the given bits: all values at indices
where the bit is `false` go to the left, all values at indices where the bit is
`true` go to the right. Make sure that this sorting is stable.

Then implement

```
def sort_bytes [n] (values: [n]i64) : [n]i64
```

by calling `radix_split` eight times to sort according to increasingly more
significant bits.

Use file `radix_sort.fut` as a starting point and feel free to use whatever the
Futhark standard library offers.

