import play.mvc.EssentialFilter;
import play.filters.cors.CORSFilter;
import play.http.HttpFilters;
import javax.inject.Inject;

/**
 * 
 * @author DeepakShankar
 *
 */
public class Filters implements HttpFilters {

    @Inject
    CORSFilter corsFilter;

    public EssentialFilter[] filters() {
        return new EssentialFilter[] { corsFilter.asJava() };
    }
}