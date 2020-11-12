package superdaily_project.pojo.external;


import superdaily_project.pojo.LimitConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by arun on 04/09/20.
 */
public class ServiceLimit {
    private String service;
    private Map<String, LimitConfig> globalLimits;
    private List<ApiLimit> apiLimits;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Map<String, LimitConfig> getGlobalLimits() {
        return globalLimits;
    }

    public void setGlobalLimits(Map<String, LimitConfig> globalLimits) {
        this.globalLimits = globalLimits;
    }

    public List<ApiLimit> getApiLimits() {
        return apiLimits;
    }

    public void setApiLimits(List<ApiLimit> apiLimits) {
        this.apiLimits = apiLimits;
    }
}
