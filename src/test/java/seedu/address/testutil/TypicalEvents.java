package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MEETING;

import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */

public class TypicalEvents {

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event DOCTORAPPT =
            new ScheduledEventBuilder()
                    .withName(VALID_NAME_DOCTORAPPT)
                    .withDescription(VALID_DESC_DOCTORAPPT)
                    .withDate(VALID_DATE_DOCTORAPPT)
                    .withTime(VALID_TIME_DOCTORAPPT)
                    .withAddress(VALID_ADDRESS_DOCTORAPPT)
                    .build();
    public static final Event MEETING =
            new ScheduledEventBuilder()
                    .withName(VALID_NAME_MEETING)
                    .withDescription(VALID_DESC_MEETING)
                    .withDate(VALID_DATE_MEETING)
                    .withTime(VALID_TIME_MEETING)
                    .withAddress(VALID_ADDRESS_MEETING)
                    .build();

    private TypicalEvents() {} // prevents instantiation
}
