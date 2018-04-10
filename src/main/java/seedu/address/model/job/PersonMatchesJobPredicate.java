package seedu.address.model.job;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

        for (String entry : job.getSkills().getSkillSet()) {
            if (!(entry.compareTo("ALL") == 0)) {
                this.keywords.add(entry);
            }
        }

        for (String entry : job.getLocation().toString().split(",")) {
            if (!(entry.compareTo("ALL") == 0)) {
                this.keywords.add(entry);
            }
        }

        for (String entry : job.getTags().toString().split(",")) {
            if (!(entry.compareTo("ALL") == 0)) {
                entry = entry.trim();
                entry = entry.replaceAll("\\[", "");
                entry = entry.replaceAll("\\]", "");
                this.keywords.add(entry);
            }
        }

        notLocationBound = (job.getLocation().toString().equals("ALL"));
        notTagsBound = (job.getTags().toString().contains("ALL"));
        notSkillsBound = (job.getSkills().toString().equals("ALL"));
    }

    @Override
    public boolean test(Person person) {
        String[] toMatchPersonAddressWords = person.getAddress().toString().split(",");

        StringBuilder stringBuilder = new StringBuilder();
        for (String entry : toMatchPersonAddressWords) {
            if (!(entry.compareTo("ALL") == 0)) {
                entry = entry.trim();
                stringBuilder.append(" " + entry + " ");
            }
        }
        String toMatchPersonAddress = stringBuilder.toString();

        stringBuilder = new StringBuilder();
        String[] toMatchPersonSkillsWords = person.getSkills().toString().split(",");
        for (String entry : toMatchPersonSkillsWords) {
            if (!(entry.compareTo("ALL") == 0)) {
                entry = entry.trim();
                stringBuilder.append(" " + entry + " ");
            }
        }
        String toMatchPersonSkills = stringBuilder.toString();

        stringBuilder = new StringBuilder();
        String[] toMatchPersonTagsWords = person.getTags().toString().split(",");
        for (String entry : toMatchPersonTagsWords) {
            if (!(entry.compareTo("ALL") == 0)) {
                entry = entry.trim();
                entry = entry.replaceAll("\\[", "");
                entry = entry.replaceAll("\\]", "");
                stringBuilder.append(" " + entry + " ");
            }
        }
        String toMatchPersonTags = stringBuilder.toString();

        boolean locationMatch =
                keywords.stream().anyMatch(keyword -> toMatchPersonAddress.contains(keyword));
        boolean skillsMatch =
                keywords.stream().anyMatch(keyword -> toMatchPersonSkills.contains(keyword));
        boolean tagsMatch =
                keywords.stream().anyMatch(keyword -> toMatchPersonTags.contains(keyword));

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
