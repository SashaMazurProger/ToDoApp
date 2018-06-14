package sample.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import sample.utils.DateUtils;
import sample.viewmodel.TaskViewModel;

public class TaskOverviewController {

    @FXML
    private TableView<TaskViewModel> taskTable;
    @FXML
    private TableColumn<TaskViewModel, String> nameColumn;
    @FXML
    private TableColumn<TaskViewModel, String> timeColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label timeCreateLabel;
    @FXML
    private Label doneLabel;
    @FXML
    private CheckBox doneCheckBox;

    private Main main;

    public TaskOverviewController() {
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        timeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TaskViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TaskViewModel, String> param) {
                String timeString = DateUtils.format(param.getValue().getTaskTime());
                ObservableStringValue stringValue = new SimpleStringProperty(timeString);
                return stringValue;
            }
        });

        showTaskDetails(null);

        taskTable.getSelectionModel()
                .selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> showTaskDetails(newValue)));

    }

    public void showTaskDetails(TaskViewModel model) {
        if (model != null) {
            nameLabel.setText(model.getName());
            timeLabel.setText(DateUtils.format(model.getTaskTime()));
            descriptionLabel.setText(model.getDescription());
            timeCreateLabel.setText(DateUtils.format(model.getTimeStamp()));
            doneLabel.setText(model.isDone() ? "Yes" : "No");
            doneCheckBox.setDisable(false);
            doneCheckBox.setSelected(model.isDone());

        } else {
            nameLabel.setText("");
            timeLabel.setText("");
            descriptionLabel.setText("");
            timeCreateLabel.setText("");
            doneLabel.setText("");
            doneCheckBox.setDisable(true);
        }
    }

    public void setMain(Main main) {
        this.main = main;
        taskTable.setItems(main.getTaskData());
    }

    @FXML
    private void handleDeleteTask(ActionEvent actionEvent) {
        if (isSelectedAnyTask()) {
            main.deleteTask(getSelectedTask());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No selected task");
            alert.setContentText("You should select any task and repeat action");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditTask(ActionEvent actionEvent) {
        if (isSelectedAnyTask()) {
            boolean clickedOk = main.showTaskEditDialog(getSelectedTask());
            if (clickedOk) {
                main.taskEdited(getSelectedTask());
                showTaskDetails(getSelectedTask());
            }
        }
    }

    @FXML
    private void handleCreateTask(ActionEvent actionEvent) {
        TaskViewModel newTaskViewModel = new TaskViewModel();
        boolean okClicked=main.showTaskEditDialog(newTaskViewModel);
        if(okClicked){
            main.addNewTaskViewModel(newTaskViewModel);
        }
    }

    public void handleDoneTask(ActionEvent actionEvent) {
        if (isSelectedAnyTask()) {
            main.setIsDoneTask(getSelectedTask(), doneCheckBox.isSelected());
            showTaskDetails(getSelectedTask());
        }
    }

    private boolean isSelectedAnyTask() {
        return taskTable.getSelectionModel().getSelectedIndex() >= 0;
    }

    private TaskViewModel getSelectedTask() {
        if (!isSelectedAnyTask()) {
            return null;
        }
        return taskTable.getSelectionModel().selectedItemProperty().get();
    }

}
