package com.example.expressiontree;

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
     * @@Mitchell, please document this method.
     */
    public List<String> getParams() {
        return params;
    }

    /**
     * @@Mitchell, please document this method.
     */
    public String getFname() {
        return fname;
    }

    /**
     * @@Mitchell, please document this method.
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @@Mitchell, please document this method.
     */
    public void setParams(List<String> params) {
        this.params = params;
    }
}
