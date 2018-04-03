package seedu.address.model.interview;

import java.util.ArrayList;

import seedu.address.model.person.Name;

/**
 * Represents an Interview in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {
    private InterviewTitle title;
    private Name interviewee;
    private Date date;
    private ArrayList<Quest> listQuestions;
    private InterviewLocation interviewLocation;

    public Interview(InterviewTitle title, Name interviewee, Date date, InterviewLocation location) {
        this.title = title;
        this.interviewee = interviewee;
        this.date = date;
        this.interviewLocation = location;
    }

    public InterviewTitle getInterviewTitle() {
        return this.title;
    }

    public Name getInterviewee() {
        return this.interviewee;
    }

    public Date getDate() {
        return this.date;
    }

    public InterviewLocation getLocation() {
        return this.interviewLocation;
    }

    public ArrayList<Quest> getListQuestions() {
        return this.listQuestions;
    }

    public void addQuestion(Quest question) {
        listQuestions.add(question);
    }

}
