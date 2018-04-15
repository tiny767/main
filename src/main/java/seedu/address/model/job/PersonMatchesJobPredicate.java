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
        StringBuilder stringBuilder = new StringBuilder();
        String toMatchPersonAddress = setUpAddressSearch(person, stringBuilder);

        stringBuilder = new StringBuilder();
        String toMatchPersonSkills = setUpSkillsSearch(person, stringBuilder);

        stringBuilder = new StringBuilder();
        String toMatchPersonTags = setUpTagsSearch(person, stringBuilder);

        boolean locationMatch =
                keywords.stream().anyMatch(keyword -> toMatchPersonAddress.contains(keyword.toLowerCase()));
        boolean skillsMatch =
                keywords.stream().anyMatch(keyword -> toMatchPersonSkills.contains(keyword.toLowerCase()));
        boolean tagsMatch =
                keywords.stream().anyMatch(keyword -> toMatchPersonTags.contains(keyword.toLowerCase()));

        locationMatch = isLocationMatchSatisfied(locationMatch);
        skillsMatch = isSkillsMatchSatisfied(skillsMatch);
        tagsMatch = isTagsMatchSatisfied(tagsMatch);

        return locationMatch && skillsMatch && tagsMatch;
    }

    /***
     * Checks if tag match conditions are satisfied.
     * @param tagsMatch is the boolean variable to be set
     * @return appropriate value of tagsMatch
     */
    private boolean isTagsMatchSatisfied(boolean tagsMatch) {
        if (notTagsBound) {
            tagsMatch = true;
        }
        return tagsMatch;
    }

    /***
     * Checks if skills match conditions are satisfied.
     * @param skillsMatch is the boolean variable to be set
     * @return appropriate value of skillsMatch
     */
    private boolean isSkillsMatchSatisfied(boolean skillsMatch) {
        if (notSkillsBound) {
            skillsMatch = true;
        }
        return skillsMatch;
    }

    /***
     * Checks if location match conditions are satisfied.
     * @param locationMatch is the boolean variable to be set
     * @return appropriate value of locationMatch
     */
    private boolean isLocationMatchSatisfied(boolean locationMatch) {
        if (notLocationBound) {
            locationMatch = true;
        }
        return locationMatch;
    }

    /***
     * Identifies the set of keywords to be matched for tags based matching of candidates.
     * @param person who is tested for a match currently
     * @param stringBuilder which accumulates the keyword string
     * @return the complete keywords string
     */
    private String setUpTagsSearch(Person person, StringBuilder stringBuilder) {
        String[] toMatchPersonTagsWords = person.getTags().toString().split(",");
        for (String entry : toMatchPersonTagsWords) {
            if (!(entry.compareTo("ALL") == 0)) {
                entry = entry.trim().toLowerCase();
                entry = entry.replaceAll("\\[", "");
                entry = entry.replaceAll("\\]", "");
                stringBuilder.append(" " + entry + " ");
            }
        }
        return stringBuilder.toString();
    }

    /***
     * Identifies the set of keywords to be matched for skills based matching of candidates.
     * @param person who is tested for a match currently
     * @param stringBuilder which accumulates the keyword string
     * @return the complete keywords string
     */
    private String setUpSkillsSearch(Person person, StringBuilder stringBuilder) {
        String[] toMatchPersonSkillsWords = person.getSkills().toString().split(",");
        for (String entry : toMatchPersonSkillsWords) {
            if (!(entry.compareTo("ALL") == 0)) {
                entry = entry.trim().toLowerCase();
                stringBuilder.append(" " + entry + " ");
            }
        }
        return stringBuilder.toString();
    }

    /***
     * Identifies the set of keywords to be matched for location based matching of candidates.
     * @param person who is tested for a match currently
     * @param stringBuilder which accumulates the keyword string
     * @return the complete keywords string
     */
    private String setUpAddressSearch(Person person, StringBuilder stringBuilder) {
        String[] toMatchPersonAddressWords = person.getAddress().toString().split(",");
        for (String entry : toMatchPersonAddressWords) {
            if (!(entry.compareTo("ALL") == 0)) {
                entry = entry.trim().toLowerCase();
                stringBuilder.append(" " + entry + " ");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesJobPredicate// instanceof handles nulls
                && this.keywords.equals((
                        (PersonMatchesJobPredicate) other).keywords)); // state check
    }
}
