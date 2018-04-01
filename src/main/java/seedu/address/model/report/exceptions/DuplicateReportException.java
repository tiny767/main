package seedu.address.model.report.exceptions;

import seedu.address.commons.exceptions.DuplicateDataException;

/**
 * Signals that the operation will result in duplicate Job objects.
 */
public class DuplicateReportException extends DuplicateDataException {
    public DuplicateReportException() {
        super("Operation would result in duplicate jobs");
    }
}
