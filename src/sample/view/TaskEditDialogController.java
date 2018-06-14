package sample.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.utils.DateUtils;
import sample.utils.TextUtils;
import sample.viewmodel.TaskViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskEditDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField timeField;
    @FXML
    private Button okBtn;

    private Stage dialogStage;
    private TaskViewModel taskModel;
    private boolean isOkClicked = false;


    @FXML
    public void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTaskModel(TaskViewModel taskModel) {
        this.taskModel = taskModel;

        nameField.setText(taskModel.getName());
        descriptionTextArea.setText(taskModel.getDescription());
        timeField.setText(DateUtils.format(taskModel.getTaskTime()));
    }

    public boolean isOkClicked() {
        return isOkClicked;
    }

    @FXML
    private void handleOk(ActionEvent actionEvent) {
        if (isInputValid()) {
            if (taskModel != null) {
                taskModel.setName(nameField.getText());
                taskModel.setDescription(descriptionTextArea.getText());
                taskModel.setTaskTime(DateUtils.parse(timeField.getText()));

            }
            isOkClicked = true;
            dialogStage.close();
        }

    }

    private boolean isInputValid() {
        String errorMessage = "";
        StringBuilder stringBuilder = new StringBuilder();

        if (TextUtils.isNullOrEmpty(nameField.getText())) {
            stringBuilder.append("Name requires\n");
        }

        if (TextUtils.isNullOrEmpty(timeField.getText())) {
            stringBuilder.append("Time requires\n");
        } else if (!DateUtils.isCorrectStringDate(timeField.getText())) {
            stringBuilder.append("Incorrect time\n");
        }

//        if(TextUtils.isNullOrEmpty(descriptionTextArea.getText())){
//            stringBuilder.append("Description requires\n");
//        }

        errorMessage = stringBuilder.toString();
        if (errorMessage != null && !errorMessage.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Error");
            alert.setHeaderText("Please input correct info");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }

        return true;
    }

    @FXML
    private void handleCancel(ActionEvent actionEvent) {
        isOkClicked = false;
        dialogStage.close();
    }
}
