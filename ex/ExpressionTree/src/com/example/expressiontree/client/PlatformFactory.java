package com.example.expressiontree.client;

import java.util.HashMap;

/**
 * @class PlatformFactory
 * 
 * @brief This class is a factory that is responsible for building the
 *        designated @a Platform implementation at runtime.
 */
public class PlatformFactory {
    /**
     * Enumeration distinguishing platforms Android from plain ol' Java.
     */
    private enum PlatformType {
        ANDROID,
        PLAIN_JAVA
    };

    /**
     * Keep track of the type of platform.  This value won't change at
     * runtime.
     */
    private final PlatformType mPlatformType =
        System.getProperty("java.specification.vendor").indexOf("Android") >= 0
            ? PlatformType.ANDROID
            : PlatformType.PLAIN_JAVA;

    /** 
     * This interface uses the Command pattern to create @a Platform
     * implementations at runtime.
     */
    @FunctionalInterface
    private static interface IPlatformFactoryCommand {
        public Platform execute();
    }
	
    /**
     * HashMap used to map strings containing the Java platform names
     * and dispatch the execute() method of the associated @a Platform
     * implementation.
     */
    private HashMap<PlatformType, IPlatformFactoryCommand> platformMap = 
        new HashMap<>();
	
    /** 
     * Ctor that stores the objects that perform input and output for
     * a particular platform, such as CommandLinePlatform or the
     * AndroidPlatform.
     */
    public PlatformFactory(final Object input,
                           final Object output,
                           final Object activity) {
        if (mPlatformType == PlatformType.ANDROID)
            // Map the PlatformType of ANDROID to a command object
            // that creates an @a PlatformStrategyAndroid
            // implementation.
            platformMap.put(PlatformType.ANDROID,
                            () -> null);
        else if (mPlatformType == PlatformType.PLAIN_JAVA)
            // Map the PlatformType of PLAIN_JAVA to a command object
            // that creates an @a CommandLinePlatform implementation.
            platformMap.put(PlatformType.PLAIN_JAVA,
                            () -> new CommandLinePlatform(input,
                                                          output));
    }

    /** 
     * Create a new @a Platform object based on underlying Java
     * platform.
     */
    public Platform makePlatform() {
        return platformMap.get(mPlatformType).execute();
    }
}
