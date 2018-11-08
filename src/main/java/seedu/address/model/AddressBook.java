package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.filereader.FileReader;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueEventList events;
    private final UniqueTagList eventTags;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        events = new UniqueEventList();
        eventTags = new UniqueTagList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Replaces the contents of the event tag list with {@code eventTags}.
     * {@code eventTags} must not contain duplicate event tags.
     */
    public void setEventTags(List<Tag> eventTags) {
        this.eventTags.setTags(eventTags);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setEvents(newData.getEventList());
        setEventTags(newData.getEventTagList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void updatePerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from the person list in this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// event-level operations

    /**
     * Returns true if an event with the same identity as {@code event} exists in the address book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Returns true if a clashing event with {@code event} exists in the address book.
     */
    public boolean hasClashingEvent(Event event) {
        requireNonNull(event);
        return events.containsClashingEvent(event);
    }

    /**
     * Removes {@code key} from the events list in this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    /**
     * Adds an event to the address book.
     * The event must not already exist in the address book, and must not clash any of the existing events in the
     * address book. All event tags must be existing in the address book.
     */
    //TODO: decision to allow clashing events? If from xml, goes here directly. If from user, can do additional check to
    // ask the
    // user (by raising an event which brings up a message and prompts user for further input - enter to add anyway
    // or esc to delete) for confirmation before adding.
    public void addEvent(Event event) {
        assert !hasEvent(event);
        assert !hasClashingEvent(event);
        for (Tag eventTag : event.getEventTags()) {
            assert hasEventTag(eventTag);
        }

        events.add(event);
    }

    /**
     * Reads contacts info in the given file reader.
     */
    public void importContacts(FileReader fileReader) {
        ArrayList<String> contacts = fileReader.getContacts();
        for (String s : contacts) {
            String[] parts = s.split(",");
            String nameString = parts[fileReader.getNameIndex()];
            String phoneString = parts[fileReader.getPhoneIndex()].replaceAll("[^0-9]", "");
            String addressString = parts[fileReader.getAddressIndex()];
            String emailString = parts[fileReader.getEmailIndex()];
            String facultyString = parts[fileReader.getFacultyIndex()];

            if (!(Name.isValidName(nameString) && Phone.isValidPhone(phoneString)
                    && Address.isValidAddress(addressString) && Email.isValidEmail(emailString))
                    && Faculty.isValidFaculty(facultyString)) {
                continue;
            }

            try {
                Name name = ParserUtil.parseName(nameString);
                Phone phone = ParserUtil.parsePhone(phoneString);
                Email email = ParserUtil.parseEmail(emailString);
                Address address = ParserUtil.parseAddress(addressString);
                Set<Tag> tagList = ParserUtil.parseTags(new ArrayList<>());
                Faculty faculty = ParserUtil.parseFaculty(facultyString);

                Person person = new Person(name, phone, email, address, tagList, faculty);

                if (!persons.contains(person)) {
                    persons.add(person);
                } else {
                    fileReader.incrementFailCounter();
                }
            } catch (ParseException e) {
                fileReader.incrementFailCounter();
            }
        }
    }

    //// tag-level operations
    /**
     * Returns true if an event tag with the same identity as {@code tag} exists in the address book.
     */
    public boolean hasEventTag(Tag tag) {
        requireNonNull(tag);
        return eventTags.contains(tag);
    }

    /**
     * Adds an event tag to the address book.
     * The event tag must not be already existing in the address book.
     */
    public void addEventTag(Tag tag) {
        assert !hasEventTag(tag);

        eventTags.add(tag);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons"
                + "\n" + events.asUnmodifiableObservableList().size() + " events";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getEventTagList() {
        return eventTags.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && events.equals(((AddressBook) other).events))
                && eventTags.equals(((AddressBook) other).eventTags);
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + events.hashCode() + eventTags.hashCode();
    }
}
