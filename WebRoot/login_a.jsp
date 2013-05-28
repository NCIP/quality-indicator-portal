<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

 <!--
  	Himanso Sahni
	MMHCC
	SAIC NCI
	login_a.jsp
-->


<%@ page language="java" import="java.util.*,nci.mmhcc.util.*, nci.mmhcc.*,nci.mmhcc.db.*" %>
<%@ page info="this page is the login page, first name & last name and verified on tbis page "%>


<%   
   
	
	//SessionManager sessionManager = new SessionManager(session,-1);
    //SessionManager.setSessionProperties(session,-1);
    //DatabaseSetup.setPageExpiration(response); %>
	<% 	    
 		 //add request type to session 	
		  
		 
		String mode = (String) request.getParameter("mode");
		if(	mode==null){
	         mode = (String) request.getAttribute("nci.mmhcc.newuser");
	       }
		System.out.println("mode   ========:"+mode);
		 if( mode != null){ 
			session.setAttribute("nci.mmhcc.requestType",mode); 
		}
		
		
%>
    
		
<%

  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Pragma","no-cache");%>
<%-- Populate Submitter object with last and first name --%>
<%-- Verify name --%>

<%@ include file="login_a.html"%> 

 
		 	
	     	
		

