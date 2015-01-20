package com.example.expressiontree;

/**
 * A POJO class that represents the results returned from a server
 * request. This will be parsed from JSON using the Retrofit library,
 * so names, getters, and setters are not used directly in the
 * project, but still matter.
 */
public class ServerResult {
    /**
     * Result returned from the server.
     */
    public String result;

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
}
