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

import gov.nih.nci.qi.DatabaseSetup;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;

public class LoadServlet extends HttpServlet
{

    public LoadServlet()
    {
        database = new DatabaseSetup();
    }
    public void init(ServletConfig config)
    throws ServletException
  {
     super.init(config);    // required
		try{
     ServletContext context = config.getServletContext();
		// Find out the server type unix or windows
		 String userDir = System.getProperty("user.dir");
		 System.out.println(userDir);
		 String dbPropDir = null; 			
		 if(userDir.indexOf(":")!= -1){ //if a windows system
		 	dbPropDir = context.getInitParameter ("dbProp_WIN32");			
		 }
		 else{
		 	dbPropDir = context.getInitParameter ("dbProp_UNIX");			
		 }
		 
	 
		
	   System.out.println(dbPropDir);
     System.out.println (" Load dbProp  " +
       (dbPropDir != null ? "succeeded" : "failed"));
		 			
		Properties props = new Properties();
		
		FileInputStream fis = new FileInputStream(dbPropDir);
		props.load(fis);
		database.setDBProperties(props);
		fis.close();
		}
		catch(Exception e){
		System.out.println(" Error in Init for Loadservlet");
		e.printStackTrace();
		}
	}
/*
    public void init(ServletConfig servletconfig)
        throws ServletException
    {
        super.init(servletconfig);
        try
        {
            String s = null;
            ServletContext servletcontext = servletconfig.getServletContext();
            String s1 = servletcontext.getRealPath("/");
            String s2 = System.getProperty("file.separator");
            if(s2.equalsIgnoreCase("\\"))
                s = servletcontext.getInitParameter("dbProp_WIN32");
            else
                s = servletcontext.getInitParameter("dbProp_UNIX");
            String s3 = (new StringBuilder()).append(s1).append("WEB-INF").append(s2).append("resources").append(s2).append(s).toString();
            FileInputStream fileinputstream = new FileInputStream(s3);
            Properties properties = new Properties();
            properties.load(fileinputstream);
            database.setDBProperties(properties);
            fileinputstream.close();
        }
        catch(Exception exception)
        {
            System.out.println(" Error in Init for Loadservlet");
            exception.printStackTrace();
        }
    }
*/
    private DatabaseSetup database;
}
