package seedu.duke.command;

import seedu.duke.module.ModuleBook;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(ModuleBook moduleBook, Storage storage, Ui ui) {
        ui.showFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
