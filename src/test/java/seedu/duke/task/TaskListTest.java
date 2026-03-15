package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.duke.exception.DukeException;

class TaskListTest {

    @Test
    void addTodo_validDescription_addsTask() throws DukeException {
        TaskList list = new TaskList();
        Task task = list.addTodo("CS2113", " Week8 ");

        assertEquals(1, list.size());
        assertEquals("CS2113", task.getModuleCode());
        assertEquals("Week8", task.getDescription());
    }

    @Test
    void addTodo_emptyDescription_throws() {
        TaskList list = new TaskList();
        assertThrows(DukeException.class, () -> list.addTodo("CS2113", " "));
    }
}
