import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    public TitleLastAndMagnitudeComparator() {
    }

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1Info = q1.getInfo();
        String q1LastWord = q1Info.substring(q1Info.lastIndexOf(" ")+1);
        String q2Info = q2.getInfo();
        String q2LastWord = q2Info.substring(q2Info.lastIndexOf(" ")+1);
        int compareLastWordResult = q1LastWord.compareTo(q2LastWord);
        if (compareLastWordResult != 0){
            return compareLastWordResult;
        }
        return Double.compare(q1.getMagnitude(), q2.getMagnitude());
    }

}
