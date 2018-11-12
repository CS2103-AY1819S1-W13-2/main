package seedu.address.ui;

import java.net.URL;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.FacultyLocationDisplayChangedEvent;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.commons.events.ui.RandomMeetingLocationGeneratedEvent;
import seedu.address.logic.EmbedGoogleMaps;
import seedu.address.model.person.Person;

/**
 * The Browser Panel of the App.
 */
public class LocationDisplayPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "locationLanding.html";

    private static final String API_KEY = "AIzaSyAUAMhSz-X72KN47J2YdyCE5VtDtcSmvmU";

    private static final String DEFAULT_UHALL_PLACE_ID = "ChIJA1jFpVca2jERs1NXg5xbqbA";

    /**
     * First part of the location iframe content. The content is split into two so that the location to be displayed's
     * Google Maps Place ID can be inserted.
     */
    private static String locationContentA = "<iframe width=\"1150\" height=\"550\" frameborder=\"0\""
            + "style=\"border:0\" src=\""
            + "https://www.google.com/maps/embed/v1/place?q=place_id:";

    /**
     * Second part of the lccation iframe content.
     */
    private static String locationContentB = "&key=" + API_KEY + "\" "
            + "allowfullscreen></iframe>";

    private static final String FXML = "LocationDisplayPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private WebView locationBrowser;

    public LocationDisplayPanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        loadDefaultPage();
        registerAsAnEventHandler(this);
    }

    private void loadPersonLocation(Person person) {
        loadIframe(prepareLocationContent(person));
    }

    private void loadRandomMeetingLocation(String placeId) {
        loadIframe(prepareLocationContent(placeId));
    }

    public void loadPage(String url) {
        Platform.runLater(() -> locationBrowser.getEngine().load(url));
    }

    /**
     * Prepares the final iframe location content string with the Faculty of the Person.
     * @param person The person whose {@code Faculty} location is to be displayed.
     * @return The final iframe location content.
     */
    public String prepareLocationContent(Person person) {
        String faculty = person.getFaculty().toString();
        String finalLocationContent = null;
        if (faculty.equals("-")) {
            finalLocationContent = locationContentA + DEFAULT_UHALL_PLACE_ID + locationContentB;
        } else {
            String placeId = EmbedGoogleMaps.getPlaceId(faculty);
            finalLocationContent = locationContentA + placeId + locationContentB;
        }
        return finalLocationContent;
    }

    /**
     * Overloaded method that prepares the final iframe location content string using placeId directly instead.
     * @param placeId The Google Maps Place ID of  location to be displayed.
     * @return The final iframe location content.
     */
    public String prepareLocationContent(String placeId) {
        String finalLocationContent = locationContentA + placeId + locationContentB;
        return finalLocationContent;
    }

    public void loadIframe(String iframeContent) {
        locationBrowser.getEngine().loadContent(iframeContent);
    }

    /**
     * Loads a default HTML file.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        locationBrowser = null;
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonLocation(event.getNewSelection());
    }

    @Subscribe
    private void handleShowFacultyLocationSelectionEvent (FacultyLocationDisplayChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonLocation(event.getSelectedPerson());
    }

    @Subscribe
    private void handleRandomMeetingLocationGeneratedEvent(RandomMeetingLocationGeneratedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadRandomMeetingLocation(event.getMeetingPlaceId());
    }
}
