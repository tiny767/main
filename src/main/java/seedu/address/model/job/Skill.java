package seedu.address.model.job;

/***
 * Represents a Job's required Skills in the Infinity Book.
 * Guarantees: is valid as declared in {@link #isValidSkill(String)}
 */
public class Skill {
    // TODO: Write the messages and regex here.

    public static final String MESSAGE_SKILL_CONSTRAINTS = "Job skills can take any values, and it should not be blank";

    private final String skills;

    public Skill(String skills) {
        this.skills = skills;
    }

    public String toString() {
        return skills;
    }

    /***
     * Checks if a given string is a valid Skill.
     * @param test is the String to be tested for validity
     * @return true if it is a valid skill.
     */
    public static boolean isValidSkill(String test) {
        // TODO: Write this based on REGEX
        return true;
    }
}
