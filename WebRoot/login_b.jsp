<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!--
  	Himanso Sahni
	MMHCC
	SAIC NCI
	login_name.jsp
-->


<html>

<%@ page language="java" import="nci.mmhcc.db.*,nci.mmhcc.util.*" %>
<%@ page info="this page is the password login page, password is validated on tbis page "%>

<%
   //gov.nih.nci.qi.DatabaseSetup.setPageExpiration(response);
   //SessionManager.verifySession(request,response,"error_entry.html",(String) session.getAttribute("loginId"));

   response.setHeader("Cache-Control","no-cache");
   response.setHeader("Pragma","no-cache");
%>
<head>

<%@ include file="submission_header.html"%>
	<title>Cancer Models Database - Submission</title>

<link rel=stylesheet Type="text/css" HREF="resources\styles.css">

</head>



<body>

			
<div align="center"><font size="+2" color="#800000"><strong>Cancer Models Database - Submission</strong></font></div>

<!--start of wrap table-->
<TABLE align="left" width="80%" cellspacing="10" cellpadding="10" border="0">
	<TR>
	<!--start of naviagation table-->
		<TD align="left" valign="top" width="10%">
			</TD>
		<!--end of naviagation table-->
		<TD valign="top">
			<!--start of main table-->
<TABLE BORDER=0 align=center cellspacing="5" cellpadding="5">
<TR ALIGN=left VALIGN=TOP>
<TD><h2>Enter your Personal Access Password:</h2></TD></tr>
<Tr>
<TD align="center">
<FORM METHOD=POST ACTION="login_b.jsp">
<INPUT TYPE=password MAXLENGTH=20 SIZE=20  NAME="password"  VALUE="" tabindex="1">
</TD></tr>
<tr>
<TD align="center"><INPUT TYPE=SUBMIT NAME="submit_password" VALUE="   NEXT   &gt;&gt;&gt;" tabindex="0">
</TD>
</TR>



</TABLE>
</FORM>

		<!-- End of the main part table -->
		</TD>
	</TR>
</TABLE><!-- End of the wrap table -->

<%@ page import="java.util.*"%>
 <jsp:useBean id="user"  scope="page" class="nci.mmhcc.db.Submitter" />
 <jsp:setProperty name="user" property="password" param="password"/>
 <%! String where, firstName, lastName; 
 	 Long SubmitterId; %> 
 <% firstName = (String) session.getAttribute("nci.mmhcc.submitter.firstName");
    lastName = (String) session.getAttribute("nci.mmhcc.submitter.lastName");
 	where = " UPPER(LASTNAME) like UPPER('%" + lastName + "%') and UPPER(FIRSTNAME) like UPPER('%" + firstName + "%') " +
			" and PASSWORD = '" + user.getPassword() + "'";
 	 System.err.println( "loginb_where: " + where);
Enumeration  paramNames = request.getParameterNames();
while(paramNames.hasMoreElements()){
		String name = (String)paramNames.nextElement();
		String value = (String)request.getParameter(name);
		System.out.println(name + " :" + value );
		}

%>
			
 <%@ page import="java.lang.Long"%>		
 <%@ page import="nci.mmhcc.util.*"%>
 <%! Vector records = new Vector(); %>
 <%-- Verify password --%>
 <%  if(request.getParameter("submit_password")!= null && user.getPassword()!= null ){
     	records = user.retrieveAllWhere(where);
		System.err.println("where:"+ where + "record size:" + records.size());
	     if(records.size() == 1){  
		 	user = (Submitter) records.firstElement();	
		 	SubmitterId = user.getSubmitteruid(); 
		 	System.err.println("set submitterid" + SubmitterId); 
	     	session.setAttribute("nci.mmhcc.submitter.submitterKey",SubmitterId);
		 	String requestType = (String) session.getAttribute("nci.mmhcc.requestType");
			System.err.println("password mode:" + requestType);
			//clean up session object
			//session.removeAttribute("nci.mmhcc.submitter.firstName");
			//session.removeAttribute("nci.mmhcc.submitter.lastName");
		 	if(requestType != null && requestType.equalsIgnoreCase("Review")){%>
			<jsp:forward page="searchResults.jsp" />
		  <%} 
		  else{
		  		QISession mySession = new QISession();
				mySession.setObjectID("SUBMITTER",SubmitterId,user.getLastname()+","+user.getFirstname());
				session.removeAttribute("nci.mmhcc.submitter.mmhccSession");
				session.setAttribute("nci.mmhcc.mmhccSession",mySession);
				%>
				<jsp:forward page="General_info.jsp" />	
		  <%}
  		}
		else{%>
<%System.err.println("record size:" + records.size());%>
			<jsp:forward page="login_pwderror.html" />	
	  	<%}
		}
	%>

</BODY>
</HTML>
