/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images;

import com.zappos.utils.images.Exceptions.UtilProcessException;

/**
 * This service provides the function to send the request and get the response according URL.
 *
 * <p>
 * Implementations of this interface may not be thread safe.
 * </p>
 *
 * @author Li
 * @version 1.0
 */
public interface UrlRequest {
    /**
     * Get the response according URL.
     * @param urlPath
     *             the target URL
     * @return the response string
     * @throws UtilProcessException
     *             if any exception in processing
     */
    public String getResponse(String urlPath) throws UtilProcessException;
}
