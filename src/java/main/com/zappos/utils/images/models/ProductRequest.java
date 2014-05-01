/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images.models;

import java.util.ArrayList;
import java.util.List;

import com.zappos.utils.images.Helper;

/**
* Bean holding the information of the request.
* <p>
* This class is mutable and therefore not thread safe.
* </p>
*
* @author Li
* @version 1.0
*/
public class ProductRequest {

    /**
     * The id list of the products.
     */
    private List<String> ids;

    /**
     * The creator which initializes the list of id.
     */
    public ProductRequest() {
        ids = new ArrayList<String>();
    }

    /**
     * This method will add the id into the request if it has enough space.
     * @param id
     * @return true if add successful, false otherwise.
     */
    public boolean add(String id) {
        if (ids.size() == Helper.LIMIT) {
            return false;
        }
        ids.add(id);
        return true;
    }

    /**
     * This method return a deep copy of list.
     * @return the deep copy of list.
     */
    public List<String> getList() {
        return new ArrayList<String>(ids);
    }
    /**
     * Convert the request to the request URL.
     * @return empty string if there is no items should be request
     *         , otherwise return the request URL.
     */
    public String makeRequest() {
        StringBuilder sb = new StringBuilder();
        if (ids.size() != 0) {
            sb.append(Helper.PRODUCT_API_URL);
            sb.append('[');
            for (int i = 0; i < ids.size(); ++i) {
                if (i != 0) {
                    sb.append(",");
                }
                sb.append("\"");
                sb.append(ids.get(i));
                sb.append("\"");
            }
            sb.append("]&key=");
            sb.append(Helper.API_KEY);
        }
        return sb.toString();
    }
};
