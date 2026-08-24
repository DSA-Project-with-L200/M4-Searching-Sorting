# M4 — Searching & Sorting Engine

## Project
DCIT 204/308 Joint DSA Semester Project: Campus Dispatch & Optimization System

## Module
M4: Searching & Sorting Engine

## Required algorithms
1. Linear Search
2. Binary Search
3. Selection Sort
4. Insertion Sort
5. Merge Sort
6. Quick Sort

## Project relevance
The M4 engine organizes dispatch records using request ID, timestamp,
distance, and driver verification status. It supports fast lookup and
ordering of campus dispatch records.

## Files
- `DispatchRecord.java` — dispatch record model.
- `Searching.java` — Linear Search and Binary Search.
- `Sorting.java` — Selection, Insertion, Merge and Quick Sort.
- `M4Test.java` — demonstrations, traces and benchmarking.

## Important constraint
The core M4 algorithms do not use `ArrayList`, `LinkedList`, `HashMap`,
`PriorityQueue`, `Stack`, or other Java collection classes.

## Compile and run

From the project folder:

```bash
javac -d out src/*.java
java -cp out M4Test
```

To run the benchmark suite:

```bash
java -cp out M4Test benchmark
```

The program creates:

`m4_benchmark_results.csv`

The benchmark sizes are:

100, 500, 1000, 5000, 10000, 50000

## Complexity

| Algorithm | Average / Typical | Worst Case | Extra Space |
|---|---:|---:|---:|
| Linear Search | O(n) | O(n) | O(1) |
| Binary Search | O(log n) | O(log n) | O(1) |
| Selection Sort | O(n²) | O(n²) | O(1) |
| Insertion Sort | O(n²) | O(n²) | O(1) |
| Merge Sort | O(n log n) | O(n log n) | O(n) |
| Quick Sort | O(n log n) | O(n²) | O(log n) average recursion |

## Binary Search condition
Binary Search must only be used after the records have been sorted by
the same key used by the search.

## Evidence
The test program produces:
- Binary Search trace
- Quick Sort trace
- Benchmark CSV
- Console output demonstrating all algorithms

Do not claim benchmark values before running the program. The actual
nanosecond values depend on the machine and runtime environment.
