package sample.model;

import sample.viewmodel.TaskViewModel;

import java.util.List;

public interface IBackuper {

    public void save(List<Task> tasks);
}
