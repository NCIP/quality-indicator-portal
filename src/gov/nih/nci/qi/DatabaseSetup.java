// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi;

import com.bigfoot.bugar.servlet.http.MultipartFormData;
import gov.nih.nci.qi.db.DatabaseAccess;
import gov.nih.nci.qi.util.Label;
import gov.nih.nci.qi.util.Lookup;
import gov.nih.nci.qi.util.QISession;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DatabaseSetup
{

    public DatabaseSetup()
    {
    }

    public void setDBProperties(Properties properties)
    {
        if(db == null)
        {
            db = new DatabaseAccess(properties);
            Lookup lookup = Lookup.getInstance();
        }
    }

    public boolean getDBStatus()
    {
        return db != null;
    }

    public static boolean checkRequiredFeilds(HttpServletRequest httpservletrequest, String s)
    {
        boolean flag = false;
        Enumeration enumeration = httpservletrequest.getParameterNames();
        Vector vector = new Vector();
        boolean flag1 = false;
        do
        {
            if(!enumeration.hasMoreElements())
                break;
            String s1 = (String)enumeration.nextElement();
            String s2 = "required_";
            if(s1.indexOf(s2) != -1 && httpservletrequest.getParameter(s1).length() <= 0)
            {
                flag1 = true;
                vector.add(s1.substring(s2.length()));
            }
        } while(true);
        if(flag1)
        {
            httpservletrequest.setAttribute("gov.nih.nci.qi.MissingFeilds", vector);
            httpservletrequest.setAttribute("nci.mmhcc.navBar", s);
            flag = true;
        }
        return flag;
    }

    public static boolean checkMissingRequiredFields(HttpServletRequest httpservletrequest)
    {
        boolean flag = false;
        Enumeration enumeration = httpservletrequest.getParameterNames();
        Vector vector = new Vector();
        boolean flag1 = false;
        do
        {
            if(!enumeration.hasMoreElements())
                break;
            String s = (String)enumeration.nextElement();
            String s1 = "required_";
            if(s.indexOf(s1) != -1 && httpservletrequest.getParameter(s).length() <= 0)
            {
                flag1 = true;
                vector.add(s.substring(s1.length()));
            }
        } while(true);
        if(flag1)
        {
            httpservletrequest.setAttribute("gov.nih.nci.qi.MissingFeilds", vector);
            flag = true;
        }
        return flag;
    }

    public static boolean checkMissingRequiredFields(HttpServletRequest httpservletrequest, MultipartFormData multipartformdata)
    {
        boolean flag = false;
        Enumeration enumeration = multipartformdata.getParameterNames();
        Vector vector = new Vector();
        boolean flag1 = false;
        do
        {
            if(!enumeration.hasMoreElements())
                break;
            String s = (String)enumeration.nextElement();
            String s1 = "required_";
            if(s.indexOf(s1) != -1 && multipartformdata.getParameter(s).length() <= 0)
            {
                flag1 = true;
                vector.add(s.substring(s1.length()));
            }
        } while(true);
        if(flag1)
        {
            httpservletrequest.setAttribute("nci.mmhcc.MissingFeilds", vector);
            flag = true;
        }
        return flag;
    }

    public static void gotoSuccessPage(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, String s, String s1, String s2, String s3)
        throws ServletException, IOException
    {
        httpservletrequest.setAttribute("nci.mmhcc.navBar", s);
        httpservletrequest.setAttribute("nci.mmhcc.prevPageName", s1);
        httpservletrequest.setAttribute("nci.mmhcc.prevPageAddress", s2);
        httpservletrequest.setAttribute("nci.mmhcc.nextPageAddress", s3);
        gotoPage("successfulAdd.jsp", httpservletrequest, httpservletresponse);
    }

    public static void setPageExpiration(HttpServletResponse httpservletresponse)
    {
        long l = System.currentTimeMillis();
        long l1 = 0x1b7740L;
        long l2 = l + l1;
        httpservletresponse.setHeader("Cache-Control", (new StringBuilder()).append("max-age=").append(l2).toString());
        httpservletresponse.setDateHeader("Expires", l2);
    }

    private static void gotoPage(String s, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        RequestDispatcher requestdispatcher = httpservletrequest.getRequestDispatcher(s);
        requestdispatcher.forward(httpservletrequest, httpservletresponse);
    }

    public static String checkForNull(String s)
    {
        if(s == null)
            s = "&nbsp;";
        return s;
    }

    public static String checkForNull(Long long1)
    {
        String s = null;
        if(long1 == null)
            s = "&nbsp;";
        else
            s = long1.toString();
        return s;
    }

    public static String removeNull(String s)
    {
        if(s == null)
            s = "";
        return s;
    }

    public static String removeNull(Long long1)
    {
        String s = null;
        if(long1 == null)
            s = "";
        else
            s = long1.toString();
        return s;
    }

    public static String removeNull(Long long1, Long long2, String s)
    {
        String s1 = null;
        if(long1 == null && long2 == null && s == null)
            s1 = "";
        else
            s1 = (new StringBuilder()).append(long1.toString()).append(long2.toString()).append(s).toString();
        return s1;
    }

    public static String pupulateList(QISession qisession, String s, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s3;
        for(Enumeration enumeration = qisession.getObjectID(s); enumeration.hasMoreElements(); stringbuffer.append(s3))
        {
            Label label = (Label)enumeration.nextElement();
            Long long1 = label.getId();
            String s2 = label.getLabelName();
            if(s2.indexOf("-2") != -1)
                s2 = (new StringBuilder()).append(s2).append("(Joe Gray Lab)").toString();
            else
            if(s2.indexOf("-3") != -1)
                s2 = (new StringBuilder()).append(s2).append("(Kathy Conway Dorsey Lab)").toString();
            else
            if(s2.indexOf("-4") != -1)
                s2 = (new StringBuilder()).append(s2).append("(Chuck Perou Lab)").toString();
            else
                s2 = (new StringBuilder()).append(s2).append("(Lance Liotta Lab)").toString();
            System.err.println((new StringBuilder()).append("enum get object id:").append(long1).toString());
            System.err.println((new StringBuilder()).append("Populate LIst Name IS:").append(s2).toString());
            s3 = null;
            s3 = (new StringBuilder()).append("<li> <a href=\"DispatcherServlet?url=").append(s1).append("&id=").append(long1).append("\">").append(s2).append("</a>").toString();
            System.err.println((new StringBuilder()).append("string: ").append(s3).toString());
        }

        return stringbuffer.toString();
    }

    private static String pupulateRelatedList(QISession qisession, Long long1, String s, String s1, String s2)
    {
        StringBuffer stringbuffer = new StringBuffer();
        Enumeration enumeration = qisession.getObjectID(s);
        do
        {
            if(!enumeration.hasMoreElements())
                break;
            Label label = (Label)enumeration.nextElement();
            Long long2 = label.getId();
            String s4 = label.getLabelName();
            Long long3 = label.getRelatedId();
            System.err.println((new StringBuilder()).append("enum get object id:").append(long2).toString());
            if(long3 != null && long3.equals(long1))
            {
                stringbuffer.append("<ul>");
                String s3 = (new StringBuilder()).append("<li> <a href=\"DispatcherServlet?url=").append(s1).append("&id=").append(long2).append("&type=").append(s2).append("&relatedId=").append(long3).append("\">").append(s4).append("</a>").toString();
                System.err.println((new StringBuilder()).append("string: ").append(s3).toString());
                stringbuffer.append(s3);
                stringbuffer.append("</ul>");
            }
        } while(true);
        return stringbuffer.toString();
    }

    private static DatabaseAccess db;
}
