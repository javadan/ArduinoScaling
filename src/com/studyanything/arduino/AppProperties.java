/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studyanything.arduino;

import static com.studyanything.arduino.YoutubeCaptureAndReduce.properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Daniel
 */
public class AppProperties {
    Properties prop = new Properties();
    public AppProperties() {
        
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public Properties getProperties()
    {
        return prop;
    }
    
    public String getOutputDataDir()
    {
        String s = prop.getProperty("outputDataDir");
        if (!s.endsWith("/"))
        {
            return s + "/";
        }
        return s;
    }
    public String getOutputBmpDir()
    {
        String s = prop.getProperty("outputBmpDir");
        if (!s.endsWith("/"))
        {
            return s + "/";
        }
        return s;
    }
    
    public Integer getBufferSize()
    {
          String s = prop.getProperty("bufferSize");
        return Integer.parseInt(s);
    }
    
    
    public Integer getX()
    {
        String s = prop.getProperty("x");
        return Integer.parseInt(s);
    }

    public Integer getY()
    {
        String s = prop.getProperty("y");
        return Integer.parseInt(s);
    }
    public Integer getW()
    {
        String s = prop.getProperty("width");
        return Integer.parseInt(s);
    }
    public Integer getH()
    {
        String s = prop.getProperty("height");
        return Integer.parseInt(s);
    }
    public Integer getScaledW()
    {
        String s = prop.getProperty("scaledWidth");
        return Integer.parseInt(s);
    }
    
    public Integer getScaledH()
    {
        String s = prop.getProperty("scaledHeight");
        return Integer.parseInt(s);
    }

    
}
