package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.JobMatchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.job.PersonMatchesJobPredicate;

/***
 * TODO: Write a javadoc comment
 */
public class JobMatchCommandParser implements Parser<JobMatchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public JobMatchCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, JobMatchCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        return new JobMatchCommand(new PersonMatchesJobPredicate(Arrays.asList(keywords)));
    }
}
