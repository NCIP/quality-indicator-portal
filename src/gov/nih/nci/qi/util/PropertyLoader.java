// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi.util;

import java.io.InputStream;
import java.util.Properties;

public abstract class PropertyLoader
{

    public static Properties loadProperties(String s, ClassLoader classloader)
    {
        Properties properties;
        InputStream inputstream;
        if(s == null)
            throw new IllegalArgumentException("null input: name");
        if(s.startsWith("/"))
            s = s.substring(1);
        if(s.endsWith(".properties"))
            s = s.substring(0, s.length() - ".properties".length());
        properties = null;
        inputstream = null;
        if(classloader == null)
            classloader = ClassLoader.getSystemClassLoader();
        s = s.replace('.', '/');
        if(!s.endsWith(".properties"))
            s = s.concat(".properties");
        inputstream = classloader.getResourceAsStream(s);
        if(inputstream != null)
            try
            {
            	properties = new Properties();
                properties.load(inputstream);
                inputstream.close();
            }
            catch(Throwable throwable) { }
       
        return properties;
    }

    public static Properties loadProperties(String s)
    {
        return loadProperties(s, Thread.currentThread().getContextClassLoader());
    }

    private PropertyLoader()
    {
    }

    private static final boolean THROW_ON_LOAD_FAILURE = false;
    private static final boolean LOAD_AS_RESOURCE_BUNDLE = false;
    private static final String SUFFIX = ".properties";
}
