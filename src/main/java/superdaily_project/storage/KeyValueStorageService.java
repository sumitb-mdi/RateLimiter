package superdaily_project.storage;


import superdaily_project.pojo.ProcessorData;

/**
 * Created by arun on 04/09/20.
 */
public interface KeyValueStorageService {
    void saveValue(String key, ProcessorData o);
    ProcessorData getValue(String key);
}
