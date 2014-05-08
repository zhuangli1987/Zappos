/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.zappos.utils.images.Exceptions.ConfigurationException;
import com.zappos.utils.images.Exceptions.UtilProcessException;
import com.zappos.utils.images.models.Product;
import com.zappos.utils.images.models.ProductRequest;
import com.zappos.utils.images.models.ProductResponse;

/**
 * The main class of the utility.
 * See the method printUsage for the usage of this utility.
 *
 * <p>
 * This class should be thread safe.
 * </p>
 *
 * @author Li
 * @version 1.0
 */
public final class Utility {

    /**
     * Print help in console.
     */
    private static void printUsage() {
        System.out.println(" Usage: getpic [-h] [File]");
        System.out.println(" Please modify the conf.ini first.");
        System.out.println(" Download the images according File and save them.");
        System.out.println(" One SKU per line in the File.");
        System.out.println(" -h output the help.");
    }

    /**
     * This method convert the JSON to the product and put it into list.
     * @param urlRequest
     *             the URL request
     * @param pr
     *             the bean of product request
     * @return the list of products.
     * @throws UtilProcessException
     *             if any error while processing.
     */
    private static List<Product> buildFromJson(UrlRequest urlRequest,  ProductRequest pr) throws UtilProcessException {
        String request = pr.makeRequest();
        try {
            System.out.println("Send the request to: " + request);
            String json = urlRequest.getResponse(request).trim();
            Gson gson = new Gson();
            ProductResponse productResponse = gson.fromJson(json, ProductResponse.class);
            if (productResponse.getStatusCode() != 200) {
                if (productResponse.getStatusCode() == 400) {
                    System.out.println("Unsuccessful request: code["
                            + productResponse.getStatusCode() + "] " + productResponse.getMessage());
                } else {
                    System.out.println("Unsuccessful request: code["
                            + productResponse.getStatusCode() + "] " + productResponse.getError());
                }
            } else {
                for (Product product : productResponse.getProduct()) {
                    System.out.println("Get image URL: " + product.getDefaultImageUrl());
                }
                return productResponse.getProduct();
            }
        } catch (UtilProcessException e) {
            System.out.println("Can not execute the request : " + request + ".");
            System.out.println("Please check the SKU in this request.");
            throw e;
        }
        return new ArrayList<Product>();
    }

    /**
     * The entrance of the utility.
     * @param args
     *             the arguments.
     * @throws Exception
     *             if the fault error happened.
     */
    public static void main(String args[]) throws Exception {
        for (String arg : args) {
            if (arg == "-h") {
                printUsage();
                return;
            }
        }
        if (args.length != 1) {
            printUsage();
            return;
        }
        File file = new File(args[0]);
        try {
            if (!file.exists()) {
                throw new ConfigurationException("The file " + args[0] + " does not exist.");
            }
        } catch (SecurityException e) {
            throw new ConfigurationException("Get SecurityException when check the file " + args[0] + ".");
        }
        try {
            Helper.loadProperties("conf.ini");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        UrlRequest urlRequest = new UrlRequestImpl();

        List<Product> products = new ArrayList<Product>();

        BufferedReader br = null;
        FileReader fr = null;
        List<String> unsucessful = new ArrayList<String>();
        try {
            ProductRequest pr = new ProductRequest();
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = null;
            // This loop can be parallelized, to reduce the stress of API Server, not do it this time.
            while (true) {
                line = br.readLine();
                if (line == null) {
                    try {
                        products.addAll(buildFromJson(urlRequest, pr));
                    } catch (UtilProcessException e) {
                    	e.printStackTrace();
                        unsucessful.addAll(pr.getList());
                    }
                    break;
                }
                line = line.trim();
                if (!line.isEmpty() && !pr.add(line)) {
                    try {
                        products.addAll(buildFromJson(urlRequest, pr));
                    } catch (UtilProcessException e) {
                    	e.printStackTrace();
                        unsucessful.addAll(pr.getList());
                    }
                    pr = new ProductRequest();
                    pr.add(line);
                }
            }
        } finally {
            Helper.closeReader(fr);
            Helper.closeReader(br);
        }

        Persister persister = new PersisterImpl(products);
        persister.persist(Helper.IMAGE_PATH);

        if (unsucessful.size() != 0) {
            System.out.println("Following SKU request(s) is(are) not successful :");
            for (String SKU : unsucessful) {
                System.out.println(SKU);
            }
        }
        System.out.println("Done!");
    }
}
