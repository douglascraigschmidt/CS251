package com.example.expressiontree.server;

import com.example.expressiontree.client.Platform;

/**
 * @class ServerInputHandler
 *
 * @brief @@ Mitchell, we need to get this hosted in a Servlet.
 * @see   
 */
public class ServerInputHandler {
    /** Classifies the Handler as verbose. */
    static boolean mVerboseField;
	
    /** The context where the expression tree state resides. */
    private TreeOps mTreeOps;
	
    /** A factory for creating a command. */
    private UserCommandFactory mUserCommandFactory;
	
    /** User-supplied command. */
    private String mUserCommandString;

    /** Handle to last valid command that was executed. */
    private UserCommand mLastValidCommand;
	
    /** Constructor. */
    public ServerInputHandler(String userCommandString) {
        mUserCommandString = userCommandString;
        mTreeOps = new TreeOps();
        mUserCommandFactory = new UserCommandFactory(mTreeOps);
        UserCommand f = new NullCommand(mTreeOps);
        mLastValidCommand = f;
    }

    /** 
     * This method is called back when input is available.  It
     * implements the Template Method pattern to perform the sequence
     * of steps associated with processing expression tree application
     * commands.
     */
    public void handleInput() throws Exception {
        // Retrieves input from user.
        String userInputCommand = retrieveInput();
        
        if (userInputCommand.equals(""))
            ; // @@ Return something that informs client the input is incorrect.
        else {
            // Call hook method to make a command based on user input.
            UserCommand command = makeUserCommand(userInputCommand);
            
            // Call a hook method to execute the command.
            executeCommand(command);
        }
    }

    /** This hook method retrieves input. */
    private String retrieveInput() {
        return mUserCommandString;
    }

    /**
     * This hook method makes a command based on the user input.
     */
    private UserCommand makeUserCommand(String userInputCommand) {
      return mUserCommandFactory.makeUserCommand(userInputCommand);
    }

    /** This hook method executes a command. */
    private void executeCommand(UserCommand command) throws Exception {
        command.execute();
    }
}
