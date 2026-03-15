package seedu.duke.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.duke.exception.DukeException;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public Task addTodo(String moduleCode, String description) throws DukeException {
        if (description == null || description.trim().isEmpty()) {
            throw new DukeException("Task description cannot be empty.");
        }
        Task todo = new Todo(moduleCode, description.trim());
        tasks.add(todo);
        return todo;
    }

    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> asUnmodifiableList() {
        return Collections.unmodifiableList(tasks);
    }
}
