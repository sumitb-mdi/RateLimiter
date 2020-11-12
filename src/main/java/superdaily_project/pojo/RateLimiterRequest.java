package superdaily_project.pojo;

/**
 * Created by arun on 04/09/20.
 */
public class RateLimiterRequest {
    private String serviceName;
    private String apiMethod;
    private String apiName;

    public RateLimiterRequest(String serviceName, String apiMethod, String apiName) {
        this.serviceName = serviceName;
        this.apiMethod = apiMethod;
        this.apiName = apiName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
