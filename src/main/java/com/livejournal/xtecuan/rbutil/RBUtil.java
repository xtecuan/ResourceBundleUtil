/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livejournal.xtecuan.rbutil;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author xtecuan
 */
public class RBUtil {

    private String basename;
    private String path;
    private ClassLoader loader;

    private ResourceBundle enUs;
    private ResourceBundle esES;
    private ResourceBundle frFR;
    private ResourceBundle ptBR;

    private void initLoader() {
        File f = new File(getPath());
        URL[] urls = new URL[1];
        try {
            urls[0] = f.toURI().toURL();
            this.loader = loader = new URLClassLoader(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initResourceBundles() {
        this.enUs = getBundleFor("en", "US");
        this.esES = getBundleFor("es", "ES");
        this.ptBR = getBundleFor("pt", "BR");
        this.frFR = getBundleFor("fr", "FR");
    }

    public String getBasename() {
        return basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RBUtil(String basename, String path) {
        this.basename = basename;
        this.path = path;
        initLoader();
        initResourceBundles();
    }

    public ResourceBundle getBundleFor(String language, String country) {
        return ResourceBundle.getBundle(getBasename(), new Locale(language, country), loader);
    }

    public String getResource(String key, ResourceBundle bundle) {
        return bundle.getString(key);
    }

    public String getResourceWithParams(String key, String[] params, ResourceBundle bundle) {
        String result = getResource(key, bundle);
        for (int i = 0; i < params.length; i++) {
            result = result.replace("{" + i + "}", params[i]);
        }
        return result;
    }

    public String getResourceEN(String key) {
        return getResource(key, enUs);
    }

    public String getResourceWithParamsEN(String key, String[] params) {
        return getResourceWithParams(key, params, enUs);
    }

    public String getResourceES(String key) {
        return getResource(key, esES);
    }

    public String getResourceWithParamsES(String key, String[] params) {
        return getResourceWithParams(key, params, esES);
    }

    public String getResourcePT(String key) {
        return getResource(key, ptBR);
    }

    public String getResourceWithParamsPT(String key, String[] params) {
        return getResourceWithParams(key, params, ptBR);
    }

    public String getResourceFR(String key) {
        return getResource(key, frFR);
    }

    public String getResourceWithParamsFR(String key, String[] params) {
        return getResourceWithParams(key, params, frFR);
    }

    public static void main(String[] args) {

        RBUtil util = new RBUtil("sample", "C:\\Work\\JavaProjects\\RBUtil\\src\\main\\resources\\com\\livejournal\\xtecuan\\bundles");
        System.out.println(util.getResourceWithParamsEN("test.formatted",
                new String[]{"Julian","Rivera Pineda","xtecuan@gmail.com"}));
    }
}
