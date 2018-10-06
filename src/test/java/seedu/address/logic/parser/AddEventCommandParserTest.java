package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DESC_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DOCTORAPPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_DOCTORAPPT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.DOCTORAPPT;

import org.junit.Test;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.event.Event;
import seedu.address.testutil.ScheduledEventBuilder;

public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();
    private final Event emptyEvent = new Event(VALID_NAME_DOCTORAPPT, VALID_DESC_DOCTORAPPT, VALID_DATE_DOCTORAPPT,
            VALID_TIME_DOCTORAPPT, VALID_ADDRESS_DOCTORAPPT);

    //todo: add in tests for all fields, optional fields, all fields_missing_failure, invalid_value_failure
    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new ScheduledEventBuilder(DOCTORAPPT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_DOCTORAPPT + DESC_DESC_DOCTORAPPT
                + DATE_DESC_DOCTORAPPT + TIME_DESC_DOCTORAPPT + ADDRESS_DESC_DOCTORAPPT,
                new AddEventCommand(expectedEvent));

        // multiple names - only the last name accepted
        assertParseSuccess(parser, NAME_DESC_MEETING + NAME_DESC_DOCTORAPPT + DESC_DESC_DOCTORAPPT
                        + DATE_DESC_DOCTORAPPT + TIME_DESC_DOCTORAPPT + ADDRESS_DESC_DOCTORAPPT,
                new AddEventCommand(expectedEvent));

        // multiple event descriptions - only the last description accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_DOCTORAPPT + DESC_DESC_MEETING
                        + DESC_DESC_DOCTORAPPT + DATE_DESC_DOCTORAPPT + TIME_DESC_DOCTORAPPT + ADDRESS_DESC_DOCTORAPPT,
                new AddEventCommand(expectedEvent));

        // multiple dates - last date accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_DOCTORAPPT + DESC_DESC_DOCTORAPPT
                        + DATE_DESC_MEETING + DATE_DESC_DOCTORAPPT + TIME_DESC_DOCTORAPPT + ADDRESS_DESC_DOCTORAPPT,
                new AddEventCommand(expectedEvent));

        // multiple times - last time accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_DOCTORAPPT + DESC_DESC_DOCTORAPPT
                        + DATE_DESC_DOCTORAPPT + TIME_DESC_MEETING + TIME_DESC_DOCTORAPPT + ADDRESS_DESC_DOCTORAPPT,
                new AddEventCommand(expectedEvent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_DOCTORAPPT + DESC_DESC_DOCTORAPPT
                        + DATE_DESC_DOCTORAPPT + TIME_DESC_DOCTORAPPT + ADDRESS_DESC_MEETING + ADDRESS_DESC_DOCTORAPPT,
                new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                VALID_NAME_DOCTORAPPT + DESC_DESC_DOCTORAPPT + DATE_DESC_DOCTORAPPT
                        + TIME_DESC_DOCTORAPPT + ADDRESS_DESC_DOCTORAPPT,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser,
                NAME_DESC_DOCTORAPPT + VALID_DESC_DOCTORAPPT + DATE_DESC_DOCTORAPPT
                        + TIME_DESC_DOCTORAPPT + ADDRESS_DESC_DOCTORAPPT,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser,
                NAME_DESC_DOCTORAPPT + DESC_DESC_DOCTORAPPT + VALID_DATE_DOCTORAPPT
                        + TIME_DESC_DOCTORAPPT + ADDRESS_DESC_DOCTORAPPT,
                expectedMessage);

        // missing time prefix
        assertParseFailure(parser,
                NAME_DESC_DOCTORAPPT + DESC_DESC_DOCTORAPPT + DATE_DESC_DOCTORAPPT
                        + VALID_TIME_DOCTORAPPT + ADDRESS_DESC_DOCTORAPPT,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser,
                NAME_DESC_DOCTORAPPT + DESC_DESC_DOCTORAPPT + DATE_DESC_DOCTORAPPT
                        + TIME_DESC_DOCTORAPPT + VALID_ADDRESS_DOCTORAPPT,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                VALID_NAME_DOCTORAPPT + VALID_DESC_DOCTORAPPT + VALID_DATE_DOCTORAPPT
                        + VALID_TIME_DOCTORAPPT + VALID_ADDRESS_DOCTORAPPT,
                expectedMessage);
    }

    // todo: add tests to check for invalid input after Event attribute objects are set up
}
