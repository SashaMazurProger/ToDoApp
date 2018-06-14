package sample.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.*;
import sample.viewmodel.TaskViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private ObservableList<TaskViewModel> taskData = FXCollections.observableArrayList();

    private Stage primaryStage;
    private BorderPane rootLayout;

    private IBackuper mTxtBackuper;
    private ITaskRepository mDatTaskRepository;
    private final static String TXT_FILE_PATH = "\\tasks_txt.txt";
    private static final String DAT_FILE_PATH = "\\tasks_dat.dat";

    public Main() {

//        taskData.add(new TaskViewModel("Learn English", "Lingualeo,Youtube"));
//        taskData.add(new TaskViewModel("Learn Java", "Youtube"));
//        taskData.add(new TaskViewModel("Learn Android", "Udacity's cources"));
//        taskData.add(new TaskViewModel("Learn Git", "Youtube"));

        mDatTaskRepository = new TaskDatRepository(DAT_FILE_PATH);
        mTxtBackuper = new TaskTxtBackuper(TXT_FILE_PATH);
    }

    private void loadTaskData() {
        taskData.clear();
        for (Task task : mDatTaskRepository.load()) {
            taskData.add(TaskViewModel.convertTask(task));
        }
    }

    private void saveTaskData() {
        List<Task> forSave = new ArrayList<>(taskData.size());

        for (TaskViewModel task : taskData) {
            forSave.add(task.convertToTask());
        }
        mDatTaskRepository.save(forSave);
    }

    public ObservableList<TaskViewModel> getTaskData() {
        return taskData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ToDoApp");
        this.primaryStage.getIcons().add(new Image("file:resources/images/app_icon.png"));


        loadTaskData();
        initRootLayout();
        showTaskOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showTaskOverview() {
        try {
            // Load task overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("TaskOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set task overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            TaskOverviewController taskOverviewController = loader.getController();
            taskOverviewController.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showTaskEditDialog(TaskViewModel viewModel) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("TaskEditDialog.fxml"));
            AnchorPane rootEditLayout = (AnchorPane) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Edit task...");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            Scene scene = new Scene(rootEditLayout);
            stage.setScene(scene);

            TaskEditDialogController editDialogController = loader.getController();
            editDialogController.setTaskModel(viewModel);
            editDialogController.setDialogStage(stage);
            stage.showAndWait();

            return editDialogController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @FXML
    private void handleSaveToTxtFile() {

        System.out.println("is null:"+(mTxtBackuper==null?"yes":"no"));
        System.out.println("is null:"+(mDatTaskRepository==null?"yes":"no"));
        mTxtBackuper.save(mDatTaskRepository.load());
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Saved");
        alert.setContentText("Saved in " + TXT_FILE_PATH);
        alert.show();
    }


    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void deleteTask(TaskViewModel model) {
        taskData.remove(model);
        saveTaskData();
    }

    public void setIsDoneTask(TaskViewModel selectedTask, boolean selected) {
        selectedTask.setIsDone(selected);
        saveTaskData();
    }

    public void taskEdited(TaskViewModel selectedTask) {
        saveTaskData();
    }

    public void addNewTaskViewModel(TaskViewModel newTaskViewModel) {
        taskData.add(newTaskViewModel);
        saveTaskData();
    }

    public void handleCloseApp(ActionEvent actionEvent) {

    }
}
