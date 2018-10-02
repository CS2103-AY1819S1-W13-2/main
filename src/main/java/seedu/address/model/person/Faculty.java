package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Faculty {

    public static final String MESSAGE_FACULTY_CONSTRAINTS =
            "Faculty name should be standardized with what NUS uses." +
                    "Contacts with no faculty should have the field set to '-'.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String FACULTY_VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param faculty A valid address.
     */
    public Faculty(String faculty) {
        requireNonNull(faculty);
        checkArgument(isValidAddress(faculty), MESSAGE_FACULTY_CONSTRAINTS);
        value = faculty;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(FACULTY_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Faculty // instanceof handles nulls
                && value.equals(((Faculty) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
