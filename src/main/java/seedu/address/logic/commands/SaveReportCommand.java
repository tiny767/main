package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ToggleReportPanelEvent;
import seedu.address.model.tag.Tag;

public class SaveReportCommand extends Command {
    public static final String COMMAND_WORD = "savereport";
    public static final String COMMAND_ALIAS = "sr";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            //TODO: update report messages
            + ": Managing reports.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Saved report";
    public final Tag population;

    public SaveReportCommand(Tag population) {
        this.population = population;
    }

    @Override
    public CommandResult execute() {
        model.updateReport(population);
        model.addReport(model.getReport());
        EventsCenter.getInstance().post(new ToggleReportPanelEvent());

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
