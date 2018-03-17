import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {

    String source = "src\\data\\nov20quakedatasmall.atom";
    //String source = "src\\data\\nov20quakedata.atom";
    //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

    public EarthQuakeClient2() {
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 

        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter mf = new MagnitudeFilter(4.0, 5.0);
        Filter df = new DepthFilter(-35000.0, -12000.0);
        Location tokyo = new Location(35.42, 139.43);
        Filter disf = new DistanceFilter(tokyo, 10000000.00);
        Filter phrf = new PhraseFilter("end","Japan");

        //ArrayList<QuakeEntry> magnitudeFiltered  = filter(list, mf);
        //ArrayList<QuakeEntry> depthFiltered = filter(magnitudeFiltered, df);

        ArrayList<QuakeEntry> distanceFiltered  = filter(list, disf);
        ArrayList<QuakeEntry> phraseFiltered = filter(distanceFiltered, phrf);


        for (QuakeEntry qe: phraseFiltered) {
            System.out.println(qe);
        }


    }

    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        MatchAllFilter maf = new MatchAllFilter();
        Filter mf = new MagnitudeFilter(0.0, 2.0);
        Filter df = new DepthFilter(-100000.0, -10000.0);
        Filter pf = new PhraseFilter("starts","a");
        maf.addFilter(mf);
        maf.addFilter(df);
        maf.addFilter(pf);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : list) {
            if (maf.satisfies(qe)) {
                answer.add(qe);
            }
        }
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        System.out.println("Found " + list.size() + " quakes that match that criteria");
    }


    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        Location tulsa = new Location(36.1314, -95.9372);
        MatchAllFilter maf = new MatchAllFilter();
        Filter mf = new MagnitudeFilter(0.0, 3.0);
        Filter df = new DistanceFilter(tulsa, 10000000);
        Filter pf = new PhraseFilter("starts","Ca");
        maf.addFilter(mf);
        maf.addFilter(df);
        maf.addFilter(pf);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : list) {
            if (maf.satisfies(qe)) {
                answer.add(qe);
            }
        }
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        System.out.println("Found " + list.size() + " quakes that match that criteria");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
        EarthQuakeClient2 client = new EarthQuakeClient2();
        client.testMatchAllFilter2();
    }

}
