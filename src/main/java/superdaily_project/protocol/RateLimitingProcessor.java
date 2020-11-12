package superdaily_project.protocol;


import superdaily_project.pojo.RateLimiterRequest;
import superdaily_project.pojo.RateLimiterResponse;
import superdaily_project.pojo.external.ExternalConfiguration;
import superdaily_project.storage.KeyValueStorageService;

/**
 * Created by arun on 04/09/20.
 */
public abstract class RateLimitingProcessor {
    private final KeyValueStorageService keyValueStorageService;
    private final ExternalConfiguration externalConfiguration;

    public abstract RateLimiterResponse validateForRateLimiting(RateLimiterRequest request);
    public abstract boolean validateGlobalLimit(RateLimiterRequest request);
    public abstract boolean validateApiLimit(RateLimiterRequest request);

    RateLimitingProcessor(KeyValueStorageService keyValueStorageService, ExternalConfiguration externalConfiguration) {
        this.keyValueStorageService = keyValueStorageService;
        this.externalConfiguration = externalConfiguration;
    }

    KeyValueStorageService getKeyValueStorageService() {
        return keyValueStorageService;
    }

    ExternalConfiguration getExternalConfiguration() {
       return externalConfiguration;
    }

}
