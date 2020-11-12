package superdaily_project.storage;


import superdaily_project.pojo.ProcessorData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arun on 04/09/20.
 */
public class InMemoryKeyValueStorageService implements KeyValueStorageService {
    private final Map<String, ProcessorData> inMemoryStore;

    public InMemoryKeyValueStorageService() {
        this.inMemoryStore = new HashMap();
    }

    public void saveValue(String key, ProcessorData o) {
        this.inMemoryStore.put(key, o);
    }

    public ProcessorData getValue(String key) {
        return this.inMemoryStore.get(key);
    }
}
