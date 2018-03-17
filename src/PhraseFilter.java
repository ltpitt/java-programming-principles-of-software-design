public class PhraseFilter implements Filter {
    private String where;
    private String phrase;

    public PhraseFilter(String whe, String phr) {
        where = whe;
        phrase = phr;
    }

    public String getName() {
        return "PhraseFilter";
    }

    public boolean satisfies(QuakeEntry qe) {

        switch (where) {
            case "start": return qe.getInfo().startsWith(phrase);
            case "end":  return qe.getInfo().endsWith(phrase);
            case "any":  return qe.getInfo().contains(phrase);
            default:     return qe.getInfo().contains(phrase);
        }

    }

}
