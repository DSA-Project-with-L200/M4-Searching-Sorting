public class Searching {

    // Linear Search: works on unsorted data.
    public static int linearSearchByRequestId(DispatchRecord[] records, int targetId) {
        for (int i = 0; i < records.length; i++) {
            if (records[i].getRequestId() == targetId) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search: records MUST already be sorted by request ID.
    public static int binarySearchByRequestId(DispatchRecord[] records, int targetId) {
        int left = 0;
        int right = records.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int middleId = records[middle].getRequestId();

            if (middleId == targetId) {
                return middle;
            }

            if (middleId < targetId) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    public static int binarySearchByRequestIdWithTrace(
            DispatchRecord[] records, int targetId, StringBuilder trace) {

        int left = 0;
        int right = records.length - 1;
        int step = 1;

        trace.append("Binary Search Trace\n");
        trace.append("Target request ID: ").append(targetId).append("\n");

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int middleId = records[middle].getRequestId();

            trace.append("Step ").append(step++)
                    .append(": left=").append(left)
                    .append(", middle=").append(middle)
                    .append(", right=").append(right)
                    .append(", middleId=").append(middleId)
                    .append("\n");

            if (middleId == targetId) {
                trace.append("Result: FOUND at index ").append(middle).append("\n");
                return middle;
            }

            if (middleId < targetId) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        trace.append("Result: NOT FOUND\n");
        return -1;
    }
}
