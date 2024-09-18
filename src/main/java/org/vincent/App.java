package org.vincent;

import org.vincent.service.TaskHandler;
import org.vincent.service.TaskOperations;

import java.io.IOException;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) throws IOException {
        //catch input errors
        //if no args is entered
        if(args.length == 0){
            System.out.println("Usage: taskTracker-1.0-SNAPSHOT.jar <command> [arguments]");
            return;
        }

        //if none on the operations is entered
        if(Arrays.stream(TaskOperations.values())
                .noneMatch(ops -> args[0].toUpperCase().equalsIgnoreCase(ops.name() ) ) ){
            System.out.println("Usage: taskTracker-1.0-SNAPSHOT.jar <ADD | DELETE | UPDATE | MARK | LIST> [arguments]");
            return;
        }

        TaskHandler.handler(args);

        //writes the list of task to the json file
        TaskHandler.save();
    }

}
