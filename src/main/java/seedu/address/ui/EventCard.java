package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;

public class EventCard extends UiPart<Region> {
    private static final String FXML = "EventListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Event event;

    @FXML
    private Label id;
    @FXML
    private Label time;
    @FXML
    private Label name;
    @FXML
    private Label address;
    @FXML
    private Label description;

    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        assert event != null;

        this.event = event;
        id.setText("[" + displayedIndex + "] ");
        time.setText(event.getEventTime().toString());
        name.setText(event.getEventName().eventName);
        address.setText(event.getEventAddress().eventAddress);
        description.setText(event.getEventDescription().eventDescription);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
