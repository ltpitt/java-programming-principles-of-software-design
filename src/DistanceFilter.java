public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;

    DistanceFilter(Location loc, double dist) {
        location = loc;
        maxDistance = dist;
    }

    public String getName() {
        return "DistanceFilter";
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDistance;
    }

}
