package superdaily_project.client;

import superdaily_project.configuration.CommandLineConfigurationReader;
import superdaily_project.configuration.ConfigurationReader;
import superdaily_project.exception.ReaderException;
import superdaily_project.pojo.external.ExternalConfiguration;
import superdaily_project.protocol.RateLimitingProcessor;
import superdaily_project.protocol.SlidingWindowProcessor;
import superdaily_project.storage.InMemoryKeyValueStorageService;
import superdaily_project.storage.KeyValueStorageService;

/**
 * Created by arun on 04/09/20.
 *
 * To access our module.
 */
public class RateLimiterClient {
    private RateLimitingProcessor rateLimitingProcessor;


    public RateLimiterClient() {
        try {

            // Go via class by class.  Tell that these things are required for initializing the client.
            // All these configurations are extensible.
            ConfigurationReader configurationReader = this.getConfigurationReader();
            ExternalConfiguration externalConfiguration = configurationReader.readConfiguration();
            KeyValueStorageService keyValueStorageService = this.getKeyValueStorageService();
            this.rateLimitingProcessor = this.getRateLimitingProcessor(keyValueStorageService, externalConfiguration);
        } catch (Exception e) {
            //
        }
    }


    private RateLimitingProcessor getRateLimitingProcessor( KeyValueStorageService service, ExternalConfiguration configuration) {
        // Based on some logic
        return new SlidingWindowProcessor(service, configuration);
    }


    private ConfigurationReader getConfigurationReader() {
        // Based on some logic
        return new CommandLineConfigurationReader();
    }

    private KeyValueStorageService getKeyValueStorageService() {
        // Based on some logic (Use that flag to resolve whether to use InMemory or DB storage )
        return new InMemoryKeyValueStorageService();
    }

    public RateLimitingProcessor getRateLimitingProcessor() {
        return rateLimitingProcessor;
    }

}


















