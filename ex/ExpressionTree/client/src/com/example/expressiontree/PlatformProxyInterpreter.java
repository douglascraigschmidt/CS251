package com.example.expressiontree;

import java.lang.reflect.Method;

import com.example.expressiontree.model.PlatformCall;
import com.example.expressiontree.model.ServerResponse;

/**
 * @class PlatformProxyInterpreter
 * 
 * @brief Demarshalls the data created by PlatformProxy and interprets
 *        it, calling the Platform functions specified by the data.
 */
public class PlatformProxyInterpreter {
    /**
     * Interprets the given server response and makes the
     * corresponding calls to Platform singleton.
     */
    public static void interpret(Platform platform,
                                 ServerResponse response) {
        if (response.result.toLowerCase().equals("ok")) {
            for (PlatformCall call : response.platformCalls) {
                if (call.fname.equals("outputLine"))
                    platform.outputLine(call.params.get(0));
                if (call.fname.equals("outputString"))
                    platform.outputString(call.params.get(0));
                if (call.fname.equals("outputMenu"))
                    platform.outputMenu(call.params.get(0),
                                        call.params.get(1),
                                        call.params.get(2));
                if (call.fname.equals("enableOption"))
                    platform.enableOption(call.params.get(0));
                if (call.fname.equals("disableAll"))
                    platform.disableAll(Boolean.valueOf(call.params.get(0)));
                if (call.fname.equals("addString"))
                    platform.addString(call.params.get(0));
                //if (call.fname.equals("errorLog"))
                    // @@ TODO - Is this secure?
                    //platform.errorLog(call.params.get(0),
                    //                call.params.get(1));
            }
        }
    }
}
