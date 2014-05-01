/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images.models;

/**
* Bean holding the information for Product.
* <p>
* This class is mutable and therefore not thread safe.
* </p>
*
* @author Li
* @version 1.0
*/
public class Product {

    /**
     * The product id.
     */
    private String productId;

    /**
     * The brand id.
     */
    private String brandId;

    /**
     * The brand name.
     */
    private String brandName;

    /**
     * The product name.
     */
    private String productName;

    /**
     * The product URL.
     */
    private String defaultProductUrl;

    /**
     * The image URL.
     */
    private String defaultImageUrl;

    /**
     * Returns the product id.
     *
     * @return the product id.
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the product id.
     *
     * @param productId
     *            the product id prepared for to set.
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Returns the brand id.
     *
     * @return the brand id.
     */
    public String getBrandId() {
        return brandId;
    }

    /**
     * Sets the brand id.
     *
     * @param brandId
     *            the brand id prepared for to set.
     */
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    /**
     * Returns the brand name.
     *
     * @return the brand name.
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Sets the brand name.
     *
     * @param brandName
     *            the brand name prepared for to set.
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * Returns the product name.
     *
     * @return the product name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the product name.
     *
     * @param productName
     *            the product name prepared for to set.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Returns the product URL.
     *
     * @return the product URL.
     */
    public String getDefaultProductUrl() {
        return defaultProductUrl;
    }

    /**
     * Sets the product URL.
     *
     * @param defaultProductUrl
     *            the product URL prepared for to set.
     */
    public void setDefaultProductUrl(String defaultProductUrl) {
        this.defaultProductUrl = defaultProductUrl;
    }

    /**
     * Returns the image URL.
     *
     * @return the image URL.
     */
    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    /**
     * Sets the image URL.
     *
     * @param defaultImageUrl
     *            the image URL prepared for to set.
     */
    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }
};
