package seedu.address.model.report;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents a Proportion of a report in the address book.
 */
public class Proportion {
    public final String tagName;
    public final int value;

    /**
     * Constructs a {@code Proportion}.
     *
     * @param tagName a valid tag name.
     */
    public Proportion(String tagName, int value) {
        requireNonNull(tagName);
        this.tagName = tagName;
        this.value = value;
    }

    public String toString() {
        return tagName + ": " + Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Proportion // instanceof handles nulls
                && this.tagName.equals(((Proportion) other).tagName)) // state check
                && this.value == ((Proportion) other).value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName, value);
    }
}



