package sample.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDatRepository implements ITaskRepository {
    private final String mDatFilePath;

    public TaskDatRepository(String datFilePath) {
        mDatFilePath = datFilePath;

        File file = new File(mDatFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(List<Task> tasks) {

        if (tasks != null) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(mDatFilePath))) {

                objectOutputStream.writeObject(tasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("No data for save");
        }
    }

    @Override
    public List<Task> load() {

        List<Task> tasks = new ArrayList<>();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(mDatFilePath))) {

            tasks = (ArrayList<Task>) objectInputStream.readObject();

        } catch (EOFException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
