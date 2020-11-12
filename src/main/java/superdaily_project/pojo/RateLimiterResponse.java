package superdaily_project.pojo;


import superdaily_project.constants.Constants;

/**
 * Created by arun on 04/09/20.
 */
public class RateLimiterResponse {
    private boolean isAllowed;
    private String responseData;
    private Constants.RateLimiterType violatedType;

    public RateLimiterResponse(boolean isAllowed, String responseData, Constants.RateLimiterType violatedType) {
        this.isAllowed = isAllowed;
        this.responseData = responseData;
        this.violatedType = violatedType;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public Constants.RateLimiterType getViolatedType() {
        return violatedType;
    }

    public void setViolatedType(Constants.RateLimiterType violatedType) {
        this.violatedType = violatedType;
    }
}
