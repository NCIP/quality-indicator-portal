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

import java.io.PrintStream;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

public class SessionManager
{

    public SessionManager(HttpServletRequest httpservletrequest, int i)
    {
        httpservletrequest.getSession().setMaxInactiveInterval(i);
        httpservletrequest.getSession().setAttribute("nci.mmhcc.sessionId", httpservletrequest.getSession().getId());
    }

    public static void verifySession(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, String s)
    {
        setSessionProperties(httpservletrequest.getSession(), inactive);
        if(httpservletrequest.getSession().isNew() || !httpservletrequest.getSession().getId().equals(httpservletrequest.getSession().getAttribute("nci.mmhcc.sessionId")))
            try
            {
                RequestDispatcher requestdispatcher = httpservletrequest.getRequestDispatcher(s);
                requestdispatcher.forward(httpservletrequest, httpservletresponse);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
    }

    public static void createSession(HttpServletRequest httpservletrequest, String s)
    {
        Boolean boolean1 = new Boolean(s);
        if(httpservletrequest.getSession(false) == null && boolean1.booleanValue())
            httpservletrequest.getSession(true);
    }

    public static void setId(HttpServletRequest httpservletrequest)
    {
        httpservletrequest.getSession().setAttribute("nci.mmhcc.loginId", httpservletrequest.getSession().getId());
    }

    public static String getId(HttpServletRequest httpservletrequest)
    {
        return (String)httpservletrequest.getSession().getAttribute("nci.mmhcc.loginId");
    }

    public static void setSessionProperties(HttpSession httpsession, int i)
    {
        httpsession.setAttribute("nci.mmhcc.loginId", httpsession.getId());
        httpsession.setMaxInactiveInterval(i);
    }

    public static void setSessionIdentity(HttpSession httpsession, Hashtable hashtable)
    {
        Set set = hashtable.keySet();
        String s;
        for(Iterator iterator = set.iterator(); iterator.hasNext(); httpsession.setAttribute(s, hashtable.get(s)))
            s = (String)iterator.next();

    }

    public static void setSessionIdentity(HttpSession httpsession, Hashtable hashtable, String s)
    {
        httpsession.setAttribute(s, hashtable);
    }

    public static void setSessionIdentity(HttpSession httpsession, String s, String s1)
    {
        httpsession.setAttribute("name", s);
        httpsession.setAttribute("password", s1);
    }

    public static Vector loadSessionObjects(HttpSession httpsession, String as[])
    {
        Vector vector = new Vector();
        for(int i = 0; i < as.length; i++)
            try
            {
                vector.add(Class.forName(as[i]));
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                System.out.println((new StringBuilder()).append("The Class ").append(as[i]).append(" Not Found-- Check Classpath").toString());
            }

        return vector;
    }

    public static void confirmSessionObjects(HttpSession httpsession)
    {
    }

    public static void setSessionIdentity(HttpSession httpsession, int i)
    {
        inactive = i;
    }

    private HttpSession session;
    private static int inactive = -1;

}
