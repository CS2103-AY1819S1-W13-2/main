package seedu.address.testutil;

import seedu.address.model.event.Event;

/**
 * A utility class to help with building Event objects. File naming is set to be in line with the other Event utils.
 */

public class ScheduledEventBuilder {

    public static final String DEFAULT_NAME = "Doctor Appointment";
    public static final String DEFAULT_DESC = "Consultation";
    public static final String DEFAULT_DATE = "2018-09-01";
    public static final String DEFAULT_TIME = "1015";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private String name;
    private String desc;
    private String date;
    private String time;
    private String address;

    /**
     * Builds a new Event with the default values.
     */
    public ScheduledEventBuilder() {
        name = DEFAULT_NAME;
        desc = DEFAULT_DESC;
        date = DEFAULT_DATE;
        time = DEFAULT_TIME;
        address = DEFAULT_ADDRESS;
    }

    /**
     * Initializes the EvebtBuilder with the data of {@code eventToCopy}.
     */
    public ScheduledEventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        desc = eventToCopy.getDesc();
        date = eventToCopy.getDate();
        time = eventToCopy.getTime();
        address = eventToCopy.getAddress();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public ScheduledEventBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public ScheduledEventBuilder withDescription(String desc) {
        this.desc = desc;
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public ScheduledEventBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Event} that we are building.
     */
    public ScheduledEventBuilder withTime(String time) {
        this.time = time;
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Event} that we are building.
     */
    public ScheduledEventBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public Event build() {
        return new Event(name, desc, date, time, address);
    }
}
