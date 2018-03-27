package seedu.address.model.job;

/***
 * TODO: write javadoc for this
 */
public class Skill {
    // TODO: Write the messages and regex here.

    public static final String MESSAGE_SKILL_CONSTRAINTS = "Job skills can take any values, and it should not be blank";
    private final String skills;

    public Skill(String skills) {
        this.skills = skills;
    }

    public String getSkills() {
        return skills;
    }

    /***
     * TODO: Write javadoc comment
     * @param test
     * @return
     */
    public static boolean isValidSkill(String test) {
        // TODO: Write this based on REGEX
        return true;

    }
}
