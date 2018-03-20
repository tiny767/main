package seedu.address.model.job;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.job.exceptions.DuplicateJobException;

/**
 * A list of jobs that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Job#equals(Object)
 */
public class UniqueJobList {
    private final ObservableList<Job> internalList = FXCollections.observableArrayList();

    /***
     * Returns true if the list contains an equivalent job as the given argument.
     */
    public boolean contains(Job toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    /**
     * Adds a job to the list.
     *
     * @throws DuplicateJobException if the job to add is a duplicate of an existing person in the list.
     */
    public void add(Job toAdd) throws DuplicateJobException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            // TODO: Replace with new exception
            throw new DuplicateJobException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Job> asObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

}
