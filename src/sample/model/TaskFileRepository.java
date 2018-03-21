package sample.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskFileRepository implements ITaskRepository {

    private String mFilePath;

    public TaskFileRepository(String filePath) {
        mFilePath = filePath;
    }

    @Override
    public void save(List<Task> tasks) throws IOException {
        //put each tack to string
        String stringTasks;
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.toString());
        }
        stringTasks = stringBuilder.toString();

        //open file
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(mFilePath));

            //save string
            bufferedWriter.write(stringTasks);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //close file
        finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }

    @Override
    public List<Task> load() throws IOException {
        //open file
        ArrayList<Task> tasks = new ArrayList<>();
        String stringTasks = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(mFilePath));

            //pull data
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
            stringTasks = stringBuilder.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //close file
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();

            }
        }

        //return data
        Task task = new Task();
        task.setName(stringTasks);
        tasks.add(task);
        return tasks;
    }
}
