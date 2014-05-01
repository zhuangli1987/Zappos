/*
 * Copyright (C) 2014, All Rights Reserved.
 */
package com.zappos.utils.images;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.List;

import com.zappos.utils.images.Exceptions.UtilProcessException;
import com.zappos.utils.images.models.Product;

/**
 * This implementations provides the function to persist images under the target file folder.
 *
 * <p>
 * This class is immutable and therefore thread safe.
 * </p>
 *
 * <p>
 * Usage : <br>
 * Assume the fileFolder is a valid folder path and products is a list of products.<br>
 * Persister persister = PersisterImpl(products);<br>
 * persister.persist(fileFolder);<br>
 * // check the path, you will see the images if the products list is valid.<br>
 * </p>
 * @author Li
 * @version 1.0
 */
public class PersisterImpl implements Persister{

    /**
     * The list of products.
     */
    private List<Product> products;

    /**
     * The creator is initialized with the list of products.
     * @param products
     *             the list of products
     */
    public PersisterImpl(List<Product> products) {
        this.products = products;
    }

    /**
     * Persist the images under the target file folder.
     * @param fileFolder
     *             the folder path
     * @throws UtilProcessException
     *             if any exception in processing
     * @throws IllegalArgumentException
     *             if fileFolder is empty or null
     */
    @Override
    public void persist(String fileFolder) throws UtilProcessException {
        Helper.checkNullEmtpyString("fileFolder", "PersisterImpl::persist", fileFolder);
        File imgdir = new File(fileFolder);
        String path = null;
        try {
            if (!imgdir.exists()) {
                imgdir.mkdir();
            }
            path = imgdir.getAbsolutePath();
        } catch (SecurityException e) {
            throw new UtilProcessException("Get SecurityException when fetch and save.", e);
        }
        for (Product product : products) {
            String url = product.getDefaultImageUrl();
            try {
                Helper.checkNullEmtpyString("url", "UrlFetchImpl::fetch", url);
                String[] tokens = url.split("\\.");
                String filename = "unknown";
                if (tokens.length > 0) {
                	filename = tokens[tokens.length - 1];
                }
                Helper.checkNullEmtpyString("filename", "UrlFetchImpl::fetch", filename);
                filename = product.getProductId() + '.' + filename;
                // begin to download the image
                writeImageToDisk(getImageFromNetByUrl(url), path +  File.separator + filename);
            } catch (UtilProcessException e) {
                // ignore all exception and just output the information
                System.out.println("Error to fetch " + product.getProductId() + " by " + url + ".");
                e.printStackTrace();
            }
        }
    }

    /**
     * This method helps to read from input stream and covert to bytes.
     * @param inStream
     *             the input stream need to be read.
     * @return the bytes
     * @throws UtilProcessException
     *             if any exception happened
     */
    private byte[] readInputStream(InputStream inStream) throws UtilProcessException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = inStream.read(buffer)) != -1){
                outStream.write(buffer, 0, len);
            }
            return outStream.toByteArray();
        }catch (IOException e) {
            throw new UtilProcessException("Get IOException when read input stream.", e);
        } finally {
            Helper.closeOutpuStream(outStream);
        }

    }

    /**
     * This method helps to get the image bytes according URL.
     * @param strUrl
     *             the target URL.
     * @return the bytes of a image
     * @throws UtilProcessException
     *             if any exception happened
     */
    private byte[] getImageFromNetByUrl(String strUrl) throws UtilProcessException{
        HttpURLConnection conn = null;
        InputStream ins = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.connect();
            ins = conn.getInputStream();
            return readInputStream(ins);
        } catch(MalformedURLException e) {
            throw new UtilProcessException("Get MalformedURLException when get image by url.", e);
        } catch (SocketTimeoutException e) {
            throw new UtilProcessException("Get SocketTimeoutException when get image by url.", e);
        } catch (UnknownServiceException e) {
            throw new UtilProcessException("Get UnknownServiceException when get image by url.", e);
        } catch (IOException e) {
            throw new UtilProcessException("Get IOException when get image by url.", e);
        }  finally {
            Helper.closeInputStream(ins);
            Helper.closeConnection(conn);
        }
    }

    /**
     * This method helps to save the image into the file.
     * @param img
     *             the image bytes
     * @param fileName
     *             the file name
     * @throws UtilProcessException
     *             if any exception happened
     */
    private void writeImageToDisk(byte[] img, String fileName) throws UtilProcessException {
        FileOutputStream fos = null;
        try {
            File file = new File(fileName);
            fos = new FileOutputStream(file);
            fos.write(img);
            fos.flush();
            System.out.println("Successful save to " + fileName);
        } catch (FileNotFoundException e) {
            throw new UtilProcessException("Get FileNotFoundException when write image to disk.", e);
        } catch (SecurityException e) {
            throw new UtilProcessException("Get SecurityException when write image to disk.", e);
        } catch (IOException e) {
            throw new UtilProcessException("Get IOException when write image to disk.", e);
        } finally {
            Helper.closeOutpuStream(fos);
        }
    }
}
