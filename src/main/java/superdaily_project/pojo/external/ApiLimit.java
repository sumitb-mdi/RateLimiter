package superdaily_project.pojo.external;


import superdaily_project.pojo.LimitConfig;

import java.util.Map;

/**
 * Created by arun on 04/09/20.
 */
public class ApiLimit {
    private String api;
    private Map<String, LimitConfig> methods;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Map<String, LimitConfig> getMethods() {
        return methods;
    }

    public void setMethods(Map<String, LimitConfig> methods) {
        this.methods = methods;
    }
}
