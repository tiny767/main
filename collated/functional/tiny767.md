# tiny767
###### \java\seedu\address\logic\commands\RemarkCommand.java
``` java
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddCommand.MESSAGE_DUPLICATE_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Modifies the remark of a person in the address book
 */
public class RemarkCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "remark";
    public static final String COMMAND_ALIAS = "rm";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit the remark of a person. "
            + "Parameters: "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Is a very good coder.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Person: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Person: %1$s";

    private final Index index;
    private final Remark remark;

    private Person personToEdit;
    private Person editedPerson;



    /**
     *
     * @param index index of the person in the list to modify the remark
     * @param remark content of the remark to be updated
     */
    public RemarkCommand(Index index, Remark remark) {
        requireNonNull(index);
        requireNonNull(remark);

        this.index = index;
        this.remark = remark;

    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(personToEdit);
        requireNonNull(editedPerson);

        try {
            model.updatePerson(personToEdit, editedPerson);
        } catch (DuplicatePersonException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        } catch (PersonNotFoundException pnfe) {
            throw new AssertionError("The target person cannot be missing");
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(createSuccessMessage(editedPerson));
    }

    /**
     *Generate an execution success message based on whether the remark is adding to or removing from
     */
    private String createSuccessMessage(Person editedPerson) {
        String message = remark.value.isEmpty() ? MESSAGE_DELETE_REMARK_SUCCESS : MESSAGE_ADD_REMARK_SUCCESS;
        return String.format(message, editedPerson);
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        personToEdit = lastShownList.get(index.getZeroBased());
        editedPerson = new Person(personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                this.remark,
                personToEdit.getTags());
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }

}
```
###### \java\seedu\address\logic\commands\ThemeCommand.java
``` java
package seedu.address.logic.commands;

import seedu.address.ui.UiStyle;

/**
 * Change the theme of addressbook
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";
    public static final String COMMAND_ALIAS = "t";
    public static final String LIGHT_THEME = "light";
    public static final String DARK_THEME = "dark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes theme to. "
            + "Parameters: light/dark";


    public static final String MESSAGE_SUCCESS = "Theme has been changed successfully";

    private final String theme;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ThemeCommand(String theme) {
        this.theme = theme;
    }

    @Override
    public CommandResult execute() {
        if (theme.equalsIgnoreCase(ThemeCommand.LIGHT_THEME)) {
            UiStyle.getInstance().setToLightTheme();
        } else if (theme.equalsIgnoreCase(ThemeCommand.DARK_THEME)) {
            UiStyle.getInstance().setToDarkTheme();
        }

        return new CommandResult(MESSAGE_SUCCESS);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ThemeCommand)) {
            return false;
        }

        // state check
        ThemeCommand t = (ThemeCommand) other;
        return theme.equals(t.theme);
    }
}
```
###### \java\seedu\address\logic\parser\ThemeCommandParser.java
``` java
package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class ThemeCommandParser implements Parser<ThemeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ThemeCommand
     * and returns a ThemeCommand object for execution.
         * @throws ParseException if the user input does not conform the expected format
     */
    public ThemeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            String trimmedArgs = args.trim();
            if (!trimmedArgs.equalsIgnoreCase(ThemeCommand.LIGHT_THEME)
                && !trimmedArgs.equalsIgnoreCase(ThemeCommand.DARK_THEME)) {
                throw new IllegalValueException("");

            } else {
                return new ThemeCommand(trimmedArgs);
            }
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ThemeCommand.MESSAGE_USAGE));
        }

    }

}
```
###### \java\seedu\address\ui\UiStyle.java
``` java
package seedu.address.ui;

import javafx.scene.Scene;

/**
 * stores the styles that can be used for the interface
 */
public class UiStyle {
    private static final String LIGHT_THEME_STYLE = "view/LightTheme.css";
    private static final String DARK_THEME_STYLE = "view/DarkTheme.css";
    private static Scene scene = null;
    private static UiStyle instance = null;

    public static void setScene(Scene s) {
        scene = s;
        setDefaultTheme();
    }

    private static void setDefaultTheme() {
        scene.getStylesheets().add(DARK_THEME_STYLE);
    }

    public static void setToLightTheme() {
        scene.getStylesheets().setAll(LIGHT_THEME_STYLE);
    }

    public static void setToDarkTheme() {
        scene.getStylesheets().setAll(DARK_THEME_STYLE);
    }

    public static UiStyle getInstance() {
        if (instance == null) {
            instance = new UiStyle();
        }

        return instance;
    }
}
```
