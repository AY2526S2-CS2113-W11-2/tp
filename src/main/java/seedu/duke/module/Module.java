package seedu.duke.module;

import seedu.duke.exception.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class Module {
    private final String code;
    private final TaskList taskList = new TaskList();

    public Module(String code) {
        this.code = code.toUpperCase();
    }

    public String getCode() {
        return code;
    }

    public TaskList getTasks() {
        return taskList;
    }

    public Task addTodo(String description) throws DukeException {
        return taskList.addTodo(code, description);
    }
}
