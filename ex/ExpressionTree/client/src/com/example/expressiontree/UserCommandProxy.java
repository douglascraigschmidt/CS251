package com.example.expressiontree;

import retrofit.RestAdapter;


/**
 * @class UserCommandProxy
 *
 * @brief Acts as a proxy between this client and the server
 * 		that actually hosts the expression tree code.
 */
public class UserCommandProxy {
    private String mUserCommandString;

    public UserCommandProxy(String userCommandString) {
        mUserCommandString = userCommandString;
    }

    /** Runs the command by passing  */
    public void execute() throws Exception {
    	// @@ Right now we're just passing the command string to the server.
    	// More complexities to follow...
    	
    	RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint("http://localhost:8080/")
        .build();

    	ExpressionTreeService service = restAdapter.create(ExpressionTreeService.class);
    	
    	service.execute(mUserCommandString);
        
    }
	
    /** Print the valid commands available to users. */
    public void printValidCommands(boolean verboseField) {
    }
}
