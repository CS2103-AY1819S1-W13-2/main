package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.event.EventTagMatchesKeywordsPredicate;

/**
 * Finds and lists all events in address book whose event tag matches any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindEventCommand extends Command {
    public static final String COMMAND_WORD = "findEvent";
    public static final String COMMAND_WORD_ALIAS = "fe";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all events whose event tags matches "
            + "the specified event tags (case-insensitive) and displays them as in the Event tab\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " meeting appointment";

    private final EventTagMatchesKeywordsPredicate predicate;

    public FindEventCommand(EventTagMatchesKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCommand // instanceof handles nulls
                && predicate.equals(((FindEventCommand) other).predicate)); // state check
    }
}
