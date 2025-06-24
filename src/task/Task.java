package task;

public class Task {
    private String title;
    private String dueDate;
    private boolean completed;

    public Task(String title, String dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return title + " | " + dueDate + " | " + (completed ? "Yes" : "No");
    }
}
