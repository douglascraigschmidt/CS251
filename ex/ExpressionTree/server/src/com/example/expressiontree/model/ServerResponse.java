package com.example.expressiontree.model;

import java.util.List;

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
     * Our client ID that we should use to access state we've previously created on the server. 
     */
    public Long clientID = null;
    
    public Long getID() {
		return clientID;
	}

	public void setID(Long iD) {
		clientID = iD;
	}	
	
	/**
     * The calls to Platform that are used to format output.
     */
    public List<PlatformCall> platformCalls = null;
    
    /**
     * An error message that might be returned.
     */
    public String message = null;

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
    
    /**
     * Converts the ServerResponse from a Java object into a Json
     * formatted message that can be passed over HTTP.
     */
    public String toJson() {
        StringBuilder out = new StringBuilder();
		
        out.append("{result:\"" + result + '"');
		
        if (clientID != null)
            out.append(", clientID:" + clientID.longValue());
		
        if (message != null)
            out.append(", message:\"" + message + '"');
		
        if (platformCalls != null) {
            out.append(", platformCalls: [");
			
            for (int i = 0; i < platformCalls.size(); ++i) {
                if (i != 0)
                    out.append(',');
				
                PlatformCall call = platformCalls.get(i);
				
                out.append("{fname:\"" + call.fname + "\", params: [ ");
				
                for (int k = 0; k < call.params.size(); ++k) {
                    if (k != 0)
                        out.append(',');
						
                    out.append('"' + call.params.get(k) + '"');
                }
					
                out.append("] }");
            }
			
            out.append(']');
        }
		
        out.append("}");
		
        return out.toString();
    }
}
