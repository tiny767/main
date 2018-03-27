package seedu.address.model.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code skills or address} matches any of the keywords given.
 */

public class PersonMatchesJobPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final boolean notLocationBound;

    public PersonMatchesJobPredicate(Job job) {
        this.keywords = new ArrayList<String>();
        this.keywords.addAll(Arrays.asList(job.getSkills().toString().split(",")));
        this.keywords.addAll(Arrays.asList(job.getLocation().toString().split(" ")));

        notLocationBound = (job.getLocation().toString().compareTo("##") == 0);
    }

    @Override
    public boolean test(Person person) {
        String toMatchPerson = person.getAddress().toString();
        toMatchPerson.concat(person.getSkills().toString());
        toMatchPerson.concat(person.getTags().toString());


        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(toMatchPerson, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesJobPredicate// instanceof handles nulls
                && this.keywords.equals((
                        (PersonMatchesJobPredicate) other).keywords)); // state check
    }
}
