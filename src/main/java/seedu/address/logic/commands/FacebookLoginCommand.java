package seedu.address.logic.commands;

import javafx.scene.web.WebEngine;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Connects the address book to a personal Facebook account.
 */
public class FacebookLoginCommand extends Command {
    public static final String COMMAND_WORD = "facebooklogin";
    public static final String COMMAND_ALIAS = "fb";
    public static final String MESSAGE_SUCCESS = "Authentication has been initiated. "
            + "Please log into your Facebook account.";
    public static final String FACEBOOK_DOMAIN = "https://www.facebook.com/";
    private static final String FACEBOOK_APP_ID = "131555220900267";
    private static final String FACEBOOK_AUTH_URL =
            "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + FACEBOOK_APP_ID
                    + "&redirect_uri=" + FACEBOOK_DOMAIN;

    private static WebEngine webEngine;

    /**
     * Sets a WebEngine
     */
    public static void setWebEngine(WebEngine webEngine) {
        FacebookLoginCommand.webEngine = webEngine;
    }

    @Override
    public CommandResult execute() throws CommandException {
        webEngine.load(FACEBOOK_AUTH_URL);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
