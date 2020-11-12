package superdaily_project;

import superdaily_project.client.RateLimiterClient;
import superdaily_project.pojo.RateLimiterRequest;
import superdaily_project.protocol.RateLimitingProcessor;

import java.util.concurrent.TimeUnit;

/**
 * Created by arun on 04/09/20.
 */
public class Driver {
    static private final RateLimiterClient rateLimiterClient = new RateLimiterClient();

    public static void main(String[] args) throws InterruptedException {
        RateLimitingProcessor processor = rateLimiterClient.getRateLimitingProcessor();

        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(processor.validateForRateLimiting(getRequestObject()).getResponseData());


    }

    private static RateLimiterRequest getRequestObject() {
        return new RateLimiterRequest("OrderService", "POST", "CreateOrder");
    }

}
