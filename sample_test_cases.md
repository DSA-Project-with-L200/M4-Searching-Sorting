# M4 Test Cases

| ID | Test | Expected Result |
|---|---|---|
| T01 | Linear Search for existing request ID | Correct index returned |
| T02 | Linear Search for missing request ID | -1 returned |
| T03 | Binary Search for existing sorted request ID | Correct index returned |
| T04 | Binary Search for missing request ID | -1 returned |
| T05 | Selection Sort by distance | Records ordered ascending |
| T06 | Insertion Sort by distance | Records ordered ascending |
| T07 | Merge Sort by request ID | Records ordered ascending |
| T08 | Quick Sort by request ID | Records ordered ascending |
| T09 | Empty array sort | No error |
| T10 | Single-record sort | Record remains unchanged |
| T11 | Duplicate sort keys | Records remain correctly ordered |
| T12 | Benchmark N=100 | CSV row produced |
| T13 | Benchmark N=50000 | CSV row produced |

## Manual verification

For every sorting algorithm, inspect the output and confirm that each
record's selected key is <= the next record's key.

For Binary Search, verify that the input array is sorted by request ID
before calling the algorithm.
