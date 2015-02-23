package com.example.expressiontree.server;

import java.util.ArrayList;
import java.util.List;

import com.example.expressiontree.model.PlatformCall;
import com.example.expressiontree.model.ServerResponse;

/**
 * @class PlatformProxy
 * 
 * @brief Handles marshalling calls to the Platform interface into instances of
 *        ServerResponse, which can then be converted into GSON and sent back to
 *        the client.
 * 
 *        This class also uses the Thread-Specific Storage pattern to avoid
 *        collisions when processing requests from multiple threads at once.
 */
public class PlatformProxy extends Platform {
    /**
     * The list of platform calls made to this class. Each thread has it's own
     * list.
     */
    ThreadLocal<List<PlatformCall>> mCallObjects =
        new ThreadLocal<List<PlatformCall>>() {
        @Override
        protected List<PlatformCall> initialValue() {
            return new ArrayList<PlatformCall>();
        }
    };

    /**
     * Returns the JSON object that was built since reap() was last called or
     * since the Platform was initialized.
     * 
     * This method is called "reap" because the Platform accrues calls
     * made to it over time and stores them in memory as PlatformCall
     * objects. Then, when the server is ready to send a response back
     * to the client, it collects, or "reaps", the accrued calls into
     * a single ServerResponse object. Each time reap is called, the
     * list of PlatformCalls is cleared out, so that any given call
     * made to this Platform will only be returned by reap() once.
     */
    public ServerResponse reap() {

        // Get the calls made by the current thread.
        List<PlatformCall> callObjects = mCallObjects.get();

        // Construct a response.
        ServerResponse resp = new ServerResponse();

        resp.setResult("ok");
        resp.setPlatformCalls(callObjects);

        // Restart collecting call objects for this thread.
        mCallObjects.set(new ArrayList<>());

        return resp;
    }

    /**
     * Takes a function name and several params and appends it to the
     * JSON object.
     */
    private void appendCallObject(String fname, String... params) {

        // Get the thread-specific storage object
        List<PlatformCall> callObjects = mCallObjects.get();

        List<String> paramList = new ArrayList<String>();

        for (String p : params)
            paramList.add(p);

        callObjects.add(new PlatformCall(fname, paramList));
    }

    /**
     * Appends a call to outputLine to the current list of calls to be returned
     * to the client.
     */
    @Override
	public String outputLine(String line) {
        appendCallObject("outputLine", line);
        return line;
    }

    /**
     * Since all input is handled by the servlet, this method will never be
     * called.
     */
    @Override
	public String retrieveInput(boolean verbose) {
        // This method will not be used by the server.
        return null;
    }

    /**
     * Appends a call to outputString to the current list of calls to be
     * returned to the client.
     */
    @Override
	public String outputString(String input) {
        appendCallObject("outputString", input);
        return input;
    }

    /**
     * Returns the name of the current platform. Since we don't know what the
     * client's platform is, we're returning proxy.
     */
    @Override
	public String platformName() {
        return "Proxy";
    }

    /**
     * This method is only called by the Android application and will never be
     * called in the context of this server.
     */
    @Override
	public boolean isCommandLinePlatform() {
        // This method will not be used by the server.
        return false;
    }

    /**
     * Appends a call to outputMenu to the current list of calls to be returned
     * to the client.
     */
    @Override
	public void outputMenu(String numeral, String option, String selection) {
        appendCallObject("outputMenu", numeral, option, selection);
    }

    /**
     * Appends a call to enableOption to the current list of calls to be
     * returned to the client.
     */
    @Override
	public void enableOption(String option) {
        appendCallObject("enableOption", option);

    }

    /**
     * Appends a call to disableAll to the current list of calls to be returned
     * to the client.
     */
    @Override
	public void disableAll(boolean verbose) {
        appendCallObject("disableAll", Boolean.toString(verbose));
    }

    /**
     * Appends a call to addString to the current list of calls to be returned
     * to the client.
     */
    @Override
	public String addString(String Input) {
        appendCallObject("addString", Input);
        return null;
    }

    /**
     * Appends a call to errorLog to the current list of calls to be returned to
     * the client.
     */
    @Override
	public void errorLog(String javaFile, String errorMessage) {
        appendCallObject("errorLog", javaFile, errorMessage);
    }
}
