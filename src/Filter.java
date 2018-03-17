
/**
 * This interface allows to Filter Earthquake data
 * 
 * @author Davide Nastri
 * @version 3/15/2018
 */
public interface Filter
{
    public  boolean satisfies(QuakeEntry qe);

    public String getName();

}
