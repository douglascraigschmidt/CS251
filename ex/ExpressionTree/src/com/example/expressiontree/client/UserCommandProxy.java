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
        ServerInputHandler serverInputHandler =
            new ServerInputHandler(mUserCommandString);
        serverInputHandler.handleInput();
    }

    /** Print the valid commands available to users. */
    public void printValidCommands(boolean verboseField) {
    }
}
