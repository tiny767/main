# deeheenguyen
###### \java\seedu\address\logic\commands\AddInterviewCommand.java
``` java
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.exceptions.DuplicateInterviewException;

/***
 * Adds an Interview to AddressBook
 */
public class AddInterviewCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "addInterview";
    public static final String COMMAND_ALIAS = "ai";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to the address book. "
            + "Parameters: "
            + PREFIX_INTERVIEW + "INTERVIEW "
            + PREFIX_NAME + "INTERVIEWEE "
            + PREFIX_DATE + "INTERVIEW_DATE"
            + PREFIX_LOCATION + "INTERVIEW_LOCATION "
            + "\n "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INTERVIEW + "INTERNSHIP INTERVIEW "
            + PREFIX_NAME + "DAVID "
            + PREFIX_DATE + "04.05.2018 "
            + PREFIX_LOCATION + "SUNTEX CITY ";

    public static final String MESSAGE_SUCCESS = "New interview added: ";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists in the address book";

    private final Interview toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Job}
     */
    public AddInterviewCommand(Interview interview) {
        requireNonNull(interview);
        toAdd = interview;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            model.addInterview(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (DuplicateInterviewException e) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInterviewCommand // instanceof handles nulls
                && toAdd.equals(((AddInterviewCommand) other).toAdd));
    }
}
//author@@ deeheenguyen
```
###### \java\seedu\address\logic\commands\ListInterviewCommand.java
``` java
package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

/**
 * Lists all jobs in the address book to the user.
 */
public class ListInterviewCommand extends Command {
    public static final String COMMAND_WORD = "listInterview";

    public static final String MESSAGE_SUCCESS = "Listed all interviews";

    @Override
    public CommandResult execute() {
        model.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
```
###### \java\seedu\address\logic\commands\ListInterviewCommand.java
``` java

```
###### \java\seedu\address\logic\commands\ViewCommand.java
``` java
package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.model.person.EmailFilter;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the person with the email\n"
            + "Example: " + COMMAND_WORD + " abcd@gmail.com";

    private final EmailFilter predicate;

    public ViewCommand(EmailFilter predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        Index targetIndex = Index.fromOneBased(1);
        EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex));
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && this.predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
```
###### \java\seedu\address\logic\commands\ViewCommand.java
``` java

```
###### \java\seedu\address\storage\XmlAdaptedInterview.java
``` java
package seedu.address.storage;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.interview.Date;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.InterviewLocation;
import seedu.address.model.interview.InterviewTitle;
import seedu.address.model.person.Name;

/**
 * JAXB-friendly version of the Job.
 */
public class XmlAdaptedInterview {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Job's %s field is missing!";

    @XmlElement(required = true)
    private String interviewTitle;
    @XmlElement(required = true)
    private String interviewLocation;
    @XmlElement(required = true)
    private String interviewee;
    @XmlElement (required = true)
    private String date;

    /**
     * Constructs an XmlAdaptedInterview.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedInterview() {}

    /**
     * Constructs an {@code XmlAdaptedInterview} with the given interview details.
     */
    public XmlAdaptedInterview(String interviewTitle, String interviewee, String location, String date) {
        this.interviewTitle = interviewTitle;
        this.interviewLocation = location;
        this.date = date;
        this.interviewee = interviewee;
    }

    /**
     * Converts a given Interview into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedInterview
     */
    public XmlAdaptedInterview(Interview source) {
        interviewTitle = source.getInterviewTitle().toString();
        interviewLocation = source.getInterviewLocation().toString();
        interviewee = source.getInterviewee().toString();
        date = source.getDate().toString();
    }

    /**
     * Converts this jaxb-friendly adapted interview object into the model's job object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interview
     */
    public Interview toModelType() throws IllegalValueException {
        if (this.interviewTitle == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewTitle.class.getSimpleName()));
        }
        if (!InterviewTitle.isValidTitle(this.interviewTitle)) {
            throw new IllegalValueException(InterviewTitle.MESSAGE_TITLE_CONSTRAINTS);
        }
        final InterviewTitle interviewTitle = new InterviewTitle(this.interviewTitle);

        if (this.interviewee == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(this.interviewee)) {
            throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        final Name interviewee = new Name(this.interviewee);

        if (this.interviewLocation == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewLocation.class.getSimpleName()));
        }
        if (!InterviewLocation.isValidLocation(this.interviewLocation)) {
            throw new IllegalValueException(InterviewLocation.MESSAGE_LOCATION_CONSTRAINTS);
        }
        final InterviewLocation interviewLocation = new InterviewLocation(this.interviewLocation);

        if (this.date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(this.date)) {
            throw new IllegalValueException(Date.MESSAGE_DATE_CONSTRAINTS);
        }
        final Date date = new Date(this.date);

        return new Interview(interviewTitle, interviewee, date, interviewLocation);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedInterview)) {
            return false;
        }

        XmlAdaptedInterview otherInterview = (XmlAdaptedInterview) other;
        return Objects.equals(interviewTitle, otherInterview.interviewTitle)
                && Objects.equals(interviewee, otherInterview.interviewee)
                && Objects.equals(interviewLocation, otherInterview.interviewLocation)
                && Objects.equals(date, otherInterview.date);
    }
}
//author@@ deeheenguyen



```
