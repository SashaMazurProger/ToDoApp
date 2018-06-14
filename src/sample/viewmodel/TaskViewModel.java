package sample.viewmodel;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class TaskViewModel {

    private final StringProperty name;
    private final StringProperty description;
    private final BooleanProperty isDone;
    private final ObjectProperty<LocalDateTime> taskTime;
    private final ObjectProperty<LocalDateTime> timeStamp;


    public TaskViewModel() {
        this(null, null);
    }

    //, boolean isDone, LocalDateTime taskTime, long timeStamp
    public TaskViewModel(String name, String description) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.isDone = new SimpleBooleanProperty(true);
        this.taskTime = new SimpleObjectProperty<>(LocalDateTime.now());
        this.timeStamp = new SimpleObjectProperty<>(LocalDateTime.now());
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public boolean isDone() {
        return isDone.get();
    }

    public BooleanProperty isDoneProperty() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone.set(isDone);
    }

    public LocalDateTime getTaskTime() {
        return taskTime.get();
    }

    public ObjectProperty<LocalDateTime> taskTimeProperty() {
        return taskTime;
    }

    public void setTaskTime(LocalDateTime taskTime) {
        this.taskTime.set(taskTime);
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp.get();
    }

    public ObjectProperty<LocalDateTime> timeStampProperty() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp.set(timeStamp);
    }

    @Override
    public String toString() {
        return "TaskViewModel{" +
                "name=" + name +
                ", description=" + description +
                ", isDone=" + isDone +
                ", taskTime=" + taskTime +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
