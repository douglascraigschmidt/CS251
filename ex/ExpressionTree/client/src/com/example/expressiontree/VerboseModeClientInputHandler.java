package com.example.expressiontree;


/**
 * @class VerboseModeClientInputHandler
 * 
 * @brief Provides a concrete interface for verbosely handling input
 *        events associated with the expression tree application. 
 *
 *        This class overrides several hook methods for use in the
 *        Template Method pattern.
 */
public class VerboseModeClientInputHandler extends ClientInputHandler {
    /** Keeps track of whether we've prompted the user already. */
    private boolean prompted;
	
    /** Ctor */
    VerboseModeClientInputHandler() {
        prompted = false;
    }

    /** This hook method verbosely prompts the user for input. */
    public void promptUser() {
        Platform platform = Platform.instance();

        if (!prompted) {
            platform.disableAll(verboseField);
            platform.outputMenu("", "", "");  
            platform.outputMenu("1a.",
                                "format",
                                "[in-order]");
            platform.outputMenu("1b.",
                                "set",
                                "[variable=value]");
            platform.outputMenu("2.",
                                "expr",
                                "[expression]");
            platform.outputMenu("3a.",
                                "eval",
                                "[post-order]");
            platform.outputMenu("3b.",
                                "print",
                                "[in-order | pre-order | post-order| level-order]");
            platform.outputMenu("0c.",
                                "quit",
                                "");
            platform.outputMenu("", "", "");

            prompted = true;
        }
        /** Output the '>' prompt character. */
        platform.outputString("> ");
    }

    /** 
     * This hook method makes the appropriate command based on the
     * user input.
     */
    public UserCommandProxy makeUserCommandProxy(String userInputExpression) {
        return new UserCommandProxy(userInputExpression);
    }	
}
