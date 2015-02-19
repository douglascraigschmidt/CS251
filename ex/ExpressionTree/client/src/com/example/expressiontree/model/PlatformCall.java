package com.example.expressiontree.model;

import java.util.List;

/**
 * @class PlatformCall
 * 
 * @brief A POJO object that represents a call to the platform interface.
 *
 *        Note: This class will be instantiated by a JSON interpreter
 *        that the IDE will not detect. Therefore, don't try and
 *        refactor this file by renaming any of the members.
 */
public class PlatformCall {
    /**
     * The name of the function in Platform to call.
     */
    public String fname;

    /**
     * The parameters to pass to the function.
     */
    public List<String> params;

    /**
     * Constructor.
     */
    public PlatformCall(String fname, List<String> params) {
        this.fname = fname;
        this.params = params;
    }
}
