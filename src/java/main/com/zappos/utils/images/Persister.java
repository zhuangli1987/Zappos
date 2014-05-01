/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images;

import com.zappos.utils.images.Exceptions.UtilProcessException;

/**
 * This service provides the function to persist something under the target file folder.
 *
 * <p>
 * Implementations of this interface may not be thread safe.
 * </p>
 *
 * @author Li
 * @version 1.0
 */
public interface Persister {
    /**
     * Persist something under the target file folder.
     * @param fileFolder
     *             the folder path
     * @throws UtilProcessException
     *             if any exception in processing
     */
    public void persist(String fileFolder) throws UtilProcessException;
}
