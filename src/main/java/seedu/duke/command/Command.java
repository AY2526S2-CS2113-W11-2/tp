package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.module.ModuleBook;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public abstract class Command {
    public abstract void execute(ModuleBook moduleBook, Storage storage, Ui ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
