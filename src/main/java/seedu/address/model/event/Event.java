package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    // identity fields
    private final String name;
    private final String desc;
    private final String date;
    private final String time;

    // data fields
    private final String address;

    /**
     * Every field must be present and not null.
     */
    public Event(String name, String desc, String date, String time, String address) {
        requireAllNonNull(name, desc, date, time, address);
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.address = address;
    }

    // todo: change type of getter functions when classes for the attributes are created

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }

    // todo: define a weaker form of equality to check clashes

    /**
     * Returns true if both persons have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherPerson = (Event) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getDesc().equals(getDesc())
                && otherPerson.getDate().equals(getDate())
                && otherPerson.getTime().equals(getTime())
                && otherPerson.getAddress().equals(getAddress());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, desc, date, time, address);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Description: ")
                .append(getDesc())
                .append(" Date: ")
                .append(getDate())
                .append(" Time: ")
                .append(getTime())
                .append(" Address: ")
                .append(getAddress());
        return builder.toString();
    }

}

