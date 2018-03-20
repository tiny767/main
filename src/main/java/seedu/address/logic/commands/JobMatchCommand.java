package seedu.address.logic.commands;

import seedu.address.model.job.PersonMatchesJobPredicate;

/**
 * Finds and lists all persons in address book whose profiles match the job posting.
 * Keyword matching is case sensitive.
 */

public class JobMatchCommand extends Command {
    public static final String COMMAND_WORD = "matchjob";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Matches all persons whose profiles match job posting "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: JOB [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final PersonMatchesJobPredicate predicate;

    public JobMatchCommand(PersonMatchesJobPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobMatchCommand // instanceof handles nulls
                && this.predicate.equals(((JobMatchCommand) other).predicate)); // state check
    }

}
