# Task Tracker

A simple project that uses Java core to manage and track your tasks, and saves it in a JSON file. You can add, delete, update, and list your tasks using this app
This app will be utilized in the command-line interface (CLI).

## Features

* **Add** : Add any task using the app.
* **Delete** : You can also delete any tasks that is present in the list.
* **Update** : Update any task in the list.
* **Mark** : To track the status of your tasks in the app, you can mark it if it is ***To do***, ***In Progress***, or ***DONE***
* **List** : Finally, you can list all of the tasks you have, or you can filter it by status.

## How to Use

Download the executable jar file in this repository

### Using the app

Use the following syntax when running commands in the command line: 
`taskTracker-1.0-SNAPSHOT.jar <command> [arguments]`

#### Commands and applicable arguments
* ADD - `taskTracker-1.0-SNAPSHOT.jar ADD "sample task"`
* UPDATE - `taskTracker-1.0-SNAPSHOT.jar UPDATE 1 "new description"`
  * Attributes
    * ID of the task
    * New description  
* DELETE - `taskTracker-1.0-SNAPSHOT.jar DELETE 1`
  * Attributes
     * ID of the task
* MARK - `taskTracker-1.0-SNAPSHOT.jar MARK 1 "IN PROGRESS`
  * Attributes
    * ID of the task
    * Status [IN PROGRESS, DONE, TO DO]
* LIST - `taskTracker-1.0-SNAPSHOT.jar LIST "IN PROGRESS"`
  * Attributes
    * Status [IN PROGRESS, DONE, TO DO] (Optional)


_This is a solution to a project challenge in_ [roadmap.sh](https://roadmap.sh/projects/task-tracker)
