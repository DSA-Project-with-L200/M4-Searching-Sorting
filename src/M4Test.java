import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class M4Test {

    public static void main(String[] args) throws Exception {
        DispatchRecord[] records = sampleRecords();

        System.out.println("=== M4 SEARCHING & SORTING ENGINE ===");

        int linear = Searching.linearSearchByRequestId(records, 105);
        System.out.println("Linear Search: request 105 found at index " + linear);

        DispatchRecord[] binaryData = copy(records);
        Sorting.mergeSort(binaryData, Sorting.SortKey.REQUEST_ID);

        StringBuilder binaryTrace = new StringBuilder();
        int binary = Searching.binarySearchByRequestIdWithTrace(
                binaryData, 108, binaryTrace);
        System.out.println(binaryTrace);
        System.out.println("Binary Search result index: " + binary);

        runSortDemo(records, "Selection Sort", 1);
        runSortDemo(records, "Insertion Sort", 2);
        runSortDemo(records, "Merge Sort", 3);
        runQuickSortTrace(records);

        if (args.length > 0 && args[0].equalsIgnoreCase("benchmark")) {
            runBenchmarks();
            System.out.println("\nBenchmark CSV written to: m4_benchmark_results.csv");
        } else {
            System.out.println("\nDemo complete. Run with argument 'benchmark' to generate benchmark CSV.");
        }
    }

    private static void runSortDemo(DispatchRecord[] original, String name, int type) {
        DispatchRecord[] data = copy(original);

        switch (type) {
            case 1:
                Sorting.selectionSort(data, Sorting.SortKey.DISTANCE);
                break;
            case 2:
                Sorting.insertionSort(data, Sorting.SortKey.DISTANCE);
                break;
            case 3:
                Sorting.mergeSort(data, Sorting.SortKey.DISTANCE);
                break;
            default:
                return;
        }

        System.out.println("\n" + name + " by distance:");
        print(data);
    }

    private static void runQuickSortTrace(DispatchRecord[] original) {
        DispatchRecord[] data = copy(original);
        StringBuilder trace = new StringBuilder();

        Sorting.quickSortWithTrace(data, Sorting.SortKey.REQUEST_ID, trace);

        System.out.println("\nQuick Sort trace:");
        System.out.println(trace);
        System.out.println("Quick Sort result:");
        print(data);
    }

    private static DispatchRecord[] sampleRecords() {
        return new DispatchRecord[] {
            new DispatchRecord(105, "2026-08-24 10:05", "Pentagon", "UG Hospital", 2.4, true),
            new DispatchRecord(101, "2026-08-24 09:10", "Commonwealth", "Night Market", 3.2, true),
            new DispatchRecord(108, "2026-08-24 11:20", "Limann", "UG Hospital", 1.7, true),
            new DispatchRecord(103, "2026-08-24 09:45", "JSB", "Balme Library", 4.1, false),
            new DispatchRecord(110, "2026-08-24 12:00", "Pentagon", "UG Hospital", 2.0, true),
            new DispatchRecord(102, "2026-08-24 09:20", "Akuafo", "Commonwealth", 5.3, false)
        };
    }

    private static DispatchRecord[] copy(DispatchRecord[] source) {
        DispatchRecord[] result = new DispatchRecord[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = source[i];
        }
        return result;
    }

    private static void print(DispatchRecord[] records) {
        for (DispatchRecord r : records) {
            System.out.println(r);
        }
    }

    private static void runBenchmarks() throws IOException {
        int[] sizes = {100, 500, 1000, 5000, 10000, 50000};

        File output = new File("m4_benchmark_results.csv");
        FileWriter writer = new FileWriter(output);

        writer.write("Algorithm,N,TimeNanoseconds\n");

        for (int n : sizes) {
            DispatchRecord[] base = generateRecords(n);

            benchmark(writer, "Selection Sort", base, 1);
            benchmark(writer, "Insertion Sort", base, 2);
            benchmark(writer, "Merge Sort", base, 3);
            benchmark(writer, "Quick Sort", base, 4);
        }

        writer.close();
    }

    private static void benchmark(FileWriter writer, String name,
                                  DispatchRecord[] base, int algorithm)
            throws IOException {

        // Selection and insertion sort become expensive at large N.
        // They are still measured to satisfy the comparison requirement.
        DispatchRecord[] data = copy(base);

        long start = System.nanoTime();

        switch (algorithm) {
            case 1:
                Sorting.selectionSort(data, Sorting.SortKey.REQUEST_ID);
                break;
            case 2:
                Sorting.insertionSort(data, Sorting.SortKey.REQUEST_ID);
                break;
            case 3:
                Sorting.mergeSort(data, Sorting.SortKey.REQUEST_ID);
                break;
            case 4:
                Sorting.quickSort(data, Sorting.SortKey.REQUEST_ID);
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm");
        }

        long end = System.nanoTime();
        long elapsed = end - start;

        writer.write(name + "," + data.length + "," + elapsed + "\n");
    }

    private static DispatchRecord[] generateRecords(int n) {
        DispatchRecord[] records = new DispatchRecord[n];

        for (int i = 0; i < n; i++) {
            int id = n - i;
            double distance = ((i * 37) % 500) / 10.0;

            records[i] = new DispatchRecord(
                    id,
                    "2026-08-24 10:00",
                    "Campus Node " + (i % 50),
                    (i % 3 == 0) ? "UG Hospital" : "Campus Destination",
                    distance,
                    i % 2 == 0
            );
        }

        return records;
    }
}
