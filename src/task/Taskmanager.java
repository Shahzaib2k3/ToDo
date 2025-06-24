package task;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
public class Taskmanager {
    private static List<Task> tasks = new ArrayList<>();

    public static void addTask(String title, String dueDate) {
        tasks.add(new Task(title, dueDate));
    }

    public static void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public static void markCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markCompleted();
        }
    }

    public static void sortByDate() {
        tasks.sort(Comparator.comparing(Task::getDueDate));
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static void clearAllTasks() {
        tasks.clear();
    }
}