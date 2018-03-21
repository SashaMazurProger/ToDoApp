package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.model.ITaskRepository;
import sample.model.Task;
import sample.model.TaskFileRepository;

import java.io.IOException;
import java.util.ArrayList;

public class TaskToFile {
    @FXML
    private TextArea textArea;

    @FXML
    private Button buttonToFile;

    @FXML
    private Label labelText;


    private ITaskRepository mTaskRepository;

    public TaskToFile() {
        this.mTaskRepository = new TaskFileRepository("tasks.txt");
    }

    @FXML
    private void handleSaveToFile(){
        String text=textArea.getText();
        ArrayList<Task> tasks=new ArrayList<>();
        Task task=new Task();
        task.setName(text);
        tasks.add(task);
        try {
            mTaskRepository.save(tasks);
        } catch (IOException e) {
            System.out.println("error saving tasks");
            e.printStackTrace();
        }
    }
}
