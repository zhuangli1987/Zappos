/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images.Exceptions;

/**
 * Process related exception.
 *
 * <p>
 * This class is mutable and not thread safe.
 * </p>
 *
 * @author Li
 * @version 1.0
 */
public class UtilProcessException extends Exception {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 2L;

    /**
     * Creates a new instance with a message.
     *
     * @param message
     *            the message
     */
    public UtilProcessException(String message) {
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
    public UtilProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
