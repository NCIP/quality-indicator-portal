<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

 
<%@ page import="java.io.*, java.util.*"%>   
<% 	if(!session.isNew()){
		request.getSession(false);
		System.out.println("Session id :"+(String)session.getId());%>		
		<%@ include file="frontpage.html"%>		
		<%}     
	else { 
	    request.getSession(true);%>
		<%@ include file="frontpage.html"%>		  
        <%}
		
	if(request.getParameter("submit_name") != null){
	   response.sendRedirect("redirect.jsp");	   
	  }	
	%>
  
           


  
    
     
