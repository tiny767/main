package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ToggleReportPanelEvent;

/**
 * Displays the report to the user.
 */
public class ReportCommand extends Command {
    public static final String COMMAND_WORD = "report";
    public static final String COMMAND_ALIAS = "r";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            //TODO: update report messages
            + ": Managing reports.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Displayed report";

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ToggleReportPanelEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
