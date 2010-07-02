// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi.util;

import java.util.Properties;

// Referenced classes of package gov.nih.nci.qi.util:
//            PropertyLoader

public class MessageRetriever
{

    public MessageRetriever()
    {
    }

    public static String getProperty(String s)
    {
        if(myApplicationProperties == null)
        {
            if("".equals(applicationPropertyFileName))
                applicationPropertyFileName = "bundle.application";
            myApplicationProperties = PropertyLoader.loadProperties(applicationPropertyFileName);
        }
        return myApplicationProperties.getProperty(s);
    }

    public static String getMessage(String s)
    {
        if(myMessageProperties == null)
        {
            if("".equals(messagePropertyFileName))
                messagePropertyFileName = "bundle.messages";
            myMessageProperties = PropertyLoader.loadProperties(messagePropertyFileName);
        }
        return myMessageProperties.getProperty(s);
    }

    private static String messagePropertyFileName = "";
    private static String applicationPropertyFileName = "";
    private static final String DEFAULT_PROPERTIES = "bundle.application";
    private static final String DEFAULT_MESSAGES = "bundle.messages";
    private static Properties myApplicationProperties;
    private static Properties myMessageProperties;

}
