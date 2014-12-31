package com.example.expressiontree.server;

/**
 * @class UserCommand
 *
 * @brief Plays the role of the "Command" in the Command pattern to
 *        define an API for "ConcreteCommand" implementations that
 *        perform an operation on the expression tree when it's
 *        executed.
 */
public abstract class UserCommand {
    /** 
     * Holds the expression tree that is the target of the
     * commands. 
     */
    protected TreeOps treeOps;
	
    /** Runs the command. */
    public abstract void execute() throws Exception;

    /** Print the valid commands available to users. */
    public abstract void printValidCommands(boolean verboseField);
}
