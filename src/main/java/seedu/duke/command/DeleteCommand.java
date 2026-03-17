package seedu.duke.command;

import seedu.duke.exception.ModuleSyncException;
import seedu.duke.module.ModuleBook;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int displayIndex;

    public DeleteCommand(int displayIndex) {
        this.displayIndex = displayIndex;
    }

    @Override
    public void execute(ModuleBook moduleBook, Storage storage, Ui ui) throws ModuleSyncException {
        assert displayIndex > 0 : "Display index must be strictly positive";
        assert displayIndex <= moduleBook.totalTaskCount() : "Display index out of bounds";
        
        Task deletedTask = moduleBook.removeTaskByDisplayIndex(displayIndex);
        storage.save(moduleBook);
        ui.showTaskDeleted(deletedTask, moduleBook.totalTaskCount());
    }
}
