package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.RemarkCommand.ERROR_MESSAGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains tests for {@code RemarkCommand}
 */
public class RemarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() throws Exception{
        assertCommandFailure(remarkCommand, model, ERROR_MESSAGE);
    }

    /**
     * Returns a {@code RemarkCommand}
     */
    private RemarkCommand makeCommand(){
        RemarkCommand remarkCommand = new RemarkCommand();
        remarkCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return remarkCommand;
    }

    private RemarkCommand remarkCommand = makeCommand();
}
