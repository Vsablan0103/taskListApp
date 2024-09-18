package org.vincent.service;

import org.vincent.Model.Status;
import org.vincent.Model.Task;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TaskHandler {
    static List<Task> taskList = new ArrayList<>();
    static File taskFile = new File("tasks.json");
    static {
        //creates the json file if it didn't exist
        if(!taskFile.exists()){
            try {
                taskFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //if the file is not empty, it will read the json and convert it to list
        if(taskFile.length() > 0){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(taskFile));
                String fileContent = reader.readLine();
                fileContent = fileContent.substring(1,fileContent.length() - 2);
                for(String task : fileContent.split("},")){
                    taskList.add(Task.convertFromJson(task));
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //writes the list of tasks into json file
    public static void save() throws FileNotFoundException {
        try(PrintWriter writer = new PrintWriter(taskFile)){
            writer.print(taskList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //calls the method depending on the command-line argument
    public static void handler(String[] args){
        switch (TaskOperations.valueOf(args[0].toUpperCase())){
            case ADD -> add(args[1]);
            case UPDATE -> update(Integer.parseInt(args[1]), args[2]);
            case DELETE -> deleteById(Integer.parseInt(args[1]));
            case MARK -> {
                String status = args[2].replace(" ","_");
                if(Arrays.stream(Status.values())
                        .noneMatch(s -> status
                                .equalsIgnoreCase(s.name()))){
                    System.out.println("Usage: taskTracker-1.0-SNAPSHOT.jar MARK [id] [IN PROGRESS | DONE] ");
                    return;
                }
                markStatus(Integer.parseInt(args[1]),Status.valueOf(status));
            }
            case LIST -> {
                if(args.length == 1){
                    list();
                }
                else if (args.length == 2){
                    String status = args[1].replace(" ","_");
                    if(Arrays.stream(Status.values())
                            .anyMatch(s -> status.equalsIgnoreCase(s.name()))){
                        list(Status.valueOf(status.toUpperCase()));
                    }else{
                        System.out.println("Usage: taskTracker-1.0-SNAPSHOT.jar LIST [IN PROGRESS | DONE] ");
                    }
                }
            }
        }
    }

    public static void add(String desc){
        System.out.println("Adding now");
        Task newTask = new Task(desc);

        taskList.add(newTask);
        System.out.println("Successfully added :" + newTask);
    }

    public static void update(int id, String updatedDesc){
        Task toBeUpdated = findById(id);
        System.out.println("Updating task: " + toBeUpdated);
        toBeUpdated.setDesc(updatedDesc);
        toBeUpdated.setUpdatedAt(LocalDate.now());
        System.out.println("Newly updated task: " + toBeUpdated);
    }

    public static Task findById(int id){
        return taskList.stream().filter(t -> t.getId() == id).findFirst().orElseThrow();
    }

    public static void deleteById(int id){
        Task toBeDeleted = findById(id);
        if(taskList.remove(toBeDeleted)){
            System.out.println("Successfully deleted");
        }
    }

    public static void markStatus(int id, Status status){
        Task tobeUpdated = findById(id);
        tobeUpdated.setStatus(status);
        tobeUpdated.setUpdatedAt(LocalDate.now());

        System.out.println("Status of task changed. " + tobeUpdated);
    }

    public static void list(){
        taskList.forEach(System.out::println);
    }

    public static void list(Status status){
        taskList.stream().filter( t -> t.getStatus() == status).forEach(System.out::println);
    }

}
