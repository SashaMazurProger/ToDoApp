package sample.model;

import java.lang.annotation.Annotation;

public class Task {


    enum TaskStatuses {
        Vykonano, NeVykonano, Vidmineno;

    }


    private String name;
    private long time;
    private String description;
    private TaskStatuses status;
    private long createTimeStamp;

    public Task() {

    }

    public Task(String name, long time, String description, TaskStatuses status, long createTimeStamp) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.status = status;
        this.createTimeStamp = createTimeStamp;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public long getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(long createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public TaskStatuses getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatuses status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
