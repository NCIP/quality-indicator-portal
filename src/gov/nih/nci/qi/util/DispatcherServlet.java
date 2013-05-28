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
import java.io.PrintStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class DispatcherServlet extends HttpServlet
{

    public DispatcherServlet()
    {
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        String s = httpservletrequest.getParameter("url");
        String s1 = httpservletrequest.getParameter("id");
        System.out.println((new StringBuilder()).append("dispatchURL before: ").append(s).toString());
        System.out.println((new StringBuilder()).append("id before: ").append(s1).toString());
        if(s == null)
            s = "Error.html";
        System.out.println((new StringBuilder()).append("Dispatcher: ").append(s).toString());
        RequestDispatcher requestdispatcher = httpservletrequest.getRequestDispatcher(s);
        requestdispatcher.forward(httpservletrequest, httpservletresponse);
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        doGet(httpservletrequest, httpservletresponse);
    }
}
