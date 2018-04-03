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
    public static final String MORNING_THEME = "morning";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes theme to. "
            + "Parameters: morning/light/dark";


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
        } else if (theme.equalsIgnoreCase(ThemeCommand.MORNING_THEME)) {
            UiStyle.getInstance().setToMorningTheme();
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
