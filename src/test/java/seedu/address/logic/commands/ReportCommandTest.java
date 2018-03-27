package seedu.address.logic.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.ReportCommand.MESSAGE_SUCCESS;

public class ReportCommandTest {

    @Test
    public void execute_report_success() {
        CommandResult result = new ReportCommand().execute();
        assertEquals(MESSAGE_SUCCESS, result.feedbackToUser);
    }

}
