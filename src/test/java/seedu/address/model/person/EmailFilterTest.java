package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmailFilterTest {

    @Test
    public void equals() {
        Email firstEmail = new Email("abc@example.com");
        Email secondEmail = new Email("def@example.com");

        EmailFilter firstPredicate = new EmailFilter(firstEmail);
        EmailFilter secondPredicate = new EmailFilter(secondEmail);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailFilter firstPredicateCopy = new EmailFilter(firstEmail);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void tests() {
        Email firstEmail = new Email("abc@example.com");
        Email secondEmail = new Email("def@example.com");

        EmailFilter firstPredicate = new EmailFilter(firstEmail);
        EmailFilter secondPredicate = new EmailFilter(secondEmail);

    }
}
