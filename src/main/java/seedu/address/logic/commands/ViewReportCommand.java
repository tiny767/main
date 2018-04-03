package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ToggleReportPanelEvent;
import seedu.address.model.tag.Tag;

/**
 * Displays the report to the user.
 */
public class ViewReportCommand extends Command {
    public static final String COMMAND_WORD = "viewreport";
    public static final String COMMAND_ALIAS = "vr";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            //TODO: update report messages
            + ": Managing reports.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Displayed report";
    public final Tag population;

    public ViewReportCommand(Tag population) {
        this.population = population;
    }

    @Override
    public CommandResult execute() {
        model.updateReport(population);
        EventsCenter.getInstance().post(new ToggleReportPanelEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
