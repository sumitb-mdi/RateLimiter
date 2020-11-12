package superdaily_project.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arun on 04/09/20.
 */
public class ProcessorData implements Serializable {
    private List<Long> data;

    public ProcessorData(List<Long> data) {
        this.data = data;
    }

    public List<Long> getData() {
        return data;
    }

    public void setData(List<Long> data) {
        this.data = data;
    }
}
