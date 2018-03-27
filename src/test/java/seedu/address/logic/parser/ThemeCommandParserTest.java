package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ThemeCommand.DARK_THEME;
import static seedu.address.logic.commands.ThemeCommand.LIGHT_THEME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.ThemeCommand;

public class ThemeCommandParserTest {
    private ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void execute_themeChangeSuccess() {
        // valid light theme
        ThemeCommand expectedCommand = new ThemeCommand(LIGHT_THEME);
        assertParseSuccess(parser, LIGHT_THEME, expectedCommand);

        // valid dark theme
        expectedCommand = new ThemeCommand(ThemeCommand.DARK_THEME);
        assertParseSuccess(parser, DARK_THEME, expectedCommand);

        // Message for failure
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE);

        // invalid theme name
        assertParseFailure(parser, "pink", expectedMessage);

    }
}
