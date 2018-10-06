package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

public class AddEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_failure() {
        Event emptyEvent = new Event(VALID_NAME_DOCTORAPPT, VALID_DESC_DOCTORAPPT, VALID_DATE_DOCTORAPPT,
                VALID_TIME_DOCTORAPPT, VALID_ADDRESS_DOCTORAPPT);
        AddEventCommand addEventCommand = new AddEventCommand(emptyEvent);

        assertCommandFailure(addEventCommand, model, commandHistory,
                addEventCommand.MESSAGE_METHOD_NOT_IMPLEMENTED_YET + emptyEvent);
    }

    @Test
    public void equals() {
        Event firstEvent = new Event(VALID_NAME_DOCTORAPPT, VALID_DESC_DOCTORAPPT, VALID_DATE_DOCTORAPPT,
                VALID_TIME_DOCTORAPPT, VALID_ADDRESS_DOCTORAPPT);
        Event secondEvent = new Event(VALID_NAME_MEETING, VALID_DESC_MEETING, VALID_DATE_MEETING, VALID_TIME_MEETING,
                VALID_ADDRESS_MEETING);
        AddEventCommand addFirstEventCommand = new AddEventCommand(firstEvent);
        AddEventCommand addSecondEventCommand = new AddEventCommand(secondEvent);

        // same object -> returns true
        assertTrue(addFirstEventCommand.equals(addFirstEventCommand));

        // same values -> returns true
        AddEventCommand addFirstEventCommandCopy = new AddEventCommand(firstEvent);
        assertTrue(addFirstEventCommand.equals(addFirstEventCommandCopy));

        // different types -> returns false
        assertFalse(addFirstEventCommand.equals(new ClearCommand()));

        // null -> returns false
        assertFalse(addFirstEventCommand.equals(null));

        // different event -> returns false
        assertFalse(addFirstEventCommand.equals(addSecondEventCommand));
    }
}
