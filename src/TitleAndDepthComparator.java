import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    public TitleAndDepthComparator() {
    }

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int compareTitleResult = q1.getInfo().compareTo(q2.getInfo());
        if (compareTitleResult != 0){
            return compareTitleResult;
        }
        return Double.compare(q1.getDepth(), q2.getDepth());
    }

}
