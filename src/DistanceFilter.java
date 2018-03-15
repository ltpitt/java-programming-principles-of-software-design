public class DistanceFilter {
    private Location location;
    private double distance;

    DistanceFilter(Location loc, double dist) {
        location = loc;
        distance = dist;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < distance;
    }

}
