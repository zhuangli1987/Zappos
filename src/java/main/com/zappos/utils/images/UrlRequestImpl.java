/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownServiceException;

import com.zappos.utils.images.Exceptions.UtilProcessException;

/**
 * This implementations provides the function to send the request and get the response according URL.
 *
 * <p>
 * This class is immutable and therefore thread safe.
 * </p>
 *
 * <p>
 * Usage : <br>
 * Let the urlPath as a valid URL request.<br>
 * UrlRequest urlRequest= UrlRequestImpl();<br>
 * String result = urlRequest.getResponse(urlPath);<br>
 * // result is the response of the request.<br>
 * </p>
 *
 * @author Li
 * @version 1.0
 */
public class UrlRequestImpl implements UrlRequest {

    /**
     * Get the response according URL.
     * @param urlPath
     *             the target URL
     * @return the response string
     * @throws UtilProcessException
     *             if any exception in processing
     * @throws IllegalArgumentException
     *             if urlPath is empty or null.
     */
    @Override
    public String getResponse(String urlPath) throws UtilProcessException {
        Helper.checkNullEmtpyString("urlPath", "UrlRequestImpl::getResponse", urlPath);
        HttpURLConnection conn = null;
        InputStream ins = null;
        Reader reader = null;
        BufferedReader bf = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            ins = conn.getInputStream();
            reader = new InputStreamReader(ins);
            bf = new BufferedReader(reader);
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = bf.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch(MalformedURLException e) {
            throw new UtilProcessException("Get MalformedURLException when get response from url.", e);
        } catch (UnknownServiceException e) {
            throw new UtilProcessException("Get UnknownServiceException when get response from url.", e);
        } catch (SocketTimeoutException e) {
            throw new UtilProcessException("Get SocketTimeoutException when get response from url.", e);
        } catch (IOException e) {
            throw new UtilProcessException("Get IOException when get response from url.", e);
        } finally {
            Helper.closeReader(bf);
            Helper.closeReader(reader);
            Helper.closeInputStream(ins);
            Helper.closeConnection(conn);
        }
    }
}
