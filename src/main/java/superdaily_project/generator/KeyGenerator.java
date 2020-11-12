package superdaily_project.generator;

import superdaily_project.constants.Constants;
import superdaily_project.exception.KeyGenerationException;
import superdaily_project.pojo.RateLimiterRequest;

import static superdaily_project.constants.Constants.RateLimiterType.GLOBAL;
import static superdaily_project.constants.Constants.RateLimiterType.LOCAL;

/**
 * Created by arun on 04/09/20.
 */
public final class KeyGenerator {
    private static final String CUSTOM_PREFIX = "";

    public static String generateKey(RateLimiterRequest request, Constants.RateLimiterType type) throws KeyGenerationException {
        switch (type) {
            case GLOBAL:
                return CUSTOM_PREFIX + ":" + GLOBAL.toString() + ":" + request.getServiceName() + ":" + request.getApiMethod();

            case LOCAL:
                return CUSTOM_PREFIX + LOCAL.toString() + ":" + request.getServiceName() + ":" + request.getApiName() + ":" + request.getApiMethod();

            default:
                throw new KeyGenerationException("Unknown Type");
        }
    }
}
