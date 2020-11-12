package superdaily_project.pojo;


import superdaily_project.constants.Granularity;

/**
 * Created by arun on 04/09/20.
 */
public class LimitConfig {
    private Granularity granularity;
    private int limit;

    public Granularity getGranularity() {
        return granularity;
    }

    public void setGranularity(Granularity granularity) {
        this.granularity = granularity;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
