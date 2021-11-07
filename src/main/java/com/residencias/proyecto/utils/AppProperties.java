package com.residencias.proyecto.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class AppProperties {

    private static Logger logger = LoggerFactory.getLogger(AppProperties.class);
    private static final String FILE_LOAD_ERROR = "Error opening config file: ";

    private static final Properties AppProps = new Properties();

    static {
        try {

            InputStream in = AppProperties.class.getResourceAsStream("/application.properties");

            AppProps.load(in);

        } catch (Exception e) {
            logger.error(FILE_LOAD_ERROR, e);
        }
        String externalProperties = System.getProperty("app.properties");

        System.setProperty("javax.xml.soap.SAAJMetaFactory", "com.sun.xml.messaging.saaj.soap.SAAJMetaFactoryImpl");

        try (InputStream exteralIn = new FileInputStream(externalProperties)) {
            AppProps.load(exteralIn);
        } catch (NullPointerException e) {
            logger.info("No external properties file");
        } catch (Exception e) {
            logger.error(FILE_LOAD_ERROR, e);
        }

    }

    public static Properties getProperties() {

        return AppProps;
    }

    public static String getProperty(String sPropertyName) {
        return AppProps.getProperty(sPropertyName);
    }

    private AppProperties() {
        throw new IllegalStateException("Utility class");
    }
}