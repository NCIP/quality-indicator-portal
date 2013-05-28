<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

<%@ page import="java.util.*"%>
<%@ page import="java.io.File"%>
<jsp:useBean id="db" scope="application" class="gov.nih.nci.qi.DatabaseSetup"/>
<%  
	try{
				String dbProp = (String) config.getInitParameter("dbProp");				
	            String userDir = System.getProperty("user.dir");
				String parentDir = null;
				if (dbProp == null)
					dbProp = "webapps" + File.separator + "mmhcc" + File.separator + "resources" + File.separator + "db.properties";
				System.out.println("dbProp:" + dbProp );
				String dbPropDir = null;
				System.out.println("UserDir:" + userDir );
				File currentDir = new File(userDir);
				
				if(currentDir.isDirectory())
				{
					parentDir = currentDir.getParent();
					System.out.println("parentDir:" + parentDir );
					dbPropDir = parentDir + File.separator + dbProp;
					System.out.println("dbPropDir:" + dbPropDir );
				}
				
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(dbPropDir);
  		props.load(fis);
  		db.setDBProperties(props);
		fis.close();
	  }
		catch (IOException e)
      {
        System.err.println("Failed to load database properties from db.properties");
        e.printStackTrace(System.err);
      }
		%>
</jsp:useBean>	
