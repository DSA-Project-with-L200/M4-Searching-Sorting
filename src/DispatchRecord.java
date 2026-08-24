public class DispatchRecord {
    private final int requestId;
    private final String timestamp;
    private final String pickupLocation;
    private final String destination;
    private final double distanceKm;
    private final boolean verifiedDriver;

    public DispatchRecord(int requestId, String timestamp, String pickupLocation,
                          String destination, double distanceKm, boolean verifiedDriver) {
        this.requestId = requestId;
        this.timestamp = timestamp;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.distanceKm = distanceKm;
        this.verifiedDriver = verifiedDriver;
    }

    public int getRequestId() { return requestId; }
    public String getTimestamp() { return timestamp; }
    public String getPickupLocation() { return pickupLocation; }
    public String getDestination() { return destination; }
    public double getDistanceKm() { return distanceKm; }
    public boolean isVerifiedDriver() { return verifiedDriver; }

    @Override
    public String toString() {
        return "Request " + requestId +
                " | " + timestamp +
                " | " + pickupLocation + " -> " + destination +
                " | " + distanceKm + " km" +
                " | verified=" + verifiedDriver;
    }
}
