package seedu.address.model.job;

/***
 * TODO: write javadoc for this
 */
public class Skill {
    // TODO: Write the messages and regex here.

    public static final String MESSAGE_SKILL_CONSTRAINTS = "Job skills can take any values, and it should not be blank";

    /*
     * The first character of the skill  must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String SKILL_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    public Skill(String skills) {
        this.value = skills;
    }

    public String toString() {
        return value;
    }

    /**
     * Returns true if a given string is a valid person.
     */
    public static boolean isValidSkill(String test) {
        return test.matches(SKILL_VALIDATION_REGEX);
    }

}
