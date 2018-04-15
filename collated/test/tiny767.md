# tiny767
###### \java\seedu\address\logic\parser\FacebookPostCommandParserTest.java
``` java
public class FacebookPostCommandParserTest {
    private FacebookPostCommandParser parser = new FacebookPostCommandParser();

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, FacebookPostCommand.EXAMPLE_POST,
                new FacebookPostCommand(FacebookPostCommand.EXAMPLE_POST));
    }

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FacebookPostCommand.MESSAGE_USAGE));
    }
}
```
