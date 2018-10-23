package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Enable / disable notifications
 * To do : command exception
 */

public class NotificationCommand extends Command {

    public static final String COMMAND_WORD = "notification";
    public static final String COMMAND_WORD_ALIAS = "n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "(alias: " + COMMAND_WORD_ALIAS + ")"
            + ": Enable / Disable notification in " + "the address book. "
            + "Parameters: enable/disable\n"
            + "Example: " + COMMAND_WORD + " enable";

    public String MESSAGE_SUCCESS;

    private final boolean set;

    public NotificationCommand(boolean set) { this.set = set; }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        ModelManager.updateNotificationPref(set);

        if (set)
            MESSAGE_SUCCESS = "Notification: enabled";
        else
            MESSAGE_SUCCESS = "Notification: disabled";

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}