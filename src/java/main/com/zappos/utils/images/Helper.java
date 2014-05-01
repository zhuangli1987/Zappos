/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.Properties;

import com.zappos.utils.images.Exceptions.ConfigurationException;

/**
 * Helper class used in this component.
 *
 * <p>
 * This class is immutable and therefore thread safe.
 * </p>
 *
 * @author Li
 * @version 1.0
 */
public final class Helper {

    /**
     * The limit size of id list when make the request.
     */
    public static int LIMIT;

    /**
     * The API URL of product inquire.
     */
    public static String PRODUCT_API_URL;

    /**
     * The API KEY.
     */
    public static String API_KEY;

    /**
     * The path of the image folder.
     */
    public static String IMAGE_PATH;

    /**
     * This method helps to load the properties according the file name.
     * If any exception is happened, the property which has not been set will use the default value.
     * And if limit is not a positive number, it will be changed to 1.
     * @param filename
     *             the file name
     * @throws ConfigurationException
     *             if any exception is happened when loading the properties.
     */
    public static void loadProperties(String filename) throws ConfigurationException {
        checkNullEmtpyString("filename", "Helper::loadProperties", filename);
        Properties properties = new Properties();
        File file = new File(filename);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            properties.load(is);
        } catch (FileNotFoundException e) {
            throw new ConfigurationException("Get FileNotFoundException when loading the properties.", e);
        } catch (SecurityException e) {
            throw new ConfigurationException("Get IOException when loading the properties.", e);
        } catch (IOException e) {
            throw new ConfigurationException("Get IOException when loading the properties.", e);
        } catch (IllegalArgumentException e) {
            throw new ConfigurationException("Get IllegalArgumentException when loading the properties.", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
            PRODUCT_API_URL = properties.getProperty("Product_API_URL", "http://api.zappos.com/Product?id=");
            IMAGE_PATH = properties.getProperty("Image_Path", "images");
            API_KEY = properties.getProperty("API_KEY", "");
            try {
                LIMIT = Math.max(1, Integer.valueOf(properties.getProperty("Limit", "10")));
            } catch(NumberFormatException e) {
                LIMIT = 1;
                throw new ConfigurationException("Limit is invalid. Change to default value.", e);
            }
        }
    }

    /**
     * The method help to check the string.
     * @throws IllegalArgumentException
     *             if the string is null or empty
     * @param name
     *             the name of the filed
     * @param signature
     *             the signature
     * @param s
     *             the string to be checked.
     */
    public static void checkNullEmtpyString(String name, String signature, String s) {
        checkNull(name, signature, s);
        if (s.trim().isEmpty()) {
            throw new IllegalArgumentException(signature
                    + ": The variable "  + name + " should not be Empty.");
        }
    }

    /**
     * The method help to check the object.
     * @throws IllegalArgumentException
     *             if the object is null
     * @param name
     *             the name of the filed
     * @param signature
     *             the signature
     * @param o
     *             the object to be checked.
     */
    public static void checkNull(String name, String signature, Object o) {
        if (o == null) {
            throw new IllegalArgumentException(signature
                    + ": The variable "  + name + " should not be NULL.");
        }
    }

    /**
     * The method helps to close the reader.
     * @param reader
     *             the reader need to be closed.
     */
    public static void closeReader(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            // Ignore
        }
    }

    /**
     * The method helps to close the input stream.
     * @param stream
     *             the stream need to be closed.
     */
    public static void closeInputStream(InputStream stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException e) {
            // Ignore
        }
    }

    /**
     * The method helps to close the output stream.
     * @param stream
     *             the stream need to be closed.
     */
    public static void closeOutpuStream(OutputStream stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException e) {
            // Ignore
        }
    }

    /**
     * The method helps to close the HTTP connection.
     * @param connection
     *             the connection need to be closed.
     */
    public static void closeConnection(HttpURLConnection connection) {
        if (connection != null) {
            connection.disconnect();
        }
    }
}
