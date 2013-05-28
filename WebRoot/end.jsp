<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

<%@ include file="submission_header1.html"%>

<%@ page language="java" import="nci.mmhcc.*, java.lang.*,java.util.*,java.text.*, nci.mmhcc.db.*,nci.mmhcc.util.*" %>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Pragma","no-cache");
   String modelName =(String) session.getAttribute("nci.mmhcc.modeluidForReview");
	Long userTypekey = (Long)session.getAttribute("nci.mmhcc.usertypekey");	
	String requestType = (String) session.getAttribute("nci.mmhcc.requestType");  
    Long submitterKey = (Long) session.getAttribute("nci.mmhcc.submitter.submitterKey"); 
	System.out.println("modelName:"+modelName+"usertypekey:"+userTypekey);
	String screenComment = (String)request.getAttribute("nci.mmhcc.screeningComment");
    System.out.println(" screenComment:"+screenComment);   
	String url = (String)request.getAttribute("nci.mmhhc.url");
	System.out.println(" url:"+url);   
   if(screenComment != null && screenComment.equals("1")){%>
       <form method ="post" action ="<%=url%>">
      <%}
   else  {%>
      <form method ="post" action = "searchResults.jsp">
     <%}%>
   
<TABLE align="center" width="80%" cellspacing="10" cellpadding="10" border="0">

<br><br><br>
	<TR>
	<!--start of naviagation table-->
		<TD align="center" valign="top" width="10%"></td>
	<%if(userTypekey != null && userTypekey.equals(new Long(2))){
		     if(screenComment != null && screenComment.equals("1")){%>
		        Thank you for screening comments for the model <b><%=modelName%></b>.
                <td align = "left"><input type="submit" name="nextModel" value="Screen Another Comment"></td>
	          <%}
	         else{%>
	           Thank you for screening the model <b><%=modelName%></b>.
	            <td align = "left"><input type="submit" name="nextModel" value="Screen Another Model"></td>
	           <%}
	        }%>
	<%if(userTypekey != null && userTypekey.equals(new Long(3))){%>
	Thank you for editing the model<b> <%=modelName%></b>.
	<td align = "left"><input type="submit" name="nextModel" value="Edit Another Model"></td>
	<%}%>
	<%if(userTypekey != null && userTypekey.equals(new Long(4))){%>
	Thank you for reviewing the model <b><%=modelName%></b>.
	<td align = "left"><input type="submit" name="nextModel" value="Review Another Model"></td>
	<%}%>
	
		
	</TR>
</TABLE>
</form>
<!--end of wrap table-->

<% 
 // System.err.println("***"+requestType+userTypekey+submitterKey);
  //if(request.getParameter("nextModel") != null){
 // if(requestType != null && requestType.equalsIgnoreCase("Admin")&& userTypekey.equals(new Long(2))||userTypekey.equals(new Long(3))||userTypekey.equals(new Long(4))){	%>			
   <//jsp:forward page="searchResults.jsp"/>
	  <%
				
			//}  
 // }
  
  %>

	</BODY>
</HTML>
