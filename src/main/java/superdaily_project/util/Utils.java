package superdaily_project.util;

import superdaily_project.pojo.LimitConfig;

/**
 * Created by arun on 04/09/20.
 */
public final class Utils {
    public static Long calculateOffsetTime(Long currentTimestamp, LimitConfig limitConfig) throws Exception {   // because this method always returns in milliseconds. So just conversions are required.
        switch (limitConfig.getGranularity()) {
            case minute:
                return currentTimestamp - 60 * 1000;
            case second:
                return currentTimestamp - 1000;
        }
        throw new Exception("Unknown Granularity");
    }

}
