package sample.model;

import java.io.Serializable;
import java.lang.annotation.Annotation;

public class Task implements Serializable{


    private String name;
    private long time;
    private String description;
    private boolean isDone;
    private long createTimeStamp;

    public Task() {

    }

    public Task(String name, long time, String description, boolean isDone, long createTimeStamp) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.isDone = isDone;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
