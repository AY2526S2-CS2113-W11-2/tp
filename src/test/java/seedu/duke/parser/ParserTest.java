package seedu.duke.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.duke.command.AddTodoCommand;
import seedu.duke.exception.DukeException;

class ParserTest {

    @Test
    void parse_addCommand_returnsAddTodo() throws DukeException {
        Parser parser = new Parser();
        assertTrue(parser.parse("add /mod CS2113 /task Week8") instanceof AddTodoCommand);
        assertTrue(parser.parse("add /task Week8 /mod CS2113") instanceof AddTodoCommand);
    }

    @Test
    void parse_missingFields_throws() {
        Parser parser = new Parser();
        assertThrows(DukeException.class, () -> parser.parse("add /mod CS2113"));
        assertThrows(DukeException.class, () -> parser.parse("add /task OnlyTask"));
        assertThrows(DukeException.class, () -> parser.parse("add"));
    }
}
