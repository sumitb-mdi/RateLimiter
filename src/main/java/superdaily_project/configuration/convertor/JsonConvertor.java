package superdaily_project.configuration.convertor;

import com.fasterxml.jackson.databind.ObjectMapper;
import superdaily_project.pojo.external.ExternalConfiguration;

import java.io.IOException;

/**
 * Created by arun on 04/09/20.
 */
public class JsonConvertor implements Convertor {
    private final ObjectMapper objectMapper;

    public JsonConvertor() {
        this.objectMapper = new ObjectMapper();
    }

    public ExternalConfiguration convert(String s) {
        try {
            return objectMapper.readValue(s, ExternalConfiguration.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
