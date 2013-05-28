<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

<!DOCTYPE html public "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/Transitional.dtd">
<HTML>
<HEAD>
<TITLE>Submit Entry</TITLE>
<META http-equiv="Content-Type" content="text/html;">
<LINK REL=StyleSheet HREF="css/qiStyle.css" TYPE="text/css">
 <%response.setDateHeader("Expires",System.currentTimeMillis());%>
</HEAD>

<body>

<div class="page">

<%@ include file="header.html"%> 
<%@ page import="java.util.*"%>


<div class="main">


<h2 class="title">Error: Missing Required Fields</h2>
<p>The record could not be added because the following required fields were left empty:
</p>
<%! Vector missing_feilds = new Vector();
		Enumeration feilds = null;
		
		%>
	<%  
	missing_feilds = (Vector)request.getAttribute("gov.nih.nci.qi.MissingFeilds");
	
	if(missing_feilds != null){
	feilds = missing_feilds.elements();
	while(feilds.hasMoreElements()){%>
		<li><font size="+1" color="red"><%=(String)feilds.nextElement()%></font>
	<%}//end while
	}//end if%>
	<P>   
Please go back using your browser's back button and enter a values for these fields.
</p>





</div>
	<p class="note"><a href="select.jsp">Home</a> | <a href="#top">Top of the page</a></p>
	</div>
	
</BODY>
</HTML>
