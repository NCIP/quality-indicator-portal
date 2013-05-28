<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

                     

<%@ page language="java" import="gov.nih.nci.qi.*, java.lang.*,java.util.*,java.text.*, gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*" %>

<%	 response.setHeader("Cache-Control","no-cache");
     response.setHeader("Pragma","no-cache");
     
	 
	 Long sampleId = Long.decode((String)request.getParameter("sampleId"));
	 Long submitterId = Long.decode((String)request.getParameter("submitterId"));
	 String labtrak_id = (String)request.getParameter("labtrak_id");
	
	
	
	
	
	 QISession mySession = new QISession(submitterId);
	 session.removeAttribute("nci.mmhcc.mmhccSession");  
	 session.setAttribute("nci.mmhcc.mmhccSession",mySession);	
	 session.removeAttribute("nci.mmhcc.resubmit_key");
	 session.setAttribute("nci.mmhcc.resubmit_key",submitterId.toString());	                 
   
	 boolean status1 =false;
	
	%>
	<%@ include file="exit.html"%> 
	
	
	
