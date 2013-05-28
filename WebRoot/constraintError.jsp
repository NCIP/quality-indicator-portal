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


<div class="main">


<% response.setDateHeader("Expires",System.currentTimeMillis());
   String thePage = (String)session.getAttribute("gov.nih.nci.qi.constaint");   
   String id =(String)session.getAttribute("gov.nih.nci.qi.id"); 
   
   
   
   %>
<FORM method="POST" action="constraintError.jsp">
	<INPUT type="hidden" name="a" value="1">
	<TABLE border="0" align="center" cellspacing="5" cellpadding="5" width="95%">
		<TR>
			<TD>	
			<%if(thePage != null){
			    if(thePage.equals("General_info.jsp")){%>		
			    <H2 class ="title">LabTrak ID must be unique.			
			<%  }
			    else{%>
			   <H2 class ="title">Sub-sample ID must be unique.			
			<% }
			}
			 %>
			</TD>
		</TR>
		<TR align="left">
			<TD valign="bottom">
			</td>
			</tr>
			</table>
			</TD>
		</tr>
</TABLE>	


		
<!--- 			Click on <a href="index.jsp"><img src="images/home_UP.gif" width="38" height="16" border="0" alt="Home"></a> if you would like to return to the home page.&nbsp;&nbsp;If you would like to enter or modify another model, please click <a href="intro.jsp">here</a>	 --->
			<table class="tbuttons" align="center">
			<tr>
				<td><input type="submit" class="buttons" name="backButton" value=" Go Back ">&nbsp;&nbsp;</td>
			</tr>
			</table>
 
	</div>
	</div>

<%					
	if(request.getParameter("backButton")!= null ){			  
	  String destination = null;
	  if(thePage != null){	    
	     session.removeAttribute("gov.nih.nci.qi.constaint");
		 session.removeAttribute("gov.nih.nci.qi.id");
	    if(id==null || id.equals("")){
		  destination = thePage;		    
	      %>
	      <jsp:forward page="<%=destination%>"/>						
	      <%}
	    else if(id != null && !id.equals("")){
		   destination = thePage+"?id="+id;			    
		   %>
		   <jsp:forward page="<%=destination%>"/>	
		<%}
	    }
     }
	
	%>

	
<%@ include file="footer.jsp"%>	
