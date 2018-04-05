package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ToggleReportPanelEvent;
import seedu.address.model.tag.Tag;

public class SaveReportCommand extends Command {
    public static final String COMMAND_WORD = "savereport";
    public static final String COMMAND_ALIAS = "sr";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Save report at the current state.\n"
            + "Parameters: pop/POPULATION_TAG\n"
            + "Example: " + COMMAND_WORD + " pop/SEIntern OR " + COMMAND_ALIAS + " pop/SEIntern";
    public static final String MESSAGE_SUCCESS = "Saved report for #";
    public final Tag population;

    public SaveReportCommand(Tag population) {
        this.population = population;
    }

    @Override
    public CommandResult execute() {
        model.updateReport(population);
        model.addReport(model.getReport());
        EventsCenter.getInstance().post(new ToggleReportPanelEvent());

        return new CommandResult(MESSAGE_SUCCESS + population.tagName);
    }
}
