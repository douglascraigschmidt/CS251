package com.example.expressiontree.client;

import com.example.expressiontree.server.ServerInputHandler;

/**
 * @class UserCommandProxy
 *
 * @brief ...
 */
public class UserCommandProxy {
    private String mUserCommandString;

    public UserCommandProxy(String userCommandString) {
        mUserCommandString = userCommandString;
    }

    /** Runs the command. */
    public void execute() throws Exception {
        // @@ Mitchell, this is the point where we want to use
        // HTTP method calls.
        ServerInputHandler serverInputHandler =
            new ServerInputHandler(mUserCommandString);
        serverInputHandler.handleInput();
    }

    /** Print the valid commands available to users. */
    public void printValidCommands(boolean verboseField) {
    }
}
