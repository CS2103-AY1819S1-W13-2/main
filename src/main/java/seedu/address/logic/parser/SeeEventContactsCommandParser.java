package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SeeEventContactsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDate;

/**
 * Parses input arguments and creates a new SeeEventContactsCommandParser object
 */
public class SeeEventContactsCommandParser implements Parser<SeeEventContactsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SeeEventContactsCommand
     * and returns an SeeEventContactsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SeeEventContactsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_INDEX);

        // check for mandatory fields, and that no other data is entered between the command and first argument prefix
        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SeeEventContactsCommand.MESSAGE_USAGE));
        }

        EventDate eventDate = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get());
        Index eventIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

        return new SeeEventContactsCommand(eventDate, eventIndex);
    }
}
