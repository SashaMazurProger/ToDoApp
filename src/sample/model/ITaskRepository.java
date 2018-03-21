package sample.model;

import java.io.IOException;
import java.util.List;

public interface ITaskRepository {
    public void save(List<Task> tasks) throws IOException;
    public List<Task> load() throws IOException;
}
