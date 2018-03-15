import java.util.*;

public class LargestQuakes {
    //String source = "src\\data\\nov20quakedatasmall.atom";
    String source = "src\\data\\nov20quakedata.atom";

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        //System.out.println("Found " + list.size() + " quakes that match that criteria");
        getLargest(list, 5);
    }

    public int indexOfLargest(ArrayList<QuakeEntry> list) {
        int maxIndex = -1;
        double maxMagnitude = -1;
        for (int i=0; i < list.size(); i++) {
            QuakeEntry currentQuake = list.get(i);
            double currentMagnitude = currentQuake.getMagnitude();
            if (currentMagnitude > maxMagnitude) {
                maxIndex = i;
                maxMagnitude = currentMagnitude;
            }
        }
        return maxIndex;
    }

    public void getLargest(ArrayList<QuakeEntry> list, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(list);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int i=0; i < howMany; i++) {
            int maxIndex = indexOfLargest(copy);
            answer.add(copy.get(maxIndex));
            System.out.println(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
    }

    public static void main(String[] args) {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes();
    }

}
