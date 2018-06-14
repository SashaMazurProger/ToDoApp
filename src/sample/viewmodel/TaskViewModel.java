package sample.viewmodel;

import javafx.beans.property.*;
import sample.model.Task;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalField;

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
        this.isDone = new SimpleBooleanProperty(false);
        this.taskTime = new SimpleObjectProperty<>(LocalDateTime.now());
        this.timeStamp = new SimpleObjectProperty<>(LocalDateTime.now());
    }

    public TaskViewModel(String name, String description, boolean done, LocalDateTime targetTime, LocalDateTime stamp) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.isDone = new SimpleBooleanProperty(done);
        this.taskTime = new SimpleObjectProperty<>(targetTime);
        this.timeStamp = new SimpleObjectProperty<>(stamp);
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

    public static TaskViewModel convertTask(Task task) {
        if (task != null) {
            LocalDateTime target = Instant.ofEpochMilli(task.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            LocalDateTime stamp = Instant.ofEpochMilli(task.getCreateTimeStamp())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            return new TaskViewModel(
                    task.getName(),
                    task.getDescription(),
                    task.isDone(),
                    target,
                    stamp
            );
        }
        return null;
    }

    public Task convertToTask() {
        return new Task(
                getName(),
                getTaskTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                getDescription(),
                isDone(),
                getTimeStamp().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        );
    }
}
