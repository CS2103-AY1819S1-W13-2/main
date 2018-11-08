package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * Represents a request for a random meeting location to be generated.
 */
public class RandomMeetingLocationGeneratedEvent extends BaseEvent {

    private final String meetingPlaceId;

    public RandomMeetingLocationGeneratedEvent(String placeId) {
        this.meetingPlaceId = placeId;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public String getMeetingPlaceId() {
        return meetingPlaceId;
    }

}
