package seedu.address.model.report;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Represents a Report in the address book.
 */
public class Report {

    private final Tag population;
    private final UniqueTagList groups;
    private final String name;
    private final int maxNumberOfGroups = 3;

    /**
     * Every field must be present and not null.
     */
    public Report(Tag population, Set<Tag> groups) {
        requireAllNonNull(population, groups);

        this.population = population;
        // protect internal tags from changes in the arg list
        this.groups = new UniqueTagList(groups);

        String name = population.tagName;
        for (Tag s : this.groups.toSet()) {
            name = name + "_" + s.tagName;
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Tag getPopulation() {
        return population;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getGroups() {
        return Collections.unmodifiableSet(groups.toSet());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Report)) {
            return false;
        }

        Report otherPerson = (Report) other;
        return otherPerson.getName().equals(this.getName())
                && otherPerson.getGroups().equals(this.getGroups())
                && otherPerson.getPopulation().equals(this.getPopulation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        return builder.toString();
    }

}
