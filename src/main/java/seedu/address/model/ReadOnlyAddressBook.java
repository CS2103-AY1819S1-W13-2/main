package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();

    /**
     * Returns an unmodifiable view of the event tags list.
     * This list will not contain any duplicate event tags.
     */
    ObservableList<Tag> getEventTagList();

    /**
     * Returns notification preference.
     */
    boolean getNotificationPref();

    /**
     * Updates notification preference.
     */
    void updateNotificationPref(boolean set);

    /**
     * Updates favourite.
     */
    void updateFavourite(String favourite);

    /**
     * Returns favourite String.
     */
    String getFavourite();

    /**
     * Checks if the event is the currently favourite event
     */
    boolean isFavourite(Event event);
}
