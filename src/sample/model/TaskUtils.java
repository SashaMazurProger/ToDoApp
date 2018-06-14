package sample.model;

import sample.viewmodel.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class TaskUtils {

    public static List<Task> toTaskList(List<TaskViewModel> taskViewModels){

        ArrayList<Task> tasks=null;

        if(taskViewModels==null||taskViewModels.size()==0){
            return tasks;
        }

        tasks=new ArrayList<>();
        for (int i = 0; i < taskViewModels.size(); i++) {

            TaskViewModel taskVM=taskViewModels.get(i);

            Task task=new Task(
                    taskVM.getName(),
                    0,
                    taskVM.getDescription(),
                    taskVM.isDone(),
                    0
            );

            tasks.add(task);
        }
        return tasks;
    }
}
