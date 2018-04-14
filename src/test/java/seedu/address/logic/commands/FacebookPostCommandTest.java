package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FacebookPostCommandTest {



    @Test
    public void equals() {
        FacebookPostCommand fbPostCommand = new FacebookPostCommand("some message");
        // same object -> returns true
        assertTrue(fbPostCommand.equals(fbPostCommand));

        // same values -> returns true
        FacebookPostCommand sameFbPostCommand = new FacebookPostCommand("some message");
        assertTrue(fbPostCommand.equals(sameFbPostCommand));

        // different types -> returns false
        assertFalse(fbPostCommand.equals(1));

        // null -> returns false
        assertFalse(fbPostCommand.equals(null));

        FacebookPostCommand differentPostCommand = new FacebookPostCommand("some other message");
        // different content -> return false
        assertFalse(fbPostCommand.equals(differentPostCommand));

    }

}
