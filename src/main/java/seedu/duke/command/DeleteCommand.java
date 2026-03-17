package seedu.duke.command;

import seedu.duke.exception.ModuleSyncException;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;
import seedu.duke.module.ModuleBook;
import seedu.duke.storage.Storage;

public class DeleteCommand extends Command {
    private final int displayIndex;

    public DeleteCommand(int taskNumber) {
        // Keep the 1-based index since ModuleBook uses it
        this.displayIndex = taskNumber;
    }

    @Override
    public void execute(ModuleBook moduleBook, Storage storage, Ui ui) throws ModuleSyncException {
        Task removedTask = moduleBook.deleteTaskByDisplayIndex(displayIndex);

        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + removedTask.formatForList(displayIndex));

        ui.showMessage("Now tracking " + moduleBook.totalTaskCount() + " task(s) across modules.");
    }
}