package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleBook;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

public class AddTodoCommand extends Command {
    private final String moduleCode;
    private final String description;

    public AddTodoCommand(String moduleCode, String description) {
        this.moduleCode = moduleCode;
        this.description = description;
    }

    @Override
    public void execute(ModuleBook moduleBook, Storage storage, Ui ui) throws DukeException {
        Module module = moduleBook.getOrCreate(moduleCode);
        Task task = module.addTodo(description);
        storage.save(moduleBook);
        ui.showTaskAdded(module, task, moduleBook.totalTaskCount());
    }
}
