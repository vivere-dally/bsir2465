package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.repository.ArrayTaskList;
import tasks.model.Task;
import tasks.utils.TasksOperations;

import java.util.Date;

public class TasksService {

    private ArrayTaskList tasks;

    public TasksService(ArrayTaskList tasks){
        this.tasks = tasks;
    }


    public ObservableList<Task> getObservableList(){
        return FXCollections.observableArrayList(tasks.getAll());
    }
    public String getIntervalInHours(Task task){
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);//hh:MM
    }
    public String formTimeUnit(int timeUnit){
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10) sb.append("0");
        if (timeUnit == 0) sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }


    public int parseFromStringToSeconds(String stringTime){//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        return (hours * DateService.MINUTES_IN_HOUR + minutes) * DateService.SECONDS_IN_MINUTE;
    }

    public Iterable<Task> filterTasks(Date start, Date end){
        TasksOperations tasksOps = new TasksOperations(getObservableList());
        return tasksOps.incoming(start,end);
    }

    public void addTask(String description, Date startDate, Date endDate, int interval, boolean active) {
        if (description.length() < 3 || description.length() > 255) {
            throw new IllegalArgumentException("Description must have a length between 3 and 255");
        }

        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Start Date must be before End Date");
        }

        Task task = new Task(description, startDate, endDate, interval);
        task.setActive(active);
        this.tasks.add(task);
    }
}
