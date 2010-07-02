<%@ include file="submission_header1.html"%>
 
<%@ page import="java.util.*,nci.mmhcc.util.*"%>
<TABLE align="left" width="80%" cellspacing="10" cellpadding="10" border="0">
	<TR>
	<!--start of naviagation table-->
		<TD align="left" valign="top" width="10%">
</TD><%-- check which navBar to include --%>
		
		<TD valign="top">
			<!--start of main table-->
			<TABLE border="0" align="left" cellspacing="5" cellpadding="5">
			<!--insert mainpart here-->
		<tr><td>
<H2> Authentication Failure </H2>
<p> You access to restricted information is denied.
</p>

<P>
<form method = "post" action ="index.jsp">
<input type ="submit" name ="submit" value ="Return to Home" >
</form>
		</td></tr>
		</table>
		<!--end of main table-->
		</TD>
	</TR>
</TABLE>
<!--end of wrap table-->

</BODY>

</HTML>
