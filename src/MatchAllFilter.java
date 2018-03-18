import java.util.ArrayList;

public class MatchAllFilter implements Filter {

    private ArrayList<Filter> filters = new ArrayList<Filter>();

    public MatchAllFilter() {

    }

    public String getName() {
        String usedFilters = "";
        for (Filter filter : filters) {
            usedFilters += filter.getName() + " ";
        }
        return usedFilters;
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter currentFilter : filters) {
            if (!currentFilter.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

}
