package org.vincent.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Task {
    private static int count = 1;

    private String desc;
    private int id;
    private Status status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Task(String desc) {
        this.desc = desc;
        this.id = ++count;
        this.status = Status.TO_DO;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Task() {
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    //the tasks property in JSON format is split into String array,
    //then is used to set the respective properties on the new Task object
    public static Task convertFromJson(String jSON){
        Task newTask = new Task();
        for(String param : jSON.substring(2).split(",")){
            String[] keyVal = Arrays.stream(param.split(":"))
                    .map(s -> s.replace("\"","").trim())
                    .toArray(String[]::new);

            switch (keyVal[0]){
                case "desc" -> newTask.setDesc(keyVal[1]);
                case "id" -> {
                    int currentID = Integer.parseInt(keyVal[1]);
                    newTask.setId(currentID);
                    if(currentID > count){
                        count = currentID;
                    }
                }
                case "status" -> newTask.setStatus(Status.valueOf(keyVal[1]));
                case "createdAt" -> newTask.setCreatedAt(LocalDate.parse(keyVal[1],DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                case "updatedAt" -> newTask.setUpdatedAt(LocalDate.parse(keyVal[1],DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

        }

        return newTask;
    }

    @Override
    public String toString() {
        return "{" +
                "\"desc\": \"" + desc + '\"' +
                ", \"id\" : " + id +
                ", \"status\": \"" + status + '\"' +
                ", \"createdAt\": \"" + createdAt + '\"' +
                ", \"updatedAt\": \"" + updatedAt + '\"' +
                '}';
    }


}
