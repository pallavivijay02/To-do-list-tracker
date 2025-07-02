package project;

import java.util.ArrayList;
import java.util.Scanner;

class Task {
    int id;
    String title;
    String description;
    String dueDate;
    String priority;
    boolean isCompleted;

    public Task(int id, String title, String description, String dueDate, String priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
    }

    public void displayTask() {
        System.out.println("\nTask ID      : " + id);
        System.out.println("Title        : " + title);
        System.out.println("Description  : " + description);
        System.out.println("Due Date     : " + dueDate);
        System.out.println("Priority     : " + priority);
        System.out.println("Status       : " + (isCompleted ? "Completed" : "Pending"));
    }
}

public class project1 {
    static ArrayList<Task> taskList = new ArrayList<>();
    static int taskId = 1;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n========== To-Do Task Tracker ==========");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> updateTask();
                case 4 -> deleteTask();
                case 5 -> completeTask();
                case 6 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    public static void addTask() {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Description: ");
        String desc = sc.nextLine();
        System.out.print("Enter Due Date (e.g., 2025-07-01): ");
        String due = sc.nextLine();
        System.out.print("Enter Priority (High/Medium/Low): ");
        String prio = sc.nextLine();

        Task newTask = new Task(taskId++, title, desc, due, prio);
        taskList.add(newTask);
        System.out.println("✅ Task added successfully.");
    }

    public static void viewTasks() {
        if (taskList.isEmpty()) {
            System.out.println("⚠️ No tasks found.");
        } else {
            for (Task t : taskList) {
                t.displayTask();
            }
        }
    }

    public static void updateTask() {
        System.out.print("Enter Task ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Task task = findTaskById(id);
        if (task == null) {
            System.out.println("❌ Task not found.");
            return;
        }

        System.out.print("Enter new Title (leave blank to keep unchanged): ");
        String title = sc.nextLine();
        if (!title.isEmpty()) task.title = title;

        System.out.print("Enter new Description: ");
        String desc = sc.nextLine();
        if (!desc.isEmpty()) task.description = desc;

        System.out.print("Enter new Due Date: ");
        String due = sc.nextLine();
        if (!due.isEmpty()) task.dueDate = due;

        System.out.print("Enter new Priority: ");
        String prio = sc.nextLine();
        if (!prio.isEmpty()) task.priority = prio;

        System.out.println("✅ Task updated successfully.");
    }

    public static void deleteTask() {
        System.out.print("Enter Task ID to delete: ");
        int id = sc.nextInt();
        Task task = findTaskById(id);

        if (task != null) {
            taskList.remove(task);
            System.out.println("✅ Task deleted successfully.");
        } else {
            System.out.println("❌ Task not found.");
        }
    }

    public static void completeTask() {
        System.out.print("Enter Task ID to mark as completed: ");
        int id = sc.nextInt();
        Task task = findTaskById(id);

        if (task != null) {
            task.isCompleted = true;
            System.out.println("✅ Task marked as completed.");
        } else {
            System.out.println("❌ Task not found.");
        }
    }

    public static Task findTaskById(int id) {
        for (Task t : taskList) {
            if (t.id == id) return t;
        }
        return null;
    }
}

