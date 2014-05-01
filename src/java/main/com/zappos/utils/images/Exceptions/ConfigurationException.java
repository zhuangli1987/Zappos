/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images.Exceptions;

/**
 * Configuration related exception.
 *
 * <p>
 * This class is mutable and not thread safe.
 * </p>
 *
 * @author Li
 * @version 1.0
 */
public class ConfigurationException extends Exception {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance with a message.
     *
     * @param message
     *            the message
     */
    public ConfigurationException(String message) {
        super(message);
    }

    /**
     * Creates a new instance with a message and a cause.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
