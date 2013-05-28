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

<BODY>
<div class="page">
	<%@ include file="header.html"%> 

	
	<div class="main">



<%@ page language="java" import="gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*" %>

<%   
 	 SessionManager.verifySession(request,response,"Error.jsp");
   // gov.nih.nci.qi.DatabaseSetup.setPageExpiration(response);
	 
    //SessionManager.verifySession(request,response,"error_entry.html",(String) session.getAttribute("loginId"));
%>




	

			



			<!--start of main table-->		
			<TABLE border="0" align="center" cellspacing="5" cellpadding="5">
		<tr><td>

	
<FORM METHOD=POST ACTION="login_a.jsp">
		<p>&nbsp;</p>
<%! String  userName, password ; %> 
<% userName = (String) session.getAttribute("nci.mmhcc.submitter.userName");
   String mode = (String)request.getParameter("mode");
   %>
<H2 class="title"> You have entered 
&quot;<font color=red><%= userName %></font>&quot; as your user name. Either the user name or password  is incorrect. </h2><br>
<p>
<br>
Helpful Hints:
<ul><li>Make sure you did not mistype. 
<li>Did you enter your nick name?
<li>Make sure to enter your password correctly.</ul>

	<br>
<tr><td colspan="2">  <p class="note">
			   * If you don't have an user account yet, please contact  <A 
                href="mailto:ncicb@pop.nci.nih.gov?subject=QualityIndicator">ncicb@pop.nci.nih.gov</A> to request an user name and a password. 
			  </P>	</td></tr>
<tr><td colspan="2">User with existing user account?<a href="index.jsp" > Sign in</a> to submit or search your quality indicator.</td></tr>

</FORM>



		</td></tr>
		
		</table>

	
	

</div>


<p class="note"><a href="index.jsp">Home</a> | <a href="#top">Top of the page</a></p>
</div>
</BODY>
<!-- #EndTemplate -->
</HTML>
