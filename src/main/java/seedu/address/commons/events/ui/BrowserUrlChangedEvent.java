package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
//@@author tiny767
/**
 * Indicates that the browser has changed
 */
public class BrowserUrlChangedEvent extends BaseEvent {
    private String processType;

    public BrowserUrlChangedEvent(String processType) {
        this.processType = processType;
    }

    public String getProcessType() {
        return processType;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
