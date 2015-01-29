package com.example.expressiontree;

import java.net.URLEncoder;

import retrofit.RestAdapter;

/**
 * @class UserCommandProxy
 *
 * @brief Acts as a proxy between this client and the server
 *        that actually hosts the expression tree code.
 */
public class UserCommandProxy {
    /**
     * Command string created by the user.
     */
    private String mUserCommandString;

    /**
     * Constructor sets the user command string.
     */
    public UserCommandProxy(String userCommandString) {
        mUserCommandString = userCommandString;
    }

    /** 
     * Runs the user command on the server.  
     */
    public void execute() throws Exception {
    	// @@ Right now we're just passing the command string to the
    	// server.  More complexities to follow...
    	
        // @@ Mitchell, can you please (briefly) document this code?
    	RestAdapter restAdapter =
            new RestAdapter.Builder().setEndpoint("http://localhost:8080/").build();

    	ExpressionTreeService service =
            restAdapter.create(ExpressionTreeService.class);
    	
    	ServerResponse response =
            service.execute(URLEncoder.encode(mUserCommandString,
                                              "UTF-8"));
        
    	if (!response.getResult().toLowerCase().equals("ok")) 
            Platform.instance().outputLine("Exception from server: " 
                                           + response.getResult());
    	else 
            PlatformProxyInterpreter.interpret(Platform.instance(),
                                               response);
    }
	
    /** 
     * Print the valid commands available to users. 
     */
    public void printValidCommands(boolean verboseField) {
    }
}
