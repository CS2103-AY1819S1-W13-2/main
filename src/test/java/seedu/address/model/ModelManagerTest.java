package seedu.address.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.CLASHING_EVENT_END_TIME_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.CLASHING_EVENT_START_TIME_DOCTORAPPT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.ImportContactsPersons.JACK;
import static seedu.address.testutil.ImportContactsPersons.KAITING;
import static seedu.address.testutil.ImportContactsPersons.PRATYAY;
import static seedu.address.testutil.ImportContactsPersons.RYAN;
import static seedu.address.testutil.ImportContactsPersons.YUWEI;
import static seedu.address.testutil.TypicalEvents.DOCTORAPPT;
import static seedu.address.testutil.TypicalEvents.MEETING;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalTags.APPOINTMENT_TAG;
import static seedu.address.testutil.TypicalTags.MEETING_TAG;

import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.event.Event;
import seedu.address.model.filereader.FileReader;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.FileReaderBuilder;
import seedu.address.testutil.ScheduledEventBuilder;

public class ModelManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ModelManager modelManager = new ModelManager();

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasPerson(null);
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasEvent(null);
    }

    @Test
    public void hasEventTag_nullTag_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        modelManager.hasEventTag(null);
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasEvent_eventNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasEvent(DOCTORAPPT));
    }

    @Test
    public void hasClashingEvent_clashingEventNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasClashingEvent(DOCTORAPPT));
    }

    @Test
    public void hasEventTag_tagNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasEventTag(APPOINTMENT_TAG));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasEvent_eventInAddressBook_returnsTrue() {
        DOCTORAPPT.getEventTags().forEach(modelManager::addEventTag);
        modelManager.addEvent(DOCTORAPPT);
        assertTrue(modelManager.hasEvent(DOCTORAPPT));
    }

    @Test
    public void hasClashingEvent_clashingEventInAddressBook_returnsTrue() {
        DOCTORAPPT.getEventTags().forEach(modelManager::addEventTag);
        modelManager.addEvent(DOCTORAPPT);
        Event clashingEvent = new ScheduledEventBuilder()
                .withEventName(DOCTORAPPT.getEventName().eventName)
                .withEventDescription(DOCTORAPPT.getEventDescription().eventDescription)
                .withEventDate(DOCTORAPPT.getEventDate().toString())
                .withEventStartTime(CLASHING_EVENT_START_TIME_DOCTORAPPT)
                .withEventEndTime(CLASHING_EVENT_END_TIME_DOCTORAPPT)
                .withEventAddress(DOCTORAPPT.getEventAddress().eventAddress)
                .build();
        assertTrue(modelManager.hasClashingEvent(clashingEvent));
    }

    @Test
    public void hasEventTag_tagInAddressBook_returnsTrue() {
        modelManager.addEventTag(APPOINTMENT_TAG);
        assertTrue(modelManager.hasEventTag(APPOINTMENT_TAG));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredPersonList().remove(0);
    }

    @Test
    public void getUnfilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getUnfilteredPersonList().remove(0);
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredEventList().add(new ScheduledEventBuilder().build());
    }

    @Test
    public void getEventTagList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getEventTagList().add(MEETING_TAG);
    }

    @Test
    public void hasImportContactsPerson_returnsTrue() {
        FileReader fileReader = new FileReaderBuilder().build();

        modelManager.importContacts(fileReader);
        assertTrue(modelManager.hasPerson(JACK));
        assertTrue(modelManager.hasPerson(KAITING));
        assertTrue(modelManager.hasPerson(PRATYAY));
        assertTrue(modelManager.hasPerson(RYAN));
        assertTrue(modelManager.hasPerson(YUWEI));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder()
                .withPerson(ALICE)
                .withPerson(BENSON)
                .withEventTags(DOCTORAPPT.getEventTags())
                .withEventTags(MEETING.getEventTags())
                .withEvent(DOCTORAPPT)
                .withEvent(MEETING)
                .build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList of persons -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        modelManager.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);

        // different userPrefs -> returns true
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertTrue(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
