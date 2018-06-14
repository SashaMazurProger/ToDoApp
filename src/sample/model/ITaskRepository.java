package sample.model;

import java.io.IOException;
import java.util.List;

public interface ITaskRepository {
    public void save(List<Task> tasks);
    public List<Task> load();
}
