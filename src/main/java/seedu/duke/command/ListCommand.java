package seedu.duke.command;

import seedu.duke.module.ModuleBook;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ListCommand extends Command {
    private final String moduleCode;

    public ListCommand() {
        this.moduleCode = null;
    }

    public ListCommand(String moduleCode) {
        if (moduleCode == null) {
            this.moduleCode = null;
        } else {
            this.moduleCode = moduleCode.toUpperCase();
        }
    }

    @Override
    public void execute(ModuleBook moduleBook, Storage storage, Ui ui) {
        if (moduleCode == null) {
            ui.showTaskList(moduleBook);
        } else {
            ui.showTaskList(moduleBook, moduleCode);
        }
    }
}
