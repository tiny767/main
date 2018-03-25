package seedu.address.model.interview;

/**
 * Represents a Interview's question in the address book.
 */
public class Quest {
    private String answer = "";
    private String question;

    public Quest(String question) {
        this.question = question;
    }

    public void addAnswer(String answer) {
        this.answer = answer;
    }
    public String getAnswer() {
        return this.answer;
    }

    public String getQuestion() {
        return this.question;
    }
}
