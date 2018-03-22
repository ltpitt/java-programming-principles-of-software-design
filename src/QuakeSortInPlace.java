
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author Davide Nastri
 * @version 3/20/2018
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {

    String source = "src\\data\\earthquakeDataSampleSix2.atom";
    //String source = "src\\data\\nov20quakedatasmall.atom";
    //String source = "src\\data\\nov20quakedata.atom";
    //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

    public QuakeSortInPlace() {
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int passCount = 0;
        for (int i = 0; i < in.size() -1; i++) {
            if (!checkInSortedOrder(in)) {
                onePassBubbleSort(in, i);
                passCount++;
            }   else {
                break;
            }
        }
        System.out.println("Total bubble sort pass number is " + passCount);
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        boolean result = true;
        for (int i = 0; i < quakes.size(); i++) {
            double currentQuakeMagnitude = quakes.get(i).getMagnitude();
            if (i+1 < quakes.size()) {
                double nextQuakeMagnitude = quakes.get(i + 1).getMagnitude();
                if (nextQuakeMagnitude < currentQuakeMagnitude) {
                    result = false;
                }
            }
        }
        return result;
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < quakeData.size() - numSorted; i++) {
            int currentIdx = i;
            int nextIdx = i + 1;
            double currentQuakeMagnitude = quakeData.get(currentIdx).getMagnitude();
            if (i+1 < quakeData.size()) {
                double nextQuakeMagnitude = quakeData.get(i + 1).getMagnitude();
                if (nextQuakeMagnitude < currentQuakeMagnitude) {
                    Collections.swap(quakeData, nextIdx, currentIdx);
                }
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        int passCount = 0;
        for (int i = 0; i < in.size() -1; i++) {
            onePassBubbleSort(in, i);
            passCount++;
        }
        System.out.println("Total bubble sort pass number is " + passCount);
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {

        for (int i=0; i< in.size(); i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry currentQuake = in.get(i);
            QuakeEntry largestDepthQuake = in.get(maxIdx);
            in.set(i,largestDepthQuake);
            in.set(maxIdx,currentQuake);
        }

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

    public static void main(String[] args) {

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        EarthQuakeParser parser = new EarthQuakeParser();
        QuakeSortInPlace quakeSortInPlace = new QuakeSortInPlace();
        ArrayList<QuakeEntry> list  = parser.read(quakeSortInPlace.source);

        /*
        System.out.println("Magnitude sorted data for "+list.size()+" quakes");
        quakeSortInPlace.sortByMagnitude(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }

        System.out.println("Depth sorted data for "+list.size()+" quakes");
        quakeSortInPlace.sortByLargestDepth(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }
        */
        //quakeSortInPlace.onePassBubbleSort(list, 0);
        quakeSortInPlace.sortByMagnitudeWithBubbleSort(list);
        //quakeSortInPlace.sortByMagnitudeWithBubbleSortWithCheck(list);
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        //System.out.println("List is sorted is " + quakeSortInPlace.checkInSortedOrder(list));
    }

}
