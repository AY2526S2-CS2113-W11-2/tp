package seedu.duke.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testDateParsingAndDaysLeft() {
        LocalDate expectedDate = LocalDate.now().plusDays(5);
        Deadline deadline = new Deadline("CS2113", "Finish TP", expectedDate);
        
        assertEquals(expectedDate, deadline.getBy());
        assertEquals(5, deadline.getDaysLeft());
    }

    @Test
    void testFormatForList() {
        LocalDate date = LocalDate.of(2025, 12, 31);
        Deadline deadline = new Deadline("CS2113", "Finish TP", false, date);
        
        String expectedStart = "1.[CS2113] [D][ ] Finish TP (by: 2025-12-31, ";
        String actualStr = deadline.formatForList(1);
        assert actualStr.startsWith(expectedStart) : "Format string mismatched. Actual: " + actualStr;
    }
}
