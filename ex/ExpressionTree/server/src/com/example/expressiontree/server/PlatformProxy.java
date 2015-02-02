package com.example.expressiontree.server;

import java.util.ArrayList;
import java.util.List;

/**
 * @class PlatformProxy
 * 
 * @brief Handles marshalling calls to the Platform interface into
 *        text values that can be sent across a server
 *        connection. Uses JSON notation to allow easier parsing on
 *        the client side.
 * 
 *        As an example, if a user calls outputLine("test") and
 *        outputMenu("1", "2" "3"), this class would generate a JSON
 *        object that looks like the following:
 * 
 *        {result: "ok", response: [ {call: "outputLine", params: ["test"] }, 
 * 		           {call: "outputMenu", params: ["1", "2", "3"] } ] }
 */
public class PlatformProxy extends Platform {
    /**
     * A list of json objects that represet calls to the Platform interface.
     */
    List<String> callObjects =
        new ArrayList<String>();

    /**
     * Returns the JSON object that was built since reap was last
     * called or since the Platform was initialized.
     */
    public String reap() {
        StringBuilder sb = new StringBuilder();
        sb.append("{result: \"ok\", platformCalls: [");
		
        for (int i = 0; i < callObjects.size(); ++i) {
            if (i != 0)
                sb.append(", ");
			
            sb.append(callObjects.get(i));
        }

        sb.append("] }");
		
        // Restart collecting call objects.
        callObjects.clear();
		
        return sb.toString();
    }

    /**
     * Takes a function name and several params and appends it to the
     * JSON object.
     */
    private void appendCallObject(String fname,
                                  String ... params) {
        StringBuilder sb = new StringBuilder();
        sb.append("{fname: \"" + fname + "\", params: [");
        for (int i = 0 ; i < params.length; ++i) {
            if (i != 0)
                sb.append(", ");

            sb.append("\"" + params[i] + "\"");
        }
		
        sb.append("] }");
		
        callObjects.add(sb.toString());
    }

    /**
     * Appends a call to outputLine to the current list of calls to be returned to the client.
     */
    @Override
    public String outputLine(String line) {
        appendCallObject("outputLine", line);
        return null;
    }

    /**
     * Since all input is handled by the servlet, this method will never be called.
     */
    @Override
    public String retrieveInput(boolean verbose) {
        // This method will not be used by the server.
        return null;
    }

    /**
     * Appends a call to outputString to the current list of calls to be returned to the client.
     */
    @Override
    public String outputString(String input) {
        appendCallObject("outputString", input);
        return null;
    }

    /**
     * Returns the name of the current platform. Since we don't know what the client's platform
     * is, we're returning proxy. 
     */
    @Override
    public String platformName() {
        return "Proxy";
    }

    /**
     * This method is only called by the Android application and will never be called in the context
     * of this server.
     */
    @Override
    public boolean isCommandLinePlatform() {
        // This method will not be used by the server.
        return false; 
    }

    /**
     * Appends a call to outputMenu to the current list of calls to be returned to the client.
     */
    @Override
    public void outputMenu(String numeral,
                           String option,
                           String selection) {
        appendCallObject("outputMenu",
                         numeral,
                         option,
                         selection);
    }

    /**
     * Appends a call to enableOption to the current list of calls to be returned to the client.
     */
    @Override
    public void enableOption(String option) {
        appendCallObject("enableOption",
                         option);

    }

    /**
     * Appends a call to disableAll to the current list of calls to be returned to the client.
     */
    @Override
    public void disableAll(boolean verbose) {
        appendCallObject("disableAll",
                         Boolean.toString(verbose));
    }

    /**
     * Appends a call to addString to the current list of calls to be returned to the client.
     */
    @Override
    public String addString(String Input) {
        appendCallObject("addString",
                         Input);
        return null;
    }

    /**
     * Appends a call to errorLog to the current list of calls to be returned to the client.
     */
    @Override
    public void errorLog(String javaFile, String errorMessage) {
        appendCallObject("errorLog",
                         javaFile,
                         errorMessage);
    }
}
