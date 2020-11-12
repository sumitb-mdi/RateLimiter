package superdaily_project.configuration;


import org.apache.commons.lang3.StringUtils;
import superdaily_project.configuration.convertor.Convertor;
import superdaily_project.configuration.convertor.JsonConvertor;
import superdaily_project.exception.ReaderException;
import superdaily_project.pojo.external.ExternalConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by arun on 04/09/20.
 */
public class CommandLineConfigurationReader implements ConfigurationReader {

    final BufferedReader bufferedReader;
    final Convertor convertor;

    public CommandLineConfigurationReader() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.convertor = new JsonConvertor();
    }

    public ExternalConfiguration readConfiguration() throws ReaderException {
        StringBuffer finalString = new StringBuffer();
        String input;
        try {
            while ((input = bufferedReader.readLine()) != null) {
                if (StringUtils.isEmpty(input)) {
                    break;
                }
                finalString.append(input);
            }

            return this.convertToConfig(finalString.toString());

        } catch (IOException e) {
            throw new ReaderException("Error in reading configuration via command line.", e);
        }
    }

    private ExternalConfiguration convertToConfig(String input) {
        return this.convertor.convert(input);
    }


}
