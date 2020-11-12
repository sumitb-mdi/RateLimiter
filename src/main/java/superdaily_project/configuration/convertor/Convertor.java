package superdaily_project.configuration.convertor;


import superdaily_project.pojo.external.ExternalConfiguration;

/**
 * Created by arun on 04/09/20.
 */
public interface Convertor {
    ExternalConfiguration convert(String s);
}
