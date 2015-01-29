package com.example.expressiontree;

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
     * The calls to Platform that are used to format output.
     */
    public List<PlatformCall> platformCalls;

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
     * @@ Mitchell, please document this.
     */
    public List<PlatformCall> getPlatformCalls() {
        return platformCalls;
    }

    /**
     * @@ Mitchell, please document this.
     */
    public void setPlatformCalls(List<PlatformCall> platformCalls) {
        this.platformCalls = platformCalls;
    }
}
