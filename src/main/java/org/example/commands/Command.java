package org.example.commands;

import org.example.core.exceptions.*;

public abstract class Command {
    /**
     * The method triggers the execution of the command.
     * @param args Arguments for the command.
     * @return Summary string of command execution.
     * @throws RecursionException
     * @throws FileAccessException
     * @throws CommandParamsException
     * @throws FileDoesNotExist
     */
    public abstract String execute(String ... args) throws RecursionException, FileAccessException, CommandParamsException, FileDoesNotExist, ArgumentLimitsException;

    /**
     * Gets information about the command.
     * @return String with full information about the command (description, syntax, arguments and types)
     */
    public abstract String getCommandInfo();
}
