public class Sorting {

    public enum SortKey {
        REQUEST_ID, TIMESTAMP, DISTANCE, VERIFICATION
    }

    private static int compare(DispatchRecord a, DispatchRecord b, SortKey key) {
        switch (key) {
            case REQUEST_ID:
                return Integer.compare(a.getRequestId(), b.getRequestId());
            case TIMESTAMP:
                return a.getTimestamp().compareTo(b.getTimestamp());
            case DISTANCE:
                return Double.compare(a.getDistanceKm(), b.getDistanceKm());
            case VERIFICATION:
                return Boolean.compare(a.isVerifiedDriver(), b.isVerifiedDriver());
            default:
                return 0;
        }
    }

    public static void selectionSort(DispatchRecord[] a, SortKey key) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < a.length; j++) {
                if (compare(a[j], a[min], key) < 0) {
                    min = j;
                }
            }

            if (min != i) {
                swap(a, i, min);
            }
        }
    }

    public static void insertionSort(DispatchRecord[] a, SortKey key) {
        for (int i = 1; i < a.length; i++) {
            DispatchRecord current = a[i];
            int j = i - 1;

            while (j >= 0 && compare(current, a[j], key) < 0) {
                a[j + 1] = a[j];
                j--;
            }

            a[j + 1] = current;
        }
    }

    public static void mergeSort(DispatchRecord[] a, SortKey key) {
        if (a.length < 2) return;

        DispatchRecord[] temp = new DispatchRecord[a.length];
        mergeSort(a, temp, 0, a.length - 1, key);
    }

    private static void mergeSort(DispatchRecord[] a, DispatchRecord[] temp,
                                  int left, int right, SortKey key) {
        if (left >= right) return;

        int middle = left + (right - left) / 2;
        mergeSort(a, temp, left, middle, key);
        mergeSort(a, temp, middle + 1, right, key);
        merge(a, temp, left, middle, right, key);
    }

    private static void merge(DispatchRecord[] a, DispatchRecord[] temp,
                              int left, int middle, int right, SortKey key) {
        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            if (compare(a[i], a[j], key) <= 0) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= middle) temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];

        for (int p = left; p <= right; p++) {
            a[p] = temp[p];
        }
    }

    public static void quickSort(DispatchRecord[] a, SortKey key) {
        quickSort(a, 0, a.length - 1, key);
    }

    private static void quickSort(DispatchRecord[] a, int low, int high, SortKey key) {
        if (low >= high) return;

        int pivotIndex = partition(a, low, high, key);
        quickSort(a, low, pivotIndex - 1, key);
        quickSort(a, pivotIndex + 1, high, key);
    }

    private static int partition(DispatchRecord[] a, int low, int high, SortKey key) {
        // Middle-element pivot reduces the risk of worst-case recursion
        // on already sorted or reverse-sorted benchmark data.
        int pivotSource = low + (high - low) / 2;
        swap(a, pivotSource, high);

        DispatchRecord pivot = a[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(a[j], pivot, key) <= 0) {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, high);
        return i + 1;
    }

    public static void quickSortWithTrace(DispatchRecord[] a, SortKey key,
                                          StringBuilder trace) {
        trace.append("Quick Sort Trace\n");
        quickSortTrace(a, 0, a.length - 1, key, trace);
    }

    private static void quickSortTrace(DispatchRecord[] a, int low, int high,
                                       SortKey key, StringBuilder trace) {
        if (low >= high) return;

        int pivotIndex = partitionWithTrace(a, low, high, key, trace);
        quickSortTrace(a, low, pivotIndex - 1, key, trace);
        quickSortTrace(a, pivotIndex + 1, high, key, trace);
    }

    private static int partitionWithTrace(DispatchRecord[] a, int low, int high,
                                          SortKey key, StringBuilder trace) {
        int pivotSource = low + (high - low) / 2;
        swap(a, pivotSource, high);

        DispatchRecord pivot = a[high];
        int i = low - 1;

        trace.append("Partition low=").append(low)
                .append(", high=").append(high)
                .append(", pivot=Request ")
                .append(pivot.getRequestId()).append("\n");

        for (int j = low; j < high; j++) {
            if (compare(a[j], pivot, key) <= 0) {
                i++;
                swap(a, i, j);

                trace.append("  swap index ").append(i)
                        .append(" and ").append(j)
                        .append(" -> pivot position still pending\n");
            }
        }

        swap(a, i + 1, high);

        trace.append("  final pivot swap: index ")
                .append(i + 1).append(" and ").append(high).append("\n");

        return i + 1;
    }

    private static void swap(DispatchRecord[] a, int i, int j) {
        DispatchRecord temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
