package sample.model;

import sample.viewmodel.TaskViewModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskTxtBackuper implements IBackuper {

    private String mFilePath;

    public TaskTxtBackuper(String filePath) {
        mFilePath = filePath;
    }


//    public List<Task> load()  {
//        //open file
//        ArrayList<Task> tasks = new ArrayList<>();
//        String stringTasks = null;
//        BufferedReader bufferedReader = null;
//        try {
//            bufferedReader = new BufferedReader(new FileReader(mFilePath));
//
//            //pull data
//            StringBuilder stringBuilder = new StringBuilder();
//            String line = bufferedReader.readLine();
//            while (line != null) {
//                stringBuilder.append(line);
//                line = bufferedReader.readLine();
//            }
//            stringTasks = stringBuilder.toString();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //close file
//        finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//
//        //return data
//        Task task = new Task();
//        task.setName(stringTasks);
//        tasks.add(task);
//        return tasks;
//    }

    @Override
    public void save(List<Task> tasks) {
        String stringTasks;
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append("\n");
            stringBuilder.append(task.toString());
        }
        stringTasks = stringBuilder.toString();

        //open file
        BufferedWriter bufferedWriter = null;
        try {
            File file=new File(mFilePath);

            if(file.exists())
            {
                file.createNewFile();
            }

            bufferedWriter = new BufferedWriter(new FileWriter(file));

            //save string
            bufferedWriter.write(stringTasks);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //close file
        finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
