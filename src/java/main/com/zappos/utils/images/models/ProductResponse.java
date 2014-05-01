/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images.models;

import java.util.List;

/**
* Bean holding the information of the response.
* <p>
* This class is mutable and therefore not thread safe.
* </p>
*
* @author Li
* @version 1.0
*/
public class ProductResponse {

    /**
     * The HTTP status code.
     */
    private int statusCode;

    /**
     * The message from server.
     */
    private String message;

    /**
     * The message id from server.
     */
    private String messageId;

    /**
     * The error message.
     */
    private String error;

    /**
     * The list of products.
     */
    private List<Product> product;

    /**
     * Returns the HTTP status code.
     *
     * @return the HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the HTTP status code.
     *
     * @param statusCode
     *            the status code prepared for to set.
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * set the server message.
     *
     * @param message
     *              the server message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the server message.
     *
     * @return the server message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * set the server message id.
     *
     * @param messageId
     *              the server message id.
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Returns the server message id.
     *
     * @return the server message id.
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Returns the error message.
     *
     * @return the error message.
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error
     *            the error message prepared for to set.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Returns the list of products.
     *
     * @return the list of products.
     */
    public List<Product> getProduct() {
        return product;
    }

    /**
     * Sets the list of products.
     *
     * @param product
     *            the list of products prepared for to set.
     */
    public void setProduct(List<Product> product) {
        this.product = product;
    }
};
