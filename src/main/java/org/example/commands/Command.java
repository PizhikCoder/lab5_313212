package org.example.commands;

import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionLimitException;

public abstract class Command {
    /**
     * The method triggers the execution of the command.
     * @param args Arguments for the command.
     * @return Summary string of command execution.
     * @throws RecursionLimitException
     * @throws FileAccessException
     * @throws CommandParamsException
     * @throws FileDoesNotExist
     */
    public abstract String execute(String ... args) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist;

    /**
     * Gets information about the command.
     * @return String with full information about the command (description, syntax, arguments and types)
     */
    public abstract String getCommandInfo();
}
