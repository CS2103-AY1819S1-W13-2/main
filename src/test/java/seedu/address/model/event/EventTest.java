package seedu.address.model.event;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_MEETING;
import static seedu.address.testutil.TypicalEvents.DOCTORAPPT;
import static seedu.address.testutil.TypicalEvents.MEETING;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.ScheduledEventBuilder;

public class EventTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    // equality criteria testing for two Event objects
    public void equals() {
        // same values -> returns true
        Event doctorApptCopy = new ScheduledEventBuilder(DOCTORAPPT).build();
        assertTrue(DOCTORAPPT.equals(doctorApptCopy));

        // same object -> returns true
        assertTrue(DOCTORAPPT.equals(DOCTORAPPT));

        // null -> returns false
        assertFalse(DOCTORAPPT.equals(null));

        // different type -> returns false
        assertFalse(DOCTORAPPT.equals(5));

        // different person -> returns false
        assertFalse(DOCTORAPPT.equals(MEETING));

        // different name -> returns false
        Event editedDoctorAppt = new ScheduledEventBuilder(DOCTORAPPT).withName(VALID_NAME_MEETING).build();
        assertFalse(DOCTORAPPT.equals(editedDoctorAppt));

        // different event description -> returns false
        editedDoctorAppt = new ScheduledEventBuilder(DOCTORAPPT).withDescription(VALID_DESC_MEETING).build();
        assertFalse(DOCTORAPPT.equals(editedDoctorAppt));

        // different date -> returns false
        editedDoctorAppt = new ScheduledEventBuilder(DOCTORAPPT).withDate(VALID_DATE_MEETING).build();
        assertFalse(DOCTORAPPT.equals(editedDoctorAppt));

        // different time -> returns false
        editedDoctorAppt = new ScheduledEventBuilder(DOCTORAPPT).withTime(VALID_TIME_MEETING).build();
        assertFalse(DOCTORAPPT.equals(editedDoctorAppt));

        // different address -> returns false
        editedDoctorAppt = new ScheduledEventBuilder(DOCTORAPPT).withAddress(VALID_ADDRESS_MEETING).build();
        assertFalse(DOCTORAPPT.equals(editedDoctorAppt));
    }
}
