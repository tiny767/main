package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ToggleReportPanelEvent;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Displays the statistics of the address book to the user.
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
    public CommandResult execute() throws CommandException {
        EventsCenter.getInstance().post(new ToggleReportPanelEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}