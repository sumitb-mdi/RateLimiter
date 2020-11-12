package superdaily_project.protocol;


import superdaily_project.constants.Constants;
import superdaily_project.generator.KeyGenerator;
import superdaily_project.pojo.LimitConfig;
import superdaily_project.pojo.ProcessorData;
import superdaily_project.pojo.RateLimiterRequest;
import superdaily_project.pojo.RateLimiterResponse;
import superdaily_project.pojo.external.ApiLimit;
import superdaily_project.pojo.external.ExternalConfiguration;
import superdaily_project.pojo.external.ServiceLimit;
import superdaily_project.storage.KeyValueStorageService;
import superdaily_project.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by arun on 04/09/20.
 */
public class SlidingWindowProcessor extends RateLimitingProcessor {

    public SlidingWindowProcessor(KeyValueStorageService keyValueStorageService, ExternalConfiguration
            externalConfiguration) {
        super(keyValueStorageService, externalConfiguration);
    }

    @Override
    public RateLimiterResponse validateForRateLimiting(RateLimiterRequest request) {
        boolean isAllowed = false;
        String responseData = "";
        Constants.RateLimiterType violatedType = null;
        if (validateGlobalLimit(request)) {
            if (validateApiLimit(request)) {
                isAllowed = true;
                responseData = "Allowed";
            } else {
                violatedType = Constants.RateLimiterType.LOCAL;
                responseData = "Rate Limited";
            }
        } else {
            violatedType = Constants.RateLimiterType.GLOBAL;
            responseData = "Rate Limited";
        }
        return new RateLimiterResponse(isAllowed,  responseData, violatedType);
    }

    @Override
    public boolean validateGlobalLimit(RateLimiterRequest request) {
        try {
            String key = KeyGenerator.generateKey(request, Constants.RateLimiterType.GLOBAL);
            LimitConfig limitConfig = this.getGlobalLimitFor(request, this.getExternalConfiguration());
            return processLimits(limitConfig, key);
        } catch (Exception e) {
            // TODO : Handle exception as per business requirements
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean validateApiLimit(RateLimiterRequest request) {
        try {
            String key = KeyGenerator.generateKey(request, Constants.RateLimiterType.LOCAL);
            LimitConfig limitConfig = this.getLocalLimitFor(request, this.getExternalConfiguration());
            return processLimits(limitConfig, key);
        } catch (Exception e) {
            // TODO : Handle exception as per business requirements
            e.printStackTrace();
        }
        return false;
    }

    private boolean processLimits(LimitConfig limitConfig, String key) throws Exception {
        if (limitConfig == null) return true; // Considering if config is not present, then don't apply rate limiting
        Long currentTimestamp = new Date().getTime();
        int maxSize = limitConfig.getLimit();
        long offsetTime = 0;
        offsetTime = Utils.calculateOffsetTime(currentTimestamp, limitConfig);

        return this.process(key, currentTimestamp, maxSize, offsetTime);
    }



    private LimitConfig getGlobalLimitFor(RateLimiterRequest request, ExternalConfiguration externalConfiguration) {
        ServiceLimit serviceLimit = findServiceLimit(externalConfiguration, request.getServiceName());
        if (serviceLimit != null) {
            Map<String, LimitConfig> globalLimits = serviceLimit.getGlobalLimits();
            return globalLimits.get(request.getApiMethod());
        } else {
            return null;
        }
    }

    private LimitConfig getLocalLimitFor(RateLimiterRequest request, ExternalConfiguration externalConfiguration) {
        ServiceLimit serviceLimit = findServiceLimit(externalConfiguration, request.getServiceName());
        if (serviceLimit != null) {
            List<ApiLimit> apiLimits = serviceLimit.getApiLimits();
            for (ApiLimit apiLimit : apiLimits) {
                if (request.getApiName().equalsIgnoreCase(apiLimit.getApi())) {
                    return apiLimit.getMethods().get(request.getApiMethod());
                }
            }
        }
        return null;
    }

    private ServiceLimit findServiceLimit(ExternalConfiguration externalConfiguration, String serviceName) {
        if (externalConfiguration.getServiceLimits() != null) {
            for (ServiceLimit serviceLimit : externalConfiguration.getServiceLimits()) {
                if (serviceLimit.getService().equalsIgnoreCase(serviceName)) {
                    return serviceLimit;
                }
            }
        }
        return null;
    }



    private boolean process(String key, Long currentTimestamp, int maxSize, Long offsetTime) {
        ProcessorData processorData = this.getKeyValueStorageService().getValue(key);
        if (processorData != null) {
            List<Long> accessedTimeSeries = processorData.getData();
            List<Long> newAccessedTimeSeries = this.removeOlderAccessTime(accessedTimeSeries, offsetTime);

            if (newAccessedTimeSeries.size() >= maxSize) {
                return false;
            } else {
                newAccessedTimeSeries.add(currentTimestamp);
                processorData.setData(newAccessedTimeSeries);
                return true;
            }
        } else {
            List<Long> data = new ArrayList<Long>();
            data.add(currentTimestamp);
            processorData = new ProcessorData(data);
            this.getKeyValueStorageService().saveValue(key, processorData);
            return true;
        }
    }

    private List<Long> removeOlderAccessTime(List<Long> acccessTimeSeries, Long timeOffset) {
        List<Long> newAccessList = new ArrayList<Long>();
        for (Long time : acccessTimeSeries) {
            if (time > timeOffset) {
                newAccessList.add(time);
            }
        }
        return newAccessList;
    }


}
