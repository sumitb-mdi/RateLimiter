package superdaily_project.configuration;

import superdaily_project.exception.ReaderException;
import superdaily_project.pojo.external.ExternalConfiguration;

/**
 * Created by arun on 04/09/20.
 */
public interface ConfigurationReader {
    ExternalConfiguration readConfiguration() throws ReaderException;
}
