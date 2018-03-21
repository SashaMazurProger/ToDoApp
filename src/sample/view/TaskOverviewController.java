package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
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

    private Main main;

    public TaskOverviewController() {
    }

    @FXML
    private void initialize(){
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().taskTimeProperty().asString());
    }

    public void setMain(Main main) {
        this.main = main;
        taskTable.setItems(main.getTaskData());
    }
}
