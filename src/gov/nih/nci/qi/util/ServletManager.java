/*L
 * Copyright SAIC
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
 */

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi.util;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

// Referenced classes of package gov.nih.nci.qi.util:
//            ServletLoadException

public class ServletManager
{

    public ServletManager()
    {
    }

    public void setHeaders(HttpServletResponse httpservletresponse, HashMap hashmap)
    {
        Set set = hashmap.keySet();
        String s;
        for(Iterator iterator = set.iterator(); iterator.hasNext(); httpservletresponse.setHeader(s, (String)hashmap.get(s)))
            s = (String)iterator.next();

    }

    public Properties loadProperties(String s)
        throws ServletLoadException
    {
        java.io.InputStream inputstream = getClass().getResourceAsStream(s);
        Properties properties = new Properties();
        try
        {
            properties.load(inputstream);
        }
        catch(IOException ioexception)
        {
            throw new ServletLoadException("Properties File Did Not Load");
        }
        return properties;
    }

    public void bindToServletContext(ServletContext servletcontext, String s, Object obj)
    {
        servletcontext.setAttribute(s, obj);
    }
}
