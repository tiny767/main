package seedu.address.model.interview;

import java.util.ArrayList;

import seedu.address.model.person.Person;

/**
 * Represents an Interview in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {
    private Person person;
    private Date date;
    private ArrayList<Quest> listQuestions;
    private Location location;

    public Interview(Person person, Date date, Location location) {
        this.person = person;
        this.date = date;
        this.location = location;
    }

    public void addQuestion(Quest question) {
        listQuestions.add(question);
    }

}
