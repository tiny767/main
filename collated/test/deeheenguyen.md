# deeheenguyen
###### \java\seedu\address\logic\parser\AddInterviewCommandParserTest.java
``` java
package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_SE;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEWEE_DESC_SE;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_LOCATION_DESC_SE;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_TITLE_DESC_SE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEWEE_SE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_LOCATION_SE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_TITLE_SE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.AddInterviewCommand;
import seedu.address.model.interview.Interview;
import seedu.address.testutil.InterviewBuilder;

public class AddInterviewCommandParserTest {
    private AddInterviewCommandParser parser = new AddInterviewCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Interview expectedInterview = new InterviewBuilder()
                .withInterviewTitle(VALID_INTERVIEW_TITLE_SE)
                .withInterviewee(VALID_INTERVIEWEE_SE)
                .withDate(VALID_DATE_SE)
                .withInterviewLocation(VALID_INTERVIEW_LOCATION_SE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INTERVIEW_TITLE_DESC_SE
                + INTERVIEWEE_DESC_SE +  DATE_DESC_SE + INTERVIEW_LOCATION_DESC_SE,
                new AddInterviewCommand(expectedInterview));
    }
}
```
###### \java\seedu\address\logic\parser\ViewCommandParserTest.java
``` java
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailFilter;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsIllegalException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewCommand() {
        // no leading and trailing whitespaces
        String example = "john@example.com";
        Email email = new Email(example);
        ViewCommand expectedViewCommand =
                new ViewCommand(new EmailFilter(email));
        assertParseSuccess(parser, "john@example.com", expectedViewCommand);
    }
}
```
