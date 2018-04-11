//@@author deeheenguyen
package seedu.address.model.interview;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.person.Name;

/**
 * Represents an Interview in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {
    private InterviewTitle interviewTitle;
    private Name interviewee;
    private Date date;
    private ArrayList<Quest> listQuestions;
    private InterviewLocation interviewLocation;

    public Interview(InterviewTitle interviewTitle, Name interviewee, Date date, InterviewLocation location) {
        this.interviewTitle = interviewTitle;
        this.interviewee = interviewee;
        this.date = date;
        this.interviewLocation = location;
    }

    public InterviewTitle getInterviewTitle() {
        return this.interviewTitle;
    }

    public Name getInterviewee() {
        return this.interviewee;
    }

    public Date getDate() {
        return this.date;
    }

    public InterviewLocation getInterviewLocation() {
        return this.interviewLocation;
    }

    public ArrayList<Quest> getListQuestions() {
        return this.listQuestions;
    }

    public void addQuestion(Quest question) {
        listQuestions.add(question);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interview)) {
            return false;
        }
        Interview otherInterview = (Interview) other;
        return otherInterview.getInterviewTitle().equals(this.getInterviewTitle())
                && otherInterview.getInterviewee().equals(this.getInterviewee())
                && otherInterview.getDate().equals(this.getDate())
                && otherInterview.getInterviewLocation().equals(this.getInterviewLocation());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(interviewTitle, interviewee, date, interviewLocation);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Interview Title: ")
                .append(getInterviewTitle())
                .append(" Interviewee: ")
                .append(getInterviewee())
                .append(" Date: ")
                .append(getInterviewee())
                .append(" Interview Location: ")
                .append(getInterviewLocation());
        return builder.toString();
    }
}
