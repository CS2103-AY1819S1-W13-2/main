package seedu.address.logic.parser;

import seedu.address.logic.commands.GenerateLocationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventName;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

/**
 * Parses input arguments and creates a new GenerateLocationCommand object
 */
public class GenerateLocationCommandParser implements Parser<GenerateLocationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GenerateLocationCommand
     * and returns an GenerateLocationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GenerateLocationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GenerateLocationCommand.MESSAGE_USAGE));
        }

        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());

        return new GenerateLocationCommand(eventName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
