package com.example.expressiontree.model;

import java.util.List;

import com.example.expressiontree.model.PlatformCall;

/**
 * @brief A POJO class that represents the results returned from a server
 *        request.
 * 
 *        Note: This class will be instantiated by a JSON interpreter
 *        that the IDE will not detect. Therefore, don't try and
 *        refactor this file by renaming any of the members or else
 *        you'll break something.
 */
public class ServerResponse {
    /**
     * Result returned from the server.
     */
    public String result;

    /**
     * Our client ID that we should use to access state we've
     * previously created on the server.
     */
    public Long clientID;
    
    /**
     * Get the client ID.
     */
    public Long getID() {
        return clientID;
    }

    /**
     * Set the client ID.
     */
    public void setID(Long iD) {
        clientID = iD;
    }

    /**
     * The calls to Platform that are used to format output.
     */
    public List<PlatformCall> platformCalls;
    
    /**
     * An error message that might be returned.
     */
    public String message;

    /**
     * A getter for the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * A setter for the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the result returned from the server.
     */
    public String getResult() {
        return result;
    }

    /**
     * Set the result returned from the server.
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Getter method for platform calls. 
     */
    public List<PlatformCall> getPlatformCalls() {
        return platformCalls;
    }

    /**
     * Setter method for platform calls. 
     */
    public void setPlatformCalls(List<PlatformCall> platformCalls) {
        this.platformCalls = platformCalls;
    }
}
