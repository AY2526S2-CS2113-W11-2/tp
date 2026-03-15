package seedu.duke.parser;

import seedu.duke.command.AddTodoCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.exception.DukeException;

public class Parser {
    public Command parse(String input) throws DukeException {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new DukeException("Command cannot be empty.");
        }
        if (trimmed.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }
        if (trimmed.toLowerCase().startsWith("add")) {
            return parseAdd(trimmed);
        }
        throw new DukeException("Unknown command. Try: add /mod MOD /task TASK");
    }

    private Command parseAdd(String input) throws DukeException {
        String remainder = input.length() > 3 ? input.substring(3).trim() : "";
        if (remainder.isEmpty()) {
            throw new DukeException("Usage: add /mod MOD /task DESCRIPTION");
        }

        String[] tokens = remainder.split("/");
        String module = null;
        String task = null;
        for (String token : tokens) {
            String trimmed = token.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            String lower = trimmed.toLowerCase();
            if (lower.startsWith("mod ")) {
                module = trimmed.substring(4).trim();
            } else if (lower.startsWith("task ")) {
                task = trimmed.substring(5).trim();
            }
        }

        if (module == null || module.isEmpty() || task == null || task.isEmpty()) {
            throw new DukeException("Usage: add /mod MOD /task DESCRIPTION");
        }
        return new AddTodoCommand(module, task);
    }
}
