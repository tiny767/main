package seedu.address.model.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code skills, address or tags} matches any of the keywords given.
 */

public class PersonMatchesJobPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final boolean notLocationBound;
    private final boolean notTagsBound;
    private final boolean notSkillsBound;

    public PersonMatchesJobPredicate(Job job) {
        this.keywords = new ArrayList<String>();
        this.keywords.addAll(Arrays.asList(job.getSkills().toString().split(",")));
        this.keywords.addAll(Arrays.asList(job.getLocation().toString().split(" ")));

        notLocationBound = (job.getLocation().toString().equals("##"));
        notTagsBound = (job.getTags().toString().equals("##"));
        notSkillsBound = (job.getSkills().toString().equals("##"));
    }

    @Override
    public boolean test(Person person) {
        String toMatchPersonAddress = person.getAddress().toString();
        String toMatchPersonSkills = person.getSkills().toString();
        String toMatchPersonTags = person.getTags().toString();

        boolean locationMatch =
                keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(toMatchPersonAddress, keyword));
        boolean skillsMatch =
                keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(toMatchPersonSkills, keyword));
        boolean tagsMatch =
                keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(toMatchPersonTags, keyword));

        if (notLocationBound) {
            locationMatch = true;
        }

        if (notSkillsBound) {
            skillsMatch = true;
        }

        if (notTagsBound) {
            tagsMatch = true;
        }

        return locationMatch && skillsMatch && tagsMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesJobPredicate// instanceof handles nulls
                && this.keywords.equals((
                        (PersonMatchesJobPredicate) other).keywords)); // state check
    }
}
