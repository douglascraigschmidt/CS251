package com.example.expressiontree;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.example.expressiontree.model.ServerResponse;

import retrofit.RestAdapter;
import android.os.AsyncTask;

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
     * The ID associated with this client.
     */
    static Long mClientID = null;

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
    	// Android requires that networking IO be performed in a
    	// separate thread.
    	if (Platform.instance().platformName().contains("Android")) {
        	
            // Start an asynchronous task that will make the request
            // and then post the results on the main UI thread.
            new AsyncTask<Void, Void, ServerResponse> () {
                protected ServerResponse doInBackground(Void ... params) {
                    try {
                        // Make the service.
                        ExpressionTreeService service =
                            makeService("http://10.0.2.2:8080/");
        				
                        // Make the request to the server.
                        return doRequest(service);
						
                    } catch (UnsupportedEncodingException e) {
                        return null;
                    }
                }
        		
                protected void onPostExecute(ServerResponse response) {
                    // Parse the response and update the UI
                    // accordingly.
                    parseResponse(response);
                }
            }.execute();
    	}
    	// Otherwise, just do it in the main thread.
    	else {
            // Make the service.
            ExpressionTreeService service =
                makeService("http://localhost:8080/");
        	
            // Make the request using the provided string and wait for
            // a response.
            ServerResponse response = doRequest(service);
        	
            // Parse the response
            parseResponse(response);
    	}
    }

    /**
     * Creates a Retrofit adapter to interact with the server.
     */
    private ExpressionTreeService makeService(String url) {
    	// Use Retrofit to build a simple interface to our
    	// ExpressionTree server.
    	RestAdapter restAdapter =
            new RestAdapter.Builder().setEndpoint(url).build();
    	return restAdapter.create(ExpressionTreeService.class);
    }
    
    /**
     * Send the command string to the server. 
     */
    private ServerResponse doRequest(ExpressionTreeService service)
        throws UnsupportedEncodingException {
    	
    	// If we have an ID for this specific session, go ahead and send it along
    	// with the command string.
    	if (mClientID != null) 
            return service.execute(URLEncoder.encode(mUserCommandString,
                                                     "UTF-8"),
                                   mClientID);
    	
    	// Otherwise, just send the command string, signaling that we need a token for this
    	// session.
    	else
            return service.execute(URLEncoder.encode(mUserCommandString,
                                                     "UTF-8"));
    }
    
    /**
     * Interprets the response from the server and reacts
     * appropriately.
     */
    private void parseResponse(ServerResponse response) {
    	// If the result was ok
    	if (response.getResult().toLowerCase().equals("ok")) {
            // The server might have given us a client ID. If they
            // did, we should keep it.
            if (response.clientID != null)
                mClientID = response.clientID;
    		
            // The server's output is in the form of calls to the
            // Platform interface.  We have to parse that output and
            // make the corresponding calls.
            PlatformProxyInterpreter.interpret(Platform.instance(),
                                               response);
    	}
    	// If the result was an exception.
    	else if (response.getResult().toLowerCase().equals("exception"))
            // Print the cause of the exception.
            Platform.instance().outputLine("Exception from server: " 
                                           + response.getMessage());
    }
	
    /** 
     * Print the valid commands available to users. 
     */
    public void printValidCommands(boolean verboseField) {
    }
}
