package superdaily_project.pojo.external;

import java.util.List;

/**
 * Created by arun on 04/09/20.
 */
public class ExternalConfiguration {
    private List<ServiceLimit> serviceLimits;

    public List<ServiceLimit> getServiceLimits() {
        return serviceLimits;
    }

    public void setServiceLimits(List<ServiceLimit> serviceLimits) {
        this.serviceLimits = serviceLimits;
    }
}
