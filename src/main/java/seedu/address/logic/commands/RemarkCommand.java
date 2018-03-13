package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Modifies the remark of a person in the address book
 */
public class RemarkCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "remark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit the remark of a person. "
            + "Parameters: "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Is a very good coder.";

    public static final String ERROR_MESSAGE = "Command not implemented yet";

    @Override
    public CommandResult executeUndoableCommand() throws CommandException{
        throw new CommandException(ERROR_MESSAGE);

    }
}
