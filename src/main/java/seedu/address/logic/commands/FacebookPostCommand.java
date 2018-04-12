package seedu.address.logic.commands;

import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import javafx.scene.web.WebEngine;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.NewResultAvailableEvent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.ui.BrowserPanel;

//@@author tiny767
/**
 * Posts a message to a personal Facebook account.
 */
public class FacebookPostCommand extends Command {
    public static final String COMMAND_WORD = "facebookpost";
    public static final String COMMAND_ALIAS = "fbpost";
    public static final String EXAMPLE_POST = "Good morning!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": make a post Facebook wall of user\n"
            + "Alias: " + COMMAND_ALIAS + "\n"
            + "Parameters: MESSAGE\n"
            + "Example: " + COMMAND_WORD + " " + EXAMPLE_POST;

    public static final String MESSAGE_FACEBOOK_POST_SUCCESS = "Posted to Facebook.";
    public static final String MESSAGE_FACEBOOK_POST_LOGIN = "User has not been authenticated yet, please log in.";
    public static final String MESSAGE_FACEBOOK_POST_FAIL = "There was an error posting to Facebook";

    private static String currentPost;
    private static WebEngine webEngine;

    private String toPost;

    /**
     * Creates an FacebookPostCommand based on the message
     */
    public FacebookPostCommand(String message) {
        currentPost = message;
        toPost = message;
    }

    /**
     * Completes the post command
     */
    public void completePost() throws CommandException {

        DefaultFacebookClient fbClient = FacebookLoginCommand.getFbClient();
        try {
            //fbClient.(currentPost);
            fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", currentPost));
        } catch (Exception e) {
            e.printStackTrace();
            new CommandException(MESSAGE_FACEBOOK_POST_FAIL);
        }
        EventsCenter.getInstance().post(new NewResultAvailableEvent(MESSAGE_FACEBOOK_POST_SUCCESS
                + " (to " + FacebookLoginCommand.getAuthenticatedUsername() + "'s page.)"));
        webEngine = FacebookLoginCommand.getWebEngine();
        webEngine.load(FacebookLoginCommand.getAuthenticatedUserPage());
    }

    @Override
    public CommandResult execute() throws CommandException {
        if (!FacebookLoginCommand.getAuthenticateState()) {
            BrowserPanel.setProcessType(COMMAND_WORD);
            FacebookLoginCommand fbLoginCommand = new FacebookLoginCommand();
            fbLoginCommand.execute();
            return new CommandResult(MESSAGE_FACEBOOK_POST_LOGIN);
        } else {
            completePost();
            return new CommandResult(MESSAGE_FACEBOOK_POST_SUCCESS + " (to "
                    + FacebookLoginCommand.getAuthenticatedUsername() + "'s page.)");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FacebookPostCommand // instanceof handles nulls
                && toPost.equals(((FacebookPostCommand) other).toPost));
    }
}
