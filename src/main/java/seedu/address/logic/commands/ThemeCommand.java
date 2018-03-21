package seedu.address.logic.commands;

import seedu.address.ui.UiStyle;

/**
 * Change the theme of addressbook
 */
public class ThemeCommand extends UndoableCommand {

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
    public CommandResult executeUndoableCommand() {
        if (theme.equalsIgnoreCase(ThemeCommand.LIGHT_THEME)) {
            UiStyle.getInstance().setToLightTheme();
        } else {
            UiStyle.getInstance().setToDarkTheme();
        }

        return new CommandResult(MESSAGE_SUCCESS);

    }
}
