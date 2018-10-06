package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds an event to the address book.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "EVENT NAME "
            + PREFIX_EVENT_DESCRIPTION + "EVENT DESCRIPTION "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_ADDRESS + "ADDRESS \n"
            // + "[" + PREFIX_CONTACT_INDEX + "CONTACT INDEX]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Doctor appointment "
            + PREFIX_EVENT_DESCRIPTION + "consultation "
            + PREFIX_DATE + "15-09-18 "
            + PREFIX_TIME + "1030 "
            + PREFIX_ADDRESS + "123, Clementi Rd, 1234665";
    // + PREFIX_CONTACT_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book";
    public static final String MESSAGE_METHOD_NOT_IMPLEMENTED_YET = "Method has not been implemented!";

    // todo: activate this field
    private final Event toAdd;

    /**
     * Creates an AddEventCommand to add the specified Event {@code Event}.
     */
    public AddEventCommand(Event event) {
        //requireNonNull(event);
        toAdd = event;
        //todo: implement constructor properly
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        /*
        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd))
        */
        //todo: implement this method properly
        throw new CommandException(MESSAGE_METHOD_NOT_IMPLEMENTED_YET + toAdd);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd)); // state check
    }
}
