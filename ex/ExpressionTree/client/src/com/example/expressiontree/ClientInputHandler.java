package com.example.expressiontree;


/**
 * @class ClientInputHandler
 *
 * @brief Provides an abstract class for handling input events and
 *        commands associated with the expression tree application.
 *
 *        This class defines methods for use in the Template Method
 *        pattern that is used to process user input commands.
 *
 * @see   @ VerboseModeClientInputHandler and @ SuccinctModeClientInputHandler.
 */
public abstract class ClientInputHandler {
    /** Input object recognized on both platforms. */
    public static Object globalInput;
    
    /** Output object recognized on both platforms.  */
    public static Object globalOutput;
	
    /** Classifies the Handler as verbose. */
    static boolean verboseField;
	
    /** An Activity object for the verbose mode event handler. */
    public static Object activityField;
	
    /** Handle to last valid command that was executed. */
    protected UserCommandProxy lastValidCommand;
	
    /** Constructor. */
    public ClientInputHandler() {
        // @@ Fix me.
        // UserCommand f = new NullCommand(treeOps);
        // lastValidCommand = f;
    }

    /**
     * Factory that creates the appropriate subclass of @a
     * ClientInputHandler, i.e., @a VerboseModeClientInputHandler or @a *
     * SuccinctModeClientInputHandler. 
     */
    public static ClientInputHandler makeHandler(boolean verbose,
                                                  Object input,
                                                  Object output,
                                                  Object activity) {
        globalInput = input;
        globalOutput = output;
        verboseField = verbose;
        activityField = activity;
		
        if(verbose) 
            return new VerboseModeClientInputHandler();
        else 
            return new SuccinctModeClientInputHandler();
    }
	
    /** 
     * This method is called back when input is available.  It
     * implements the Template Method pattern to perform the sequence
     * of steps associated with processing expression tree application
     * commands.
     */
    public void handleInput() throws Exception {
        /** Call a hook method to obtain user input. */
        promptUser();
		
        /** Retrieves input from user. */
        String input = retrieveInput();
        
        if (input.equals(""))
            InputDispatcher.instance().endInputDispatching();
        else {
            /** Call a hook method to make a command based on the user input. */
            UserCommandProxy commandProxy = makeUserCommandProxy(input);

            /** Call a hook method to execute the command. */
            executeCommand(commandProxy);

            /** Saves last command to a variable. */
            lastValidCommand = commandProxy;
		
            /** Displays the menu associated the the command. */
            commandProxy.printValidCommands(verboseField);
        }
    }

    /** 
     * This hook method is a placeholder for prompting the user for
     * input.
     */
    protected abstract void promptUser();

    /** This hook method retrieves input. */
    protected String retrieveInput() {
        return Platform.instance().retrieveInput(verboseField);
    }

    /**
     * This hook method is a placeholder for making a command based
     * on the user input.
     */
    protected abstract UserCommandProxy makeUserCommandProxy(String userInput);

    /** This hook method executes a command. */
    protected void executeCommand(UserCommandProxy commandProxy) throws Exception {
        commandProxy.execute();
    }
}
